/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.rediscache;

/**
 *
 * @author javaugi
 */
public class PatientNotFoundException extends Exception {
    public PatientNotFoundException() {
        super();
    }
    public PatientNotFoundException(String message) {
        super(message);
    }
}
