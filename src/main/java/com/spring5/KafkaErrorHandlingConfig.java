/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.TopicPartition;
import org.apache.kafka.common.TopicPartition; // ✅ CORRECT
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;


@Configuration
public class KafkaErrorHandlingConfig {

    @Bean
    public DeadLetterPublishingRecoverer dlqRecoverer(KafkaTemplate<?, ?> kafkaTemplate) {
        return new DeadLetterPublishingRecoverer(
                kafkaTemplate,
                (record, ex) -> new TopicPartition(record.topic() + ".DLT", record.partition()) // Use same partition
        );
    }

    @Bean
    public DefaultErrorHandler errorHandler(DeadLetterPublishingRecoverer dlqRecoverer) {
        var handler = new DefaultErrorHandler(dlqRecoverer);

        // Retry 3 times before sending to DLT
        handler.setRetryListeners((record, ex, deliveryAttempt)
                -> LoggerFactory.getLogger(KafkaErrorHandlingConfig.class)
                        .warn("Failed record {}, attempt {}", record, deliveryAttempt));

        return handler;
    }
}
