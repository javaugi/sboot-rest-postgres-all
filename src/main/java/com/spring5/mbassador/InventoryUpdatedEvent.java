/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

/**
 *
 * @author javaugi
 */
public class InventoryUpdatedEvent {

    private String productId;
    private int newQuantity;
    
    public InventoryUpdatedEvent(String productId, int newQuantity) {
        this.productId = productId;
        this.newQuantity = newQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(int newQuantity) {
        this.newQuantity = newQuantity;
    }

}
