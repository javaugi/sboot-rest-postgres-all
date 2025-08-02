/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import com.spring5.entity.Product;
import com.spring5.entity.ProductRowMapper;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author javaugi
 */
@Configuration
public class PrometheusMetricsConfig {
    /*
    Additional Notes
    If using Spring Boot 2.x+, you don't need to manually create this bean - it's already provided.
    For production, consider adding a monitoring system-specific registry (Prometheus, Datadog, etc.).
    Make sure to include the appropriate Micrometer dependency for your monitoring system.    
    */


    /*
    Creating a Spring-Managed MeterRegistry Bean
      To create a Spring-managed MeterRegistry bean (from Micrometer) in your configuration, you have several options depending 
        on your monitoring system and Spring Boot version.

        Basic Configuration
        1. Simple MeterRegistry Bean (in-memory)
    */
    
    
    private final JdbcTemplate jdbcTemplate;

    public PrometheusMetricsConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Bean
    public MeterRegistry meterRegistry() {
        return new SimpleMeterRegistry();
    }
    
    @Bean
    public ProductRowMapper productRowMapper() {
        return new ProductRowMapper();
    }    
    
    /*  4. How to Measure in Spring Boot: Use Micrometer with custom timers for DB latency tracking:
    
        5. Using Prometheus + Grafana for Categorization: Create PromQL metrics like:

        promql
            histogram_quantile(0.95, rate(db_query_customer_findAll_seconds_bucket[1m]))   
        Set Grafana thresholds using color-coded performance zones:
            Red: > 1000 ms
            Orange: 500–1000 ms
            Yellow: 100–500 ms
            Green: < 100 ms    
    */
    public List<Product> getProducts() {
        return meterRegistry().timer("db.query.product.findAll")
            .record(() -> jdbcTemplate.query("SELECT * FROM Product", productRowMapper()));
    }
    
    
    /*
    2. Using Spring Boot Autoconfiguration (Recommended)
        If you're using Spring Boot, it already provides autoconfiguration for Micrometer. Just add the dependency:   
            <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-core</artifactId>
        </dependency>
        Spring Boot will automatically create and configure a MeterRegistry bean.
    */
    
    /*
        For Specific Monitoring Systems
        3. Prometheus Example    
    */

    /**
     *
     * @param collectorRegistry
     * @return
     */
    /*
    @Bean
    public MeterRegistry meterRegistry(CollectorRegistry collectorRegistry) {
        return new PrometheusMeterRegistry(registry -> collectorRegistry);
    }    

    @Bean
    public PrometheusMeterRegistry prometheusMeterRegistry(Clock clock) {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("prometheus.descriptions", "true");
        configMap.put("prometheus.step", "1m");
        configMap.put("prometheus.scrape-timeout", "10s");

        // Example of a custom configuration implementation
        PrometheusConfig customConfig = new PrometheusConfig() {
            private final Map<String, String> properties = configMap;

            @Override
            public String get(String key) {
                return properties.get(key);
            }

            @Override
            public Duration step() {
                return Duration.parse(properties.get("prometheus.step"));
            }

            @Override
            public Duration scrapeTimeout() {
                String timeout = properties.get("prometheus.scrape-timeout");
                return timeout != null ? Duration.parse(timeout) : super.scrapeTimeout();
            }

            @Override
            public boolean descriptions() {
                return Boolean.parseBoolean(properties.getOrDefault("prometheus.descriptions", "false"));
            }
        };

        return new PrometheusMeterRegistry(customConfig, clock);
    }
    // */

    @Bean
    public Clock clock() {
        return Clock.SYSTEM;
    }
    
    /*
        4. Composite Registry (Multiple Systems)        
    */
    //@Bean
    public MeterRegistry compositeMeterRegistry() {
        CompositeMeterRegistry composite = new CompositeMeterRegistry();
        // Add other registries here
        // composite.add(new PrometheusMeterRegistry(...));
        return composite;
    }    
}

/*
Here's how to integrate Spring Boot with Grafana for monitoring:
1. Add Dependencies
Include necessary dependencies in pom.xml:
Code

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
    </dependency>
</dependencies>
2. Configure Spring Boot
Enable metrics exposure in application.properties or application.yml:
Code

management.endpoints.web.exposure.include=*
management.metrics.export.prometheus.enabled=true
3. Configure Prometheus
Configure Prometheus to scrape metrics from the Spring Boot application by adding a scrape job to your prometheus.yml:
Code

scrape_configs:
  - job_name: 'spring-boot'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']
4. Set Up Grafana
Install and run Grafana, for example, using Docker:
Code

docker run -d -p 3000:3000 grafana/grafana
Access Grafana at http://localhost:3000 and configure Prometheus as a data source.
5. Visualize Metrics
Import a pre-built dashboard or create custom visualizations in Grafana to monitor metrics such as: JVM memory usage, HTTP request rates and latencies, CPU usage, and Custom application metrics.
*/
