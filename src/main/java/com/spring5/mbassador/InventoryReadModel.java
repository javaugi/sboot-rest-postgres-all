/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

import net.engio.mbassy.listener.Handler;

/**
 *
 * @author javaugi
 */
public class InventoryReadModel {
    @Handler
    public void onInventoryUpdate(InventoryUpdatedEvent event) {
        // Update cached view
    }    
}
