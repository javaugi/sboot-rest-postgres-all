/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.rediscache;

//import static jakarta.persistence.GenerationType.UUID;
import com.spring5.RedisConfig;
import java.time.Duration;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CriticalHealthcareOperationService {

    private static final String LOCK_KEY_PREFIX = "healthcare:lock:";
    private static final long LOCK_EXPIRE_TIME_MS = 30000;
    
    @Autowired
    private @Qualifier(RedisConfig.REDIS_TPL_STR) RedisTemplate<String, String> redisTemplate;

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
