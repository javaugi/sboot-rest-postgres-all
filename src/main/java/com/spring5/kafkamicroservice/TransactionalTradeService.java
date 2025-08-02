/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.transaction.ChainedKafkaTransactionManager;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalTradeService {
    
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private DocumentRepository documentRepository;

    private final KafkaTemplate<String, DocumentEvent> kafkaDocTemplate;
    private final KafkaTemplate<String, TradeEvent> kafkaTemplate;
    private final JpaTransactionManager jpaTransactionManager;
    //private final ChainedKafkaTransactionManager<Object, Object> transactionManager;
    // this causes conflict with the transaction manager in MyApplication

    @Autowired
    public TransactionalTradeService(@Qualifier("docEventKafkaTemplate") KafkaTemplate<String, DocumentEvent> kafkaDocTemplate, 
            @Qualifier("tradeEventKafkaTemplate") KafkaTemplate<String, 
            TradeEvent> kafkaTemplate, JpaTransactionManager jpaTransactionManager) {
        this.kafkaDocTemplate = kafkaDocTemplate;
        this.kafkaTemplate = kafkaTemplate;
        this.jpaTransactionManager = jpaTransactionManager;
        /*
        this.transactionManager = new ChainedKafkaTransactionManager<>(
            new JpaTransactionManager(jpaTransactionManager.getEntityManagerFactory()),
            new KafkaTransactionManager<>(kafkaTemplate.getProducerFactory())
        );
        // */
    }

    @Transactional(transactionManager = "transactionManager")
    public void executeAtomicTrade(Trade trade, KafkaDocument document) {
        // 1. Save to DB
        tradeRepository.save(trade);
        documentRepository.save(document);
        
        // 2. Publish events transactionally
        TradeEvent tradeEvent = createTradeEvent(trade);
        DocumentEvent docEvent = createDocumentEvent(document);
        
        kafkaTemplate.send("trading.trade.events", String.valueOf(trade.getId()), tradeEvent);
        kafkaDocTemplate.send("trading.document.events", String.valueOf(document.getId()), docEvent);
        
        // 3. The commit will happen automatically if no exceptions
    }
    
    public static DocumentEvent createDocumentEvent(KafkaDocument document) {
        return new DocumentEvent(document.getId(), document.getText());
    }
    
    private TradeEvent createTradeEvent(Trade trade) {
        return TradingEventPublisher.createTradeEventFromTrade(trade);
    }

    /*
    @Bean
    public ChainedKafkaTransactionManager<Object, Object> transactionManager() {
        return transactionManager;
    }
    // */
}