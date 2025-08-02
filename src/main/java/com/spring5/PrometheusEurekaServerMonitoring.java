/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

/**
 *
 * @author javaugi
 */
public class PrometheusEurekaServerMonitoring {
    
}

/*
Here's how to set up Spring Boot with Prometheus and Eureka for monitoring microservices.
1. Add Dependencies
Include the necessary dependencies in your pom.xml:
Code

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
</dependencies>
2. Configure Eureka Client
Add the Eureka client configuration to your application.yml or application.properties:
Code

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
Annotate your Spring Boot application class with @EnableDiscoveryClient:
Java

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class MyApplication {
    // ...
}
3. Configure Prometheus
Configure Prometheus to scrape metrics from your Spring Boot application. Create a prometheus.yml file: 
Code

scrape_configs:
  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']
If you are using Eureka for service discovery, you can configure Prometheus to discover and scrape services dynamically:
Code

scrape_configs:
  - job_name: 'eureka-services'
    eureka_sd_configs:
      - server: 'http://localhost:8761'
        refresh_interval: 5s
    relabel_configs:
      - source_labels: [__meta_eureka_app_instance_metadata_prometheus_scrape]
        action: keep
        regex: true
      - source_labels: [__address__, __meta_eureka_app_instance_metadata_prometheus_port]
        action: replace
        regex: ([^:]+)(?::\d+)?;(\d+)
        replacement: $1:$2
        target_label: __address__
      - source_labels: [__meta_eureka_app_instance_metadata_prometheus_path]
        action: replace
        target_label: __metrics_path__
        regex: (.+)
Ensure your Spring Boot application exposes Prometheus metrics by including these properties in your application.yml or application.properties:
Code

management:
  endpoints:
    web:
      exposure:
        include: prometheus
4. Start Eureka Server
If you don't have a running Eureka server, you can create one using Spring Cloud:
Java

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
Configure the Eureka server in application.yml or application.properties:
Code

server:
  port: 8761
eureka:
  client:
    registerWithEureka: false
    fetchRegistry: false
5. Run and Monitor
Start the Eureka server.
Start your Spring Boot application(s). They should register with Eureka.
Start Prometheus using the configured prometheus.yml.
Access the Prometheus UI (usually at http://localhost:9090) to query and visualize metrics.
This setup allows you to monitor your Spring Boot microservices using Prometheus, with service discovery handled by Eureka.
*/
