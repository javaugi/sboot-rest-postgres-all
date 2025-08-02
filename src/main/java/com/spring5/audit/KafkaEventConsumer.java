 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring5.EventBusConfig;
import com.spring5.mbassador.OrderCreatedEvent;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventConsumer {

    private final @Qualifier(EventBusConfig.MB_EVENT_BUS) MBassador<Object> eventBus;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "OrderCreatedEvent")
    public void consumeOrderCreated(String message) throws IOException {
        OrderCreatedEvent event = objectMapper.readValue(message, OrderCreatedEvent.class);
        eventBus.publish(event);
    }
}
