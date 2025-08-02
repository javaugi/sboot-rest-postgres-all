/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SchemaAwareTradePublisher {

    private final KafkaTemplate<String, TradeEvent> kafkaTemplate;    
    public SchemaAwareTradePublisher(@Qualifier("tradeEventKafkaTemplate") KafkaTemplate<String, TradeEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }    

    public void publishTradeEvent(Trade trade) {
        TradeEvent event = TradingEventPublisher.createTradeEventFromTrade(trade);

        kafkaTemplate.send("trading.trade.events", String.valueOf(trade.getId()), event);
    }
    
    public static GenericRecord getGenericRecord(TradeEvent event) {
        return new GenericRecord(event);
    }
    
}
