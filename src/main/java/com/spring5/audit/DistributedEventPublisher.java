/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring5.EventBusConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class DistributedEventPublisher {

    @Autowired
    private @Qualifier(EventBusConfig.MB_EVENT_BUS) MBassador<Object> eventBus;

    @Autowired
    private @Qualifier("stringKafkaTemplate") KafkaTemplate<String, String> kafkaTemplate;    

    @Autowired
    private ObjectMapper objectMapper;
    
    /*
    public DistributedEventPublisher(MBassador<Object> eventBus, ObjectMapper objectMapper,
            @Qualifier("stringKafkaTemplate") KafkaTemplate<String, String> kafkaTemplate) {
        this.eventBus = eventBus;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }    
    // */
    
    
    @PostConstruct
    public void setupForwarding() {
        // Forward all events to Kafka
        /*
        eventBus.subscribe((Object event) -> {
            try {
                String topic = event.getClass().getSimpleName();
                String payload = objectMapper.writeValueAsString(event);
                kafkaTemplate.send(topic, payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Event serialization failed", e);
            }
        });
        // */
    }
}