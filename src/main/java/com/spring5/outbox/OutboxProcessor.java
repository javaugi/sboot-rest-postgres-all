/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.outbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OutboxProcessor {
    @Autowired
    private OutboxRepository outboxRepository;
    @Autowired
    private EventPublisher eventPublisher;

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void processOutboxEvents() {
        List<Outbox> events = outboxRepository.findByProcessedFalse();
        for (Outbox event : events) {
            try {
                eventPublisher.publish(event);
                event.setProcessed(true);
                outboxRepository.save(event);
            } catch (Exception e) {
                // Log the error and retry later
            }
        }
    }
}
