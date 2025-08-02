/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author javaugi
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeEvent extends BaseEvent {

    private String tradeId;
    private String instrumentId;
    private BigDecimal quantity;
    private BigDecimal price;
    private String currency;
    private TradeDirection direction;

    public TradeEvent(String sourceSystem, String instrumentId) {
        super(java.util.UUID.randomUUID().toString(),
            Instant.now(), sourceSystem);
        this.instrumentId = instrumentId;
    }    

    public TradeEvent(String id, Instant timestamp, String sourceSystem, String tradeId) {
        super(id, timestamp, sourceSystem);
        this.tradeId = tradeId;
    }    
    
    public TradeEvent(String id, Instant timestamp, String sourceSystem, String tradeId, String instrumentId,
            BigDecimal quantity, BigDecimal price, String currency, TradeDirection direction) {
        super(id, timestamp, sourceSystem);
        this.tradeId = tradeId;
        this.instrumentId = instrumentId;
        this.quantity = quantity;
        this.price = price;
        this.currency = currency;
        this.direction = direction;
    }

    public TradeEvent(String eventId, Instant timestamp, String tradeId, String instrumentId,
            BigDecimal quantity, BigDecimal price, String currency, TradeDirection direction) {
        super(eventId, timestamp, null);
        this.tradeId = tradeId;
        this.instrumentId = instrumentId;
        this.quantity = quantity;
        this.price = price;
        this.currency = currency;
        this.direction = direction;
    }
   
}
