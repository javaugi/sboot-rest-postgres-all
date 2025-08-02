/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class Resilience4jExternalService {

    private static final Logger logger = LoggerFactory.getLogger(Resilience4jExternalService.class);
    private final Random random = new Random();
    private int failureCount = 0;
    private int successCount = 0;

    public String callExternalSystem() {
        logger.info("Calling external system...");

        // Simulate random failures
        if (random.nextBoolean()) {
            failureCount++;
            logger.error("External system call failed!");
            throw new RuntimeException("External system is down!");
        } else {
            successCount++;
            logger.info("External system call successful.");
            return "Success from external system!";
        }
    }

    // Simulate a slow external system call (for Timeout or Bulkhead examples if needed)
    public CompletableFuture<String> callExternalSystemAsync() {
        return CompletableFuture.supplyAsync(() -> {
            logger.info("Calling external system asynchronously...");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5)); // Simulate random delay up to 5 seconds
                if (random.nextBoolean()) {
                    logger.error("Async external system call failed!");
                    throw new RuntimeException("Async external system is down!");
                } else {
                    logger.info("Async external system call successful.");
                    return "Async success from external system!";
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Async call interrupted", e);
                throw new RuntimeException("Async call interrupted", e);
            }
        });
    }

    public int getFailureCount() {
        return failureCount;
    }

    public int getSuccessCount() {
        return successCount;
    }
}
