/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

import net.engio.mbassy.listener.Handler;
import org.springframework.stereotype.Component;

/**
 *
 * @author javaugi
 */
@Component
public class GlobalErrorHandler {
    
    @Handler
    public void handleErrors(Throwable error) {
        System.err.println("Error in event handling: " + error.getMessage());
    }
}
/*
8. Advanced Configuration (Optional)

*/