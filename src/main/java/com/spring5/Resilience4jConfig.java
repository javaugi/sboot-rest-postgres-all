/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author javaugi
 */
@Component
public class Resilience4jConfig implements CommandLineRunner {    
    private final static Logger log = LoggerFactory.getLogger(Resilience4jConfig.class);
    
    @Override
    public void run(String... args) throws Exception {
        log.info("Resilience4jConfig running {}"); 
    }
    
}

/*
Could you give me a Java code example for spring boot using Resilience4j



Show thinking
Okay, let's create a simple Spring Boot Java code example demonstrating the use of Resilience4j, specifically focusing on the Circuit Breaker pattern, as it's a fundamental resilience mechanism.

We'll create a service that simulates a call to an external system that might fail. Resilience4j will wrap this call with a Circuit Breaker to prevent cascading failures.

1. Project Setup (Maven)

Add the necessary dependencies to your pom.xml. You'll need the Spring Boot Starter Actuator (for health indicators and monitoring), the Spring Boot AOP Starter (as Resilience4j uses AOP), and the Resilience4j Spring Boot 3 starter.

XML

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>resilience4j-demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.4</version> <relativePath/> </parent>

    <properties>
        <java.version>17</java.version>
        <resilience4j.version>2.2.0</resilience4j.version> </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
2. Application 1  Configuration (application.yml)   
 1. 
github.com
github.com

Configure the Circuit Breaker in your src/main/resources/application.yml. We'll define a Circuit Breaker named externalService.

YAML

resilience4j.circuitbreaker:
  instances:
    externalService:
      registerHealthIndicator: true
      slidingWindowType: COUNT_BASED
      slidingWindowSize: 10
      failureRateThreshold: 50 # Open the circuit if 50% of calls fail
      waitDurationInOpenState: 5s # Stay open for 5 seconds
      permittedNumberOfCallsInHalfOpenState: 3 # Allow 3 calls in half-open state
      eventConsumerBufferSize: 10
Explanation of Configuration:

instances.externalService: Defines a Circuit Breaker instance named externalService.
registerHealthIndicator: Registers a Spring Boot Health Indicator for this Circuit Breaker, accessible via /actuator/health.
slidingWindowType: How the sliding window for calculating the failure rate is measured (COUNT_BASED or TIME_BASED).
slidingWindowSize: The size of the sliding window. For COUNT_BASED, it's the number of calls.
failureRateThreshold: The percentage of failed calls in the sliding window that will trip the circuit to the OPEN state.
waitDurationInOpenState: How long the circuit stays in the OPEN state before transitioning to HALF_OPEN.
permittedNumberOfCallsInHalfOpenState: The number of calls allowed in the HALF_OPEN state to test if the external service has recovered.
3. The Simulated External Service

Create a service class that simulates calling an external system. We'll introduce some random failures and delays.

Java

package com.example.resilience4jdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class ExternalService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalService.class);
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
4. The Resilient Service

Create a service that calls the simulated external service and annotate the method with @CircuitBreaker. We also define a fallbackMethod to handle cases when the circuit is open or the call fails.

Java

package com.example.resilience4jdemo.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ResilientService {

    private static final Logger logger = LoggerFactory.getLogger(ResilientService.class);
    private final ExternalService externalService;

    public ResilientService(ExternalService externalService) {
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
}
Explanation of Annotations:

@CircuitBreaker(name = "externalService", fallbackMethod = "fallbackForExternalService"): This annotation applies the Circuit Breaker pattern to the performResilientOperation method.
name = "externalService": Refers to the Circuit Breaker instance named externalService configured in application.yml.
fallbackMethod = "fallbackForExternalService": Specifies the name of the method to call if the original method fails (due to an exception, timeout, or the circuit being open). The fallback method must have the same return type as the original method and accept a Throwable as the last argument.
5. The REST Controller

Create a simple REST controller to expose an endpoint that calls the ResilientService.

Java

package com.example.resilience4jdemo.controller;

import com.example.resilience4jdemo.service.ExternalService;
import com.example.resilience4jdemo.service.ResilientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResilienceDemoController {

    private final ResilientService resilientService;
    private final ExternalService externalService; // To see the raw service calls

    public ResilienceDemoController(ResilientService resilientService, ExternalService externalService) {
        this.resilientService = resilientService;
        this.externalService = externalService;
    }

    @GetMapping("/resilient-call")
    public String makeResilientCall() {
        return resilientService.performResilientOperation();
    }

    @GetMapping("/external-stats")
    public String getExternalServiceStats() {
        return "External Service Stats: Successes = " + externalService.getSuccessCount() + ", Failures = " + externalService.getFailureCount();
    }
}
6. The Main Application Class

Standard Spring Boot main class.

Java

package com.example.resilience4jdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Resilience4jDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Resilience4jDemoApplication.class, args);
    }
}
How to Run and Test:

Save the files in the correct package structure (com.example.resilience4jdemo).
Build the project using Maven (mvn clean install).
Run the application (mvn spring-boot:run).
Access the endpoint http://localhost:8080/resilient-call in your web browser or using a tool like curl.
Observe the Behavior:

Initially, calls will directly hit the ExternalService, and you'll see random successes and failures in the application logs.
As the failure rate for externalService exceeds 50% within the configured sliding window (10 calls), the Circuit Breaker will transition to the OPEN state.
Once the circuit is OPEN, all subsequent calls to /resilient-call will immediately trigger the fallbackForExternalService method without even attempting to call externalService.callExternalSystem(). You'll see "Executing fallback method..." in the logs and get the fallback response.
After the waitDurationInOpenState (5 seconds), the circuit will transition to the HALF_OPEN state.
In the HALF_OPEN state, a limited number of calls (permittedNumberOfCallsInHalfOpenState: 3) will be allowed to pass through to externalService.
If these test calls are successful, the circuit will close, and calls will again go directly to externalService.
If the test calls fail, the circuit will return to the OPEN state.
You can also check the Actuator health endpoint http://localhost:8080/actuator/health to see the state of the externalService Circuit Breaker (UP, DOWN, or OUT_OF_SERVICE with details about the state).

This example provides a basic implementation of the Circuit Breaker pattern with Resilience4j in Spring Boot. Resilience4j offers other patterns like Retry, Rate Limiter, Bulkhead, and Time Limiter, which can be implemented similarly using their respective annotations and configurations.
*/