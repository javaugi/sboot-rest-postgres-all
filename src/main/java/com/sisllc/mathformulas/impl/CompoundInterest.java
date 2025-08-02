package com.sisllc.mathformulas.impl;

/**
 * http://www.javacodex.com/Math-Examples/
 *
 * @author david
 *
 * Compound interest can be calculated with the following formula: A = amount P
 * = principal R = rate n = years
 *
 * A=P(1+R)^n Java code: amount = principal * Math.pow(1+rate, years);
 *
 */
public class CompoundInterest {

    public static void main(String args[]) {
        double principal = 10000;
        int years = 10;
        double rate = .072;
        calc(years, principal, rate);

        System.out.println("Calculating 30 years mortgage at 5% for 150,000 ");
        principal = 200000;
        years = 30;
        rate = .0525;
        calc(years, principal, rate);

    }

    private static void calc(int years, double principal, double rate) {
        System.out.printf("Principle: %.2f \n", principal);
        System.out.println("Years: " + years);
        System.out.println("Interest rate: " + rate);
        System.out.println("Fromula: " + principal + " * (" + (1 + rate) + " to the power of " + years + ")");

        System.out.println("Total amount componded over the " + years + " years:");
        for (int i = 1; i <= years; i++) {
            double amount = principal * Math.pow(1 + rate, i);
            System.out.printf("Year " + i + ": %.2f \n", amount);
        }
    }

}
