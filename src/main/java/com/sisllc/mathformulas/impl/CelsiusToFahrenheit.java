package com.sisllc.mathformulas.impl;

/**
 * Converting Celsius to Fahrenheit Multiply by 9, then divide by 5, then add 32
 *
 * @author david
 *
 */
public class CelsiusToFahrenheit {

    public static void main(String[] args) {
        // Water boils at this temperature
        double temp = 100;
        System.out.println("Celsius: " + temp);

        temp = temp * 9 / 5 + 32;
        System.out.println("Fahrenheit: " + temp);

    }
}
