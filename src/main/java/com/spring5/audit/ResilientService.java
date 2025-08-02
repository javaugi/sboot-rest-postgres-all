/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ResilientService {

    private static final Logger logger = LoggerFactory.getLogger(ResilientService.class);
    private final Resilience4jExternalService externalService;

    public ResilientService(Resilience4jExternalService externalService) {
        this.externalService = externalService;
    }

    @CircuitBreaker(name = "externalService", fallbackMethod = "fallbackForExternalService")
    public String performResilientOperation() {
        logger.info("Attempting resilient operation...");
        return externalService.callExternalSystem();
    }

    public String fallbackForExternalService(Throwable t) {
        logger.warn("Executing fallback method due to: " + t.getMessage());
        return "Fallback response: External service is currently unavailable.";
    }
    
     @Retry(name = "externalService")
     public String callBackendWithRetry() {
        if (Math.random() < 0.3) {
            throw new RuntimeException("Retryable failure");
        }
        return "Call with retry successful";
     }
     
    @RateLimiter(name = "externalService")
    public String callWithRateLimit() {
        return "Call within rate limit";
    }
}