/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import java.time.Instant;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TradingEventPublisher {

    private final KafkaTemplate<String, TradeEvent> kafkaTemplate;
    private final String tradeEventsTopic;
    private final String fileEventsTopic;
    private final String auditEventsTopic;
    
    @Autowired
    private TradeRepository tradeRepository;
    private FileStorageService fileStorageService;

    @Autowired
    public TradingEventPublisher(@Qualifier("tradeEventKafkaTemplate") KafkaTemplate<String, TradeEvent> kafkaTemplate,
                               @Value("${app.topics.trade-events}") String tradeEventsTopic,
                               @Value("${app.topics.file-events}") String fileEventsTopic,
                               @Value("${app.topics.audit-events}") String auditEventsTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.tradeEventsTopic = tradeEventsTopic;
        this.fileEventsTopic = fileEventsTopic;
        this.auditEventsTopic = auditEventsTopic;
    }

    public void publishTradeEvent(Trade trade) {
        TradeEvent event = createTradeEventFromTrade(trade);

        kafkaTemplate.send(tradeEventsTopic, String.valueOf(trade.getId()), event);
        publishAuditEvent(String.valueOf(trade.getId()), EntityType.TRADE, AuditAction.CREATE);
    }
    
    public static TradeEvent createTradeEventFromTrade(Trade trade) {
        return new TradeEvent(java.util.UUID.randomUUID().toString(),
            Instant.now(),
            "trading-system",
            String.valueOf(trade.getId()),
            trade.getInstrumentId(),
            trade.getQuantity(),
            trade.getPrice(),
            trade.getCurrency(),
            trade.getDirection()
        );
    }
    

    public void publishFileEvent(Trade trade, String filePath, FileOperation operation) {
        FileStorageEvent event = new FileStorageEvent(
            java.util.UUID.randomUUID().toString(),
            Instant.now(),
            "trading-system",
            "file-" + java.util.UUID.randomUUID().toString(),
            String.valueOf(trade.getId()),
            operation,
            filePath,
            "trade-confirmation"
        );

        TradeEvent tEvent = new TradeEvent(java.util.UUID.randomUUID().toString(),
            Instant.now(), "trade-confirmation", event.getTradeId());
        
        kafkaTemplate.send(fileEventsTopic, String.valueOf(trade.getId()), tEvent);
        publishAuditEvent(event.getFileId(), EntityType.FILE, AuditAction.valueOf(operation.name()));
    }

    private void publishAuditEvent(String entityId, EntityType entityType, AuditAction action) {
        KafkaAuditEvent event = new KafkaAuditEvent(java.util.UUID.randomUUID().toString(),
            Instant.now(),
            "trading-system",
            entityId,
            entityType,
            action,
            "system-user",
            Map.of("system", "trading-engine")
        );
        
        TradeEvent tEvent = new TradeEvent("trading-system", event.getEventId());
        kafkaTemplate.send(auditEventsTopic, entityId, tEvent);
    }

    @Transactional(transactionManager = "kafkaTransactionManager")
    public void executeTradeAndPublishEvents(Trade trade, MultipartFile confirmationFile) {
        // 1. Save trade to database
        tradeRepository.save(trade);
        
        // 2. Publish trade event
        publishTradeEvent(trade);
        
        // 3. Store file and publish event
        String filePath = fileStorageService.store(confirmationFile);
        publishFileEvent(trade, filePath, FileOperation.UPLOAD);
    }
}