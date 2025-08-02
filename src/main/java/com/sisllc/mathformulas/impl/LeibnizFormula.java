package com.sisllc.mathformulas.impl;

/**
 * Leibniz Formula For PI Leibniz Formula for PI (aka Gregory Leibniz Series): 1
 * - 1/3 + 1/5 - 1/7 + 1/9 - 1/11 + 1/13 - 1/15 + 1/17 ... = pi/4
 *
 * @author david
 *
 */
public class LeibnizFormula {

    public static void main(String[] args) {

        int count = 999999999;
        double pi = 0;
        double denominator = 1;

        for (int x = 0; x < count; x++) {

            if (x % 2 == 0) {
                pi = pi + (1 / denominator);
            } else {
                pi = pi - (1 / denominator);
            }
            denominator = denominator + 2;
            System.out.println("pi=" + pi + "-denominator=" + denominator);
        }
        pi = pi * 4;
        System.out.println("final pi=" + pi);
    }
}
