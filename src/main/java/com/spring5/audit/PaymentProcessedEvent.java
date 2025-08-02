/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

/**
 *
 * @author javaugi
 */
public class PaymentProcessedEvent {
    
    private String orderId;
    private double amount;
    private boolean success;

    public PaymentProcessedEvent(String orderId){this.orderId = orderId;}
    public PaymentProcessedEvent(double amount) { this.amount = amount; }
    public PaymentProcessedEvent(String orderId, double amount, boolean success) { 
        this.amount = amount; 
        this.orderId = orderId;
        this.success = success;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    
}
