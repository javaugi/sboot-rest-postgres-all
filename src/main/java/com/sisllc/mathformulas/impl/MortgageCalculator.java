package com.sisllc.mathformulas.impl;

/**
 * Mortgage Calculator This is a basic mortgage calculator. It calculates your
 * monthy payment.
 *
 * @author david
 *
 */
public class MortgageCalculator {

    public static void main(String args[]) {

        print(30, 200000, 6);

    }

    private static void print(int term, double principal, double rate) {
        // Term in years
        System.out.print("Term (years): " + term + "-principal=" + principal + "-rate=" + rate);
        //yearly rate (5.25 = 0.0525)
        rate = rate / 100 / 12;
        // Term in months
        term = term * 12;
        double payment = (principal * rate) / (1 - Math.pow(1 + rate, -term));
        System.out.println("\n calc Monthly Payment: " + payment);
        // round to two decimals
        payment = (double) Math.round(payment * 100) / 100;

        System.out.println("\n Monthly Payment: " + payment);
        System.out.println(" Total Payment: " + payment * term);
    }

    public double printPayments(int term, double principal, double rate) {
        rate = rateInMonths(rate);
        term = termInMonths(term);
        double payment = (principal * rate) / (1 - Math.pow(1 + rate, -term));
        payment = (double) Math.round(payment * 100) / 100;
        return payment;
    }

    public int termInMonths(int term) {
        return term * 12;
    }

    public double rateInMonths(double rate) {
        return rate / 100 / 12;
    }

}
