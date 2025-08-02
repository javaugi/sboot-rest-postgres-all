/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.spring5.audit.AuditEvent;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class BaseKafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    protected String bootstrapServers;
    //"localhost:9092"

    @Value("${spring.kafka.schema-registry-url}")
    protected String schemaRegistryUrl;

    
    protected Map<String, Object> baseAvroProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        return props;
    }

    protected Map<String, Object> baseStringProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
    
    
}
/*
basically means: Kafka isn’t running, or your app can’t reach it on localhost:9092.

Let’s fix that step-by-step 👇

✅ What This Means
The Kafka AdminClient (used for broker metadata or topic operations) is trying to connect to localhost:9092 and fails because:

❌ Kafka isn't running

❌ It's running on a different port or host

❌ Docker network setup isn’t bridged correctly (if using containers)

✅ Step-by-Step Fix
🔍 1. Is Kafka running?
If you're using Kafka locally, start both Zookeeper and Kafka:

If installed via tarball:
# Start Zookeeper (if not using KRaft)
bin/zookeeper-server-start.sh config/zookeeper.properties

# In another terminal, start Kafka broker
bin/kafka-server-start.sh config/server.properties
If using Docker (recommended):
docker-compose up
Your docker-compose.yml should expose port 9092 and set up advertised listeners:

services:
  kafka:
    image: confluentinc/cp-kafka:7.4.0
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
🔍 2. Check bootstrap-servers config
In your Spring Boot app (application.yml or application.properties):

spring:
  kafka:
    bootstrap-servers: localhost:9092
Or in application.properties:

properties
spring.kafka.bootstrap-servers=localhost:9092
✅ Make sure it matches the actual port Kafka is running on.

🔍 3. Kafka is in Docker, App is Local?
Then localhost:9092 might not work. Try:

spring:
  kafka:
    bootstrap-servers: 127.0.0.1:9092
Or update KAFKA_ADVERTISED_LISTENERS in Docker to be:

KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://host.docker.internal:9092
🔍 4. Kafka Logs
Check Kafka logs to ensure it started correctly and is listening on the expected port. Look for:

[KafkaServer id=0] started (kafka.server.KafkaServer)
🧪 Optional: Test with Kafka CLI
Run:

bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
If this fails, it confirms Kafka isn’t reachable.
*/