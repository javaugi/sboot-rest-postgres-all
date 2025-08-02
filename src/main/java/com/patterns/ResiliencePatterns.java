package com.patterns;


import static com.google.common.collect.Lists.asList;
import com.google.common.util.concurrent.TimeLimiter;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import org.apache.commons.lang3.concurrent.CircuitBreaker;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Retry;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author javaugi
 */
public class ResiliencePatterns {

    private static final ResiliencePatterns main = new ResiliencePatterns();

    public static void main(String[] args) {
        main.run();
    }

    private void run() {

    }

    private void faultTolerance() {
        /*
        A. Fault Tolerance
            1. Circuit Breaker
                Use Case: Prevent cascading failures from downstream outages.
                Tools: Resilience4j, Spring Retry.        
        // */
 /*
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // Open circuit after 50% failures
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .build();

        CircuitBreaker circuitBreaker = CircuitBreaker.of("paymentService", config);

        // Wrap external call
        Supplier<PaymentResponse> decorated = CircuitBreaker.decorateSupplier(
                circuitBreaker,
                () -> paymentClient.processPayment()
        );
        // */

        /*
        // Create a CircuitBreaker with default configuration
        CircuitBreaker circuitBreaker = CircuitBreaker
                .ofDefaults("backendService");

        // Create a Retry with default configuration
        // 3 retry attempts and a fixed time interval between retries of 500ms
        Retry retry = Retry
                .ofDefaults("backendService");

        // Create a Bulkhead with default configuration
        Bulkhead bulkhead = Bulkhead
                .ofDefaults("backendService");

        Supplier<String> supplier = () -> backendService
                .doSomething(param1, param2) // Decorate your call to backendService.doSomething() 
                // with a Bulkhead, CircuitBreaker and Retry
                // **note: you will need the resilience4j-all dependency for this

        Supplier<String> decoratedSupplier = Decorators.ofSupplier(supplier)
                .withCircuitBreaker(circuitBreaker)
                .withBulkhead(bulkhead)
                .withRetry(retry)
                .decorate();

        // When you don't want to decorate your lambda expression,
        // but just execute it and protect the call by a CircuitBreaker.
        String result = circuitBreaker
                .executeSupplier(backendService::doSomething);

        // You can also run the supplier asynchronously in a ThreadPoolBulkhead
        ThreadPoolBulkhead threadPoolBulkhead = ThreadPoolBulkhead
                .ofDefaults("backendService");

        // The Scheduler is needed to schedule a timeout 
        // on a non-blocking CompletableFuture
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofSeconds(1));

        CompletableFuture<String> future = Decorators.ofSupplier(supplier)
                .withThreadPoolBulkhead(threadPoolBulkhead)
                .withTimeLimiter(timeLimiter, scheduledExecutorService)
                .withCircuitBreaker(circuitBreaker)
                .withFallback(asList(TimeoutException.class,
                        CallNotPermittedException.class,
                        BulkheadFullException.class),
                        throwable -> "Hello from Recovery")
                .get().toCompletableFuture();
        // */

    }

}
