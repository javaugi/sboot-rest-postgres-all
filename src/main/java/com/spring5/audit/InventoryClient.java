/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author javaugi
 */
public interface InventoryClient {
    CompletableFuture<Boolean> reserveItems(String orderId, List<Order> items);
    void cancelReservation(String orderId);
}