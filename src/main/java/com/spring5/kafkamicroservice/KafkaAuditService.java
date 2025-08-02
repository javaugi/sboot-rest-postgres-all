/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import com.spring5.audit.AuditService;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class KafkaAuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditService.class);
    private final AuditRepository auditRepository;

    public KafkaAuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @KafkaListener(topics = "${app.topics.audit-events}", groupId = "audit-service-group")
    public void handleAuditEvent(ConsumerRecord<String, KafkaAuditEvent> record, Acknowledgment ack) {
        try {
            KafkaAuditEvent event = record.value();
            logger.debug("Received audit event: {}", event);

            AuditEntry entry = new AuditEntry(
                event.getEntityId(),
                event.getEntityType(),
                event.getAction(),
                event.getUserId(),
                event.getTimestamp(),
                event.getMetadata()
            );

            auditRepository.save(entry);
            ack.acknowledge();
        } catch (Exception e) {
            logger.error("Error processing audit event: {}", e.getMessage());
            // Implement retry or dead-letter queue logic here
        }
    }

    @KafkaListener(topics = "${app.topics.trade-events}", groupId = "audit-service-trade-group")
    public void handleTradeEvent(ConsumerRecord<String, TradeEvent> record, Acknowledgment ack) {
        try {
            TradeEvent event = record.value();
            logger.debug("Received trade event for audit: {}", event);

            // Create additional audit entry for trade specifics
            AuditEntry entry = new AuditEntry(
                event.getTradeId(),
                EntityType.TRADE,
                AuditAction.UPDATE, // Default action for trade events
                "system-user",
                event.getTimestamp(),
                Map.of(
                    "instrument", event.getInstrumentId(),
                    "quantity", event.getQuantity(),
                    "price", event.getPrice()
                )
            );

            auditRepository.save(entry);
            ack.acknowledge();
        } catch (Exception e) {
            logger.error("Error processing trade audit event: {}", e.getMessage());
        }
    }    
}
