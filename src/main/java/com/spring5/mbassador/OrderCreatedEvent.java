/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

/**
 *
 * @author javaugi
 */
public class OrderCreatedEvent {
    private String orderId;
    private boolean success;
    private String customerEmail;
    
    public OrderCreatedEvent(String orderId) { this.orderId = orderId; }
    public OrderCreatedEvent(String orderId, String email) { this.orderId = orderId; this.customerEmail = email;}

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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    
    
}
