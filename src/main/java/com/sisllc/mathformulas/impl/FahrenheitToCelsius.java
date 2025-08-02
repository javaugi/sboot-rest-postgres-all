package com.sisllc.mathformulas.impl;

/**
 * Converting Fahrenheit to Celsius Deduct 32, then multiply by 5, then divide
 * by 9
 *
 *
 * @author david
 *
 */
public class FahrenheitToCelsius {

    public static void main(String[] args) {

        // body temperature
        double temp = 98.6;
        System.out.println("Fahrenheit: " + temp);

        temp = (temp - 32) * 5 / 9;
        System.out.println("Celsius: " + temp);
    }
}
