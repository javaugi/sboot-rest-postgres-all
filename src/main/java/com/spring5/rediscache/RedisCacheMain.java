/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.rediscache;

/**
 *
 * @author javaugi
 */
public class RedisCacheMain {
    
}
/*
Redis Cache Implementation for Healthcare App
Here's a comprehensive design and implementation for using Redis in a healthcare application with file storage and auditing microservices.

Architecture Overview
    Healthcare App → [Redis Cache Layer] → [Microservices]
                                      ↘ File Storage Service
                                      ↘ Auditing Service
1. Core Dependencies
    First, add these dependencies to your pom.xml:
    <!-- Spring Boot Starter for Redis -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>

    <!-- For Redis connection pooling -->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-pool2</artifactId>
    </dependency>

    <!-- For JSON serialization -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>

2. Redis Configuration
        @Configuration
        @EnableCaching
        public class RedisConfig {

            @Value("${spring.redis.host}")
            private String redisHost;

            @Value("${spring.redis.port}")
            private int redisPort;

            @Bean
            public LettuceConnectionFactory redisConnectionFactory() {
                RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
                return new LettuceConnectionFactory(config);
            }

            @Bean
            public RedisTemplate<String, Object> redisTemplate() {
                RedisTemplate<String, Object> template = new RedisTemplate<>();
                template.setConnectionFactory(redisConnectionFactory());
                template.setKeySerializer(new StringRedisSerializer());
                template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
                return template;
            }

            @Bean
            public CacheManager cacheManager() {
                RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofMinutes(30))
                    .disableCachingNullValues()
                    .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));

                return RedisCacheManager.builder(redisConnectionFactory())
                    .cacheDefaults(cacheConfig)
                    .build();
            }
        }
3. Patient Data Caching Service
        @Service
        public class PatientDataService {

            private static final String PATIENT_CACHE = "patientCache";
            private final PatientRepository patientRepository;

            @Autowired
            private RedisTemplate<String, Object> redisTemplate;

            @Cacheable(value = PATIENT_CACHE, key = "#patientId")
            public Patient getPatientById(String patientId) {
                return patientRepository.findById(patientId)
                    .orElseThrow(() -> new PatientNotFoundException(patientId));
            }

            @CachePut(value = PATIENT_CACHE, key = "#patient.id")
            public Patient updatePatient(Patient patient) {
                return patientRepository.save(patient);
            }

            @CacheEvict(value = PATIENT_CACHE, key = "#patientId")
            public void deletePatient(String patientId) {
                patientRepository.deleteById(patientId);
            }

            // Bulk operations with pipeline
            public Map<String, Patient> getMultiplePatients(List<String> patientIds) {
                return redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
                    StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                    patientIds.forEach(id -> stringRedisConn.get("patientCache::" + id));
                    return null;
                }).stream()
                  .filter(Objects::nonNull)
                  .collect(Collectors.toMap(
                      p -> ((Patient)p).getId(),
                      p -> (Patient)p
                  ));
            }
        }
4. File Storage Service with Redis Metadata Cache
java
Copy
@Service
public class MedicalFileStorageService {

    private static final String FILE_METADATA_CACHE = "fileMetadata";
    private final FileStorageRepository fileStorageRepository;
    
    @Autowired
    private RedisTemplate<String, MedicalFileMetadata> redisTemplate;

    @Cacheable(value = FILE_METADATA_CACHE, key = "#fileId")
    public MedicalFileMetadata getFileMetadata(String fileId) {
        return fileStorageRepository.findMetadataById(fileId)
            .orElseThrow(() -> new FileNotFoundException(fileId));
    }

    public void storeFile(MedicalFile file) {
        // Store actual file in your storage system (S3, filesystem, etc.)
        fileStorageRepository.saveFile(file);
        
        // Cache the metadata
        redisTemplate.opsForValue().set(
            FILE_METADATA_CACHE + "::" + file.getId(),
            file.getMetadata(),
            Duration.ofHours(1)
        );
    }

    // Pattern-based cache eviction for related files
    public void invalidateRelatedFiles(String patientId) {
        Set<String> keys = redisTemplate.keys(FILE_METADATA_CACHE + "*patient:" + patientId + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}
5. Auditing Service with Redis Streams
java
Copy
@Service
public class AuditService {

    private static final String AUDIT_STREAM = "healthcare:audit:stream";
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void logAuditEvent(AuditEvent event) {
        ObjectRecord<String, AuditEvent> record = StreamRecords.newRecord()
            .ofObject(event)
            .withStreamKey(AUDIT_STREAM);
            
        redisTemplate.opsForStream().add(record);
    }

    public List<AuditEvent> getRecentAuditEvents(int count) {
        return redisTemplate.opsForStream()
            .read(AuditEvent.class, StreamOffset.latest(AUDIT_STREAM))
            .limit(count)
            .map(Record::getValue)
            .collect(Collectors.toList());
    }

    // Batch processing of audit events
    @Scheduled(fixedRate = 30000)
    public void processAuditEvents() {
        List<ObjectRecord<String, AuditEvent>> records = redisTemplate.opsForStream()
            .read(AuditEvent.class, StreamOffset.fromStart(AUDIT_STREAM));
            
        if (!records.isEmpty()) {
            // Process and persist to permanent storage
            persistToDatabase(records);
            
            // Acknowledge processing
            String lastId = records.get(records.size()-1).getId().getValue();
            redisTemplate.opsForStream().trim(AUDIT_STREAM, lastId);
        }
    }
}
6. Distributed Lock for Critical Operations
java
Copy
@Service
public class CriticalHealthcareOperationService {

    private static final String LOCK_KEY_PREFIX = "healthcare:lock:";
    private static final long LOCK_EXPIRE_TIME_MS = 30000;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean performCriticalOperation(String operationId, String patientId) {
        String lockKey = LOCK_KEY_PREFIX + operationId + ":" + patientId;
        String lockValue = UUID.randomUUID().toString();

        try {
            // Attempt to acquire lock
            Boolean locked = redisTemplate.opsForValue().setIfAbsent(
                lockKey, 
                lockValue, 
                Duration.ofMillis(LOCK_EXPIRE_TIME_MS)
            );

            if (Boolean.TRUE.equals(locked)) {
                // Perform your critical operation
                return true;
            }
            return false;
        } finally {
            // Release lock only if it's ours
            String currentValue = redisTemplate.opsForValue().get(lockKey);
            if (lockValue.equals(currentValue)) {
                redisTemplate.delete(lockKey);
            }
        }
    }
}
7. Health Check and Monitoring
java
Copy
@Service
public class RedisHealthService {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    public HealthCheckResponse checkRedisHealth() {
        try {
            RedisConnection connection = redisConnectionFactory.getConnection();
            String pong = connection.ping();
            connection.close();
            
            return new HealthCheckResponse(
                "Redis", 
                "UP", 
                Map.of("response", pong, "timestamp", Instant.now())
            );
        } catch (Exception e) {
            return new HealthCheckResponse(
                "Redis", 
                "DOWN", 
                Map.of("error", e.getMessage())
            );
        }
    }

    @Scheduled(fixedRate = 60000)
    public void monitorRedisPerformance() {
        RedisConnection connection = redisConnectionFactory.getConnection();
        RedisServerCommands serverCommands = connection.serverCommands();
        
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("used_memory", serverCommands.info("memory").getProperty("used_memory"));
        metrics.put("connected_clients", serverCommands.info("clients").getProperty("connected_clients"));
        
        // Log or send to monitoring system
        logRedisMetrics(metrics);
        connection.close();
    }
}
Best Practices for Healthcare Applications
Data Sensitivity:

Always encrypt sensitive patient data before caching

Implement proper TTL (Time-To-Live) for cached data

Use Redis ACLs for proper access control

Cache Strategies:

Use write-through caching for critical data

Implement cache-aside pattern for less critical data

Consider multi-level caching (Redis + local cache)

Audit Trail:

Maintain immutable audit logs using Redis Streams

Implement proper retention policies

Regularly back up audit data to permanent storage

Performance:

Use pipelining for bulk operations

Monitor Redis memory usage carefully

Consider Redis clustering for high availability

Would you like me to elaborate on any specific aspect of this implementation or provide additional examples for particular use cases in your healthcare application?
*/