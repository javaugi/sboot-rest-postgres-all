/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.kafka.support.converter.RecordMessageConverter;



//https://github.com/javaugi/sample-spring-kafka-microservices
//https://github.com/javaugi/kafka-microservices
@Configuration
public class KafkaSchemaRegistryConfig {
    
    /* TODO
    @Bean
    public RecordMessageConverter avroMessageConverter() {
        return new AvroSchemaMessageConverter();
    }

    @Bean
    public SchemaRegistryClient schemaRegistryClient(
        @Value("${spring.kafka.properties.schema.registry.url}") String endpoint) {
        return new CachedSchemaRegistryClient(endpoint, 100);
    }

    @Bean
    public RecordMessageConverter avroMessageConverter(SchemaRegistryClient schemaRegistryClient) {
        AvroSchemaRegistryClientMessageConverter converter = 
            new AvroSchemaRegistryClientMessageConverter(schemaRegistryClient);
        converter.setTypeMapper(new DefaultKafkaHeaderMapper());
        return converter;
    }    
    // */
}
