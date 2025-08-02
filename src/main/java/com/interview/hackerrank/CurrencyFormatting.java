/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author javaugi
 */
public class CurrencyFormatting {
    public static void main(String[] args) {
        printCurrency();
    }
    
    
    private static void printCurrency() {
        double payment = 12324.134;

        // Create custom Locale for India
        Locale indiaLocale = new Locale("en", "IN");
        
        Locale localeIndia = new Locale.Builder()
                .setLanguage("en")
                .setRegion("IN")
                .build();

        // Format for different locales
        NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat indiaFormat = NumberFormat.getCurrencyInstance(indiaLocale);
        NumberFormat indiaFormat2 = NumberFormat.getCurrencyInstance(localeIndia);
        NumberFormat chinaFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat franceFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);

        // Format the payment
        String us = usFormat.format(payment);
        String india = indiaFormat.format(payment);
        String india2 = indiaFormat2.format(payment);
        String china = chinaFormat.format(payment);
        String france = franceFormat.format(payment);

        // Print results
        System.out.println("US: " + us);
        System.out.println("India: " + india);
        System.out.println("India 2: " + india2);
        System.out.println("China: " + china);
        System.out.println("France: " + france);        
    }
}
