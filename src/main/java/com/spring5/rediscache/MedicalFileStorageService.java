/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.rediscache;

import com.spring5.RedisConfig;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MedicalFileStorageService {

    private static final String FILE_METADATA_CACHE = "fileMetadata";

    @Autowired
    private FileStorageRepository fileStorageRepository;
    @Autowired
    private FileMetadataRepository fileMetadataRepository;
    
    @Autowired
    private @Qualifier(RedisConfig.REDIS_TPL_MFILE) RedisTemplate<String, MedicalFileMetadata> redisTemplate;

    @Cacheable(value = FILE_METADATA_CACHE, key = "#fileId")
    public MedicalFileMetadata getFileMetadata(String fileId) throws Exception {
        return fileMetadataRepository.findById(Long.valueOf(fileId))
            .orElseThrow(() -> new FileNotFoundException(fileId));
    }

    public void storeFile(MedicalFile file) {
        // Store actual file in your storage system (S3, filesystem, etc.)
        fileStorageRepository.save(file);
        
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
