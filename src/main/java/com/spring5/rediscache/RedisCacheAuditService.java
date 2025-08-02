/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.rediscache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring5.RedisConfig;
import com.spring5.audit.AuditEvent;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamMessageListenerContainer.StreamReadRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class RedisCacheAuditService {

    private static final String AUDIT_STREAM = "healthcare:audit:stream";

    @Autowired
    private @Qualifier(RedisConfig.REDIS_TPL) RedisTemplate<String, Object> redisTemplate;

    public void logAuditEvent(AuditEvent event) {
        ObjectRecord<String, AuditEvent> record = StreamRecords.newRecord()
                .ofObject(event)
                .withStreamKey(AUDIT_STREAM);

        redisTemplate.opsForStream().add(record);
    }

    /*
    public List<AuditEvent> getRecentAuditEvents(int count) {
        return redisTemplate.opsForStream()
            .read(AuditEvent.class, StreamOffset.latest(AUDIT_STREAM))
            .limit(count)
            .map(Record::getValue)
            .collect(Collectors.toList());
    }
    // */
    public List<AuditEvent> getRecentAuditEvents(int count) {
        StreamReadOptions options = StreamReadOptions.empty().count(count).block(Duration.ofMillis(100));

        /*
        List<MapRecord<String, Object, Object>> records = redisTemplate.opsForStream()
                .read(StreamReadRequest.builder(StreamOffset.latest(AUDIT_STREAM))
                        .options(options)
                        .build());
        List<MapRecord<String, Object, Object>> records = redisTemplate.opsForStream()
                .read(StreamReadRequest.builder(StreamOffset.latest(AUDIT_STREAM))
                        .options(options)
                        .build());

        return records.stream()
                .map(record -> convertToAuditEvent(record))
                .collect(Collectors.toList());
        // */
        
        return null;
    }

    private AuditEvent convertToAuditEvent(MapRecord<String, Object, Object> record) {
        // Example conversion logic — adapt to your actual AuditEvent structure
        AuditEvent event = new AuditEvent();
        event.setEventId((String) record.getValue().get("eventId"));
        //event.setTimestamp((String) record.getValue().get("timestamp"));
        event.setEventType((String) record.getValue().get("type"));
        event.setDetails((String) record.getValue().get("details"));
        return event;
    }
    
    
    /*
    
    Optional improvement using object mapping:
    If you’re storing serialized JSON in Redis, you can integrate Jackson like this:
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private AuditEvent convertToAuditEvent(MapRecord<String, Object, Object> record) {
        return objectMapper.convertValue(record.getValue(), AuditEvent.class);
    }
    //*/


    // Batch processing of audit events
    @Scheduled(fixedRate = 30000)
    public void processAuditEvents() {
        List<ObjectRecord<String, AuditEvent>> records = redisTemplate.opsForStream()
                .read(AuditEvent.class, StreamOffset.fromStart(AUDIT_STREAM));

        if (!records.isEmpty()) {
            // Process and persist to permanent storage
            persistToDatabase(records);

            // Acknowledge processing
            String lastId = records.get(records.size() - 1).getId().getValue();
            redisTemplate.opsForStream().trim(AUDIT_STREAM, Long.valueOf(lastId));
        }
    }

    private void persistToDatabase(List<ObjectRecord<String, AuditEvent>> records) {

    }
}
