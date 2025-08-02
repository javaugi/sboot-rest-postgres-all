/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import io.lettuce.core.RedisURI;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 *
 * @author javaugi
 */
@Component
public class RedisNoSQLConnChecker implements CommandLineRunner {    
    private final static Logger log = LoggerFactory.getLogger(RedisNoSQLConnChecker.class);
    
    @Value("${app.redis.enabled}")
    protected Boolean redisCacheEnabled;
    @Value("${app.redis.host}")
    protected String redisCacheHost;
    @Value("${app.redis.port}")
    protected Integer redisCachePort;
    
    private Jedis jedis;
    
    @Override
    public void run(String... args) throws Exception {
        if (redisCacheEnabled && StringUtils.isNotEmpty(redisCacheHost)) {
            boolean redisRunning = redisConnectedRunning();
            log.info("Checking Redis connected running {}", redisRunning); 
            runRedisNoSQLDemo();
        }
    }
    
    private boolean redisConnectedRunning() {        
        try {
            jedis = new Jedis(redisCacheHost, redisCachePort);
            log.info("redisConnectedRunning ...");
            return true;
        } catch (Exception e) {
            log.error("Failed to connect to Redis: " + e.getMessage(), e);
            // Optionally, you can halt the application startup
            // SpringApplication.exit(SpringApplicationContext.getAppContext(), () -> 1);
            // System.exit(1);
        }
        
        return false;
    }
    
    private void runRedisNoSQLDemo() {
        try {
            jedis.set("name", "Jane Smith");
            String name = jedis.get("name");
            System.out.println("Name: " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

/*
URI syntax
Redis Standalone

redis :// [[username :] password@] host [:port][/database]
          [?[timeout=timeout[d|h|m|s|ms|us|ns]] [&clientName=clientName]
          [&libraryName=libraryName] [&libraryVersion=libraryVersion] ]
Redis Standalone (SSL)

rediss :// [[username :] password@] host [: port][/database]
           [?[timeout=timeout[d|h|m|s|ms|us|ns]] [&clientName=clientName]
           [&libraryName=libraryName] [&libraryVersion=libraryVersion] ]
Redis Standalone (Unix Domain Sockets)

redis-socket :// [[username :] password@]path
                 [?[timeout=timeout[d|h|m|s|ms|us|ns]] [&database=database]
                 [&clientName=clientName] [&libraryName=libraryName]
                 [&libraryVersion=libraryVersion] ]
Redis Sentinel

redis-sentinel :// [[username :] password@] host1[:port1] [, host2[:port2]] [, hostN[:portN]] [/database]
                   [?[timeout=timeout[d|h|m|s|ms|us|ns]] [&sentinelMasterId=sentinelMasterId]
                   [&clientName=clientName] [&libraryName=libraryName]
                   [&libraryVersion=libraryVersion] ]
*/
