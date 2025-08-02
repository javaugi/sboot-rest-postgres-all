/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import java.math.BigDecimal;
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
public class GenericRecord {

    private String tradeId;
    private String instrumentId;
    private BigDecimal quantity;
    private BigDecimal price;
    private String currency;
    private TradeDirection direction;

    public GenericRecord(TradeEvent event) {
        this.tradeId = event.getTradeId();
        this.instrumentId = event.getInstrumentId();
        this.quantity = event.getQuantity();
        this.price = event.getPrice();
        this.currency = event.getCurrency();
        this.direction = event.getDirection();

    }

}
