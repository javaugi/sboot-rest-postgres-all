/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import com.spring5.audit.AuditEvent;
import com.spring5.kafkamicroservice.DocumentEvent;
import com.spring5.kafkamicroservice.TradeEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaTemplateConfig extends BaseKafkaConfig {
    
    @Value("${spring.kafka.admin.enabled:false}")
    private boolean kafkaAdminEnabled;

    @Primary
    @Bean(name = "auditEventKafkaTemplate")
    public KafkaTemplate<String, AuditEvent> auditEventKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(baseAvroProducerConfigs()));
    }

    @Bean(name = "tradeEventKafkaTemplate")
    public KafkaTemplate<String, TradeEvent> tradeEventKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(baseAvroProducerConfigs()));
    }

    @Bean(name = "docEventKafkaTemplate")
    public KafkaTemplate<String, DocumentEvent> docEventKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(baseAvroProducerConfigs()));
    }


    @Bean(name = "objectKafkaTemplate")
    public KafkaTemplate<Object, Object> objectKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(baseAvroProducerConfigs()));
    }

    @Bean(name = "stringKafkaTemplate")
    public KafkaTemplate<String, String> stringKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(baseStringProducerConfigs()));
    }
    
    /*@Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }    
    
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        return props;
    }
    
    @Primary
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Primary
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, AuditEvent> auditProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, AuditEvent> kafkaAuditTemplate() {
        return new KafkaTemplate<>(auditProducerFactory());
    } 
    // */
    
    // adding a KafkaAdmin (aka KafkaAdminClient) bean is super useful for creating topics programmatically 
    // or managing broker-related settings from your Spring Boot app.
    
    @Bean
    @ConditionalOnProperty(name = "spring.kafka.admin.enabled", havingValue = "true")
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);       // 3s timeout per request
        configs.put(AdminClientConfig.RETRIES_CONFIG, 2);                     // Only 1 retry
        configs.put(AdminClientConfig.RETRY_BACKOFF_MS_CONFIG, 1000);         // Wait 1s between retries
        System.out.println("✅ KafkaAdmin bean initialized!");
        return new KafkaAdmin(configs);
    }
    
    
    /* If you want Spring to create topics automatically at startup:
    @Bean
    public NewTopic auditEventsTopic() {
        return new NewTopic("audit-events", 3, (short) 1); // topicName, partitions, replicationFactor
    }
    // */

    @Bean
    public KafkaAdminClient kafkaAdminClient() {
        System.out.println("✅ KafkaAdminClient bean initialized!");
        return (KafkaAdminClient) KafkaAdminClient.create(kafkaAdmin().getConfigurationProperties());
    }  
    
    //  Alternative: Use AdminClient interface (recommended)
    //  It's better to depend on the AdminClient interface rather than the concrete KafkaAdminClient    
    @Primary
    @Bean
    public AdminClient adminClient() {
        System.out.println("✅ AdminClient bean initialized!");
        return AdminClient.create(kafkaAdmin().getConfigurationProperties());
    }

}

/*
good thanks. Another question how can I prevent admin client from keeping checking if server is available in a indefinite loop when the server is not runnnning


ChatGPT said:
Great follow-up — that infinite retry behavior from the Kafka AdminClient when Kafka is down can be super annoying during local dev or testing. Fortunately, we can tune or disable that retry behavior.

🎯 Problem
You’re seeing logs like this repeated forever:

css
Copy
Edit
[AdminClient clientId=adminclient-1] Connection to node -1 (localhost:9092) could not be established.
This happens because the Kafka client keeps retrying indefinitely by default.

✅ Solution: Configure Kafka AdminClient Timeouts + Retries
Here’s how you prevent infinite retries and fail fast.

🔧 Option 1: Set shorter timeouts + retry limits
In your custom KafkaAdmin bean (or Spring config):

java
Copy
Edit
@Bean
public KafkaAdmin kafkaAdmin(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configs.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);       // 3s timeout per request
    configs.put(AdminClientConfig.RETRIES_CONFIG, 1);                     // Only 1 retry
    configs.put(AdminClientConfig.RETRY_BACKOFF_MS_CONFIG, 1000);        // Wait 1s between retries
    return new KafkaAdmin(configs);
}
You can also set these in application.yml if you're not using a manual bean:

yaml
Copy
Edit
spring:
  kafka:
    properties:
      request.timeout.ms: 3000
      retries: 1
      retry.backoff.ms: 1000
Now, instead of retrying forever, it’ll try once or twice and give up quickly.

🧼 Optional: Disable KafkaAdmin completely (for dev)
If you don't need it during development:

yaml
Copy
Edit
kafka:
  admin:
    enabled: false
And in your config:

java
Copy
Edit
@ConditionalOnProperty(name = "kafka.admin.enabled", havingValue = "true")
@Bean
public KafkaAdmin kafkaAdmin(...) { ... }
🧪 Tip: Spring Profiles
Use different settings for dev vs prod:

yaml
Copy
Edit
# application-dev.yml
kafka:
  admin:
    enabled: false

# application-prod.yml
spring:
  kafka:
    properties:
      request.timeout.ms: 5000
      retries: 3
*/

/*

how do I disable Kafka adminClient when I run spring boot


ChatGPT said:
Great question — if you don't want Spring Boot to initialize Kafka's AdminClient (which is used for topic auto-creation or metadata inspection), you can disable it explicitly.

✅ Disable Kafka AdminClient in Spring Boot
Spring Boot creates a KafkaAdmin bean by default if Kafka is on the classpath. To disable it:

🛑 Option 1: Set this in application.yml
yaml
Copy
Edit
spring:
  kafka:
    admin:
      fail-fast: false
      auto-create: false
And most importantly:

yaml
Copy
Edit
spring:
  autoconfigure:
    exclude: org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
But ⚠️ this disables all Kafka auto-config — including KafkaTemplate. So usually...

✅ Option 2: Manually exclude just the KafkaAdmin bean
If you want to keep KafkaTemplate, but skip KafkaAdmin, just don’t define it, and also:

In application.yml or application.properties:
yaml
Copy
Edit
spring:
  kafka:
    admin:
      auto-create: false
Then in your config, avoid defining a KafkaAdmin bean manually.

✅ Option 3: Conditional Bean Setup
In your custom config class:

java
Copy
Edit
@Configuration
public class KafkaConfig {

    @Value("${kafka.admin.enabled:false}")
    private boolean kafkaAdminEnabled;

    @Bean
    @ConditionalOnProperty(name = "kafka.admin.enabled", havingValue = "true")
    public KafkaAdmin kafkaAdmin(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }
}
Then just set:

yaml
Copy
Edit
kafka:
  admin:
    enabled: false
🚫 Result
This prevents Spring from trying to create or access Kafka topics/admin metadata — avoiding errors like:

css
Copy
Edit
[AdminClient clientId=adminclient-1] Connection to node -1 could not be established
Let me know if you're using Dockerized Kafka, or switching between dev/test/prod and want conditional Kafka enable/disable — I can set that up too!
*/