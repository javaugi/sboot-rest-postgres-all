    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.rediscache;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisServerCommands;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
        } catch (DataAccessException e) {
            return new HealthCheckResponse(
                "Redis", 
                "DOWN", 
                Map.of("error", e.getMessage())
            );
        }
    }

    @Scheduled(fixedRate = 60000)
    public void monitorRedisPerformance() {
        try (RedisConnection connection = redisConnectionFactory.getConnection()) {
            RedisServerCommands serverCommands = connection.serverCommands();
            
            Map<String, Object> metrics = new HashMap<>();
            metrics.put("used_memory", serverCommands.info("memory").getProperty("used_memory"));
            metrics.put("connected_clients", serverCommands.info("clients").getProperty("connected_clients"));
            
            // Log or send to monitoring system
            logRedisMetrics(metrics);
        }
    }
    
    private void logRedisMetrics(Map<String, Object> metrics) {
        System.out.println("logRedisMetrics: " + metrics);
    }
}