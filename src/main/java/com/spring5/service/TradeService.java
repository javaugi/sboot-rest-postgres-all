/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.service;

import com.spring5.kafkamicroservice.Trade;
import com.spring5.kafkamicroservice.TradeRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class TradeService {
    
    @Autowired
    private TradeRepository tradeRepository;
    
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }
    
    public Trade createTrade(Trade trade) {
        // Add validation logic here
        return tradeRepository.save(trade);
    }    
    
    public List<Trade> getAllTradesByUserEmail(String userEmail) throws Exception {
        return tradeRepository.getAllTradesByUserEmail(userEmail);
    }
    
    public void addMoney(long userAccountId, BigDecimal amount) throws Exception{
        tradeRepository.addMoney(userAccountId, amount);
    }
    
    public void addMoneyByAccount(String userAccount, BigDecimal amount) throws Exception{
        tradeRepository.addMoneyByAccount(userAccount, amount);
    }

    public Trade updateTrade(Trade trade) {        
        return tradeRepository.save(trade);
    }
}
