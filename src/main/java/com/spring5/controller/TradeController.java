/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import com.spring5.kafkamicroservice.Trade;
import com.spring5.service.TradeService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javaugi
 */
@RestController
@RequestMapping("/trading/traders")
public class TradeController {
    
    @Autowired
    private TradeService tradeService;
    
    @GetMapping
    public ResponseEntity<List<Trade>> getAllTrades() {
        return ResponseEntity.ok(tradeService.getAllTrades());
    }
    
    //@PostMapping("/create-trade")
    //@PostMapping("/create-trade-by-account")
    //@PostMapping(value = "/trade", consumes = "application/vnd.create-trade+json")
    //@PostMapping(value = "/trade", params = "action=create")    
    @PostMapping("/create-trade")
    public ResponseEntity<Trade> createTrade(@RequestBody Trade trade) {
        Trade createdTrade = tradeService.createTrade(trade);
        return new ResponseEntity<>(createdTrade, HttpStatus.CREATED);
    }
    
    @PutMapping("/add-money")
    public void addMoneyByUserAccountId(@PathVariable long userAccountId, @PathVariable BigDecimal amount) {
        try{
            tradeService.addMoney(userAccountId, amount); 
            throw new NoException("OK");
        } catch(Exception ex) {
            throw new NotAcceptableException("Not Acceptable");
        }
    }

    @PutMapping("/{userAccount}/{amount}")
    public void addMoneyByUserAccountNumber(@PathVariable String userAccountNumber, @PathVariable BigDecimal amount) {
        try{
            tradeService.addMoneyByAccount(userAccountNumber, amount);     
            throw new NoException("OK");
        } catch(Exception ex) {
            throw new NotAcceptableException("Not Acceptable");
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    public class NoException extends RuntimeException {
        public NoException(String message) {
            super(message);
        }
    }
    

    @PutMapping("/update-trade")
    public ResponseEntity<Trade> updateTrade(org.springframework.http.RequestEntity<Trade> request) {
        Trade trade = tradeService.updateTrade(request.getBody());
        if (trade != null) {
            return ResponseEntity.ok(trade);
        } else {
            throw new ResourceNotFoundException("No trades found");
        }
    }    

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<Trade> getAllTradesByUserEmail(@PathVariable String userEmai) {
        try{
            List<Trade> trades = tradeService.getAllTradesByUserEmail(userEmai);
            if (trades == null || trades.isEmpty()) {
                throw new ResourceNotFoundException("No trades found");
            }
            return trades;
        }catch(Exception ex) {
            throw new ResourceNotFoundException("No trades found");
        }
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public class RequestTimeOutException extends RuntimeException {
        public RequestTimeOutException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public class NotAcceptableException extends RuntimeException {
        public NotAcceptableException(String message) {
            super(message);
        }
    }
    // Add proper exception handling
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RequestTimeOutException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ResponseEntity<?> handleNotFound(RequestTimeOutException ex) {
        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler(NotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<?> notAcceptable(NotAcceptableException ex) {
        return ResponseEntity.noContent().build();
    }
    
}
