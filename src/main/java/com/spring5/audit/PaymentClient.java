/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author javaugi
 */
public interface PaymentClient {
    CompletableFuture<Boolean> processPayment(String orderId);
    
}
