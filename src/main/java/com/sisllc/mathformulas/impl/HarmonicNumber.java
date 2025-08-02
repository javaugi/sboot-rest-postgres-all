package com.sisllc.mathformulas.impl;

/**
 * In mathematics, the n-th harmonic number is the sum of the reciprocals of the
 * first n natural numbers:
 *
 * Hn = 1 + 1/2 + 1/3 + 1/4 + 1/5 + .... + 1/n
 *
 * @author david
 *
 */
public class HarmonicNumber {

    public static void main(String[] args) {

        int x = 20; // Integer.parseInt(args[0]);
        // compute 1/1 + 1/2 + 1/3 + ... + 1/x
        double sum = 0.0;
        for (int i = 1; i <= x; i++) {
            // sum += 1.0 / i;
            sum = sum + (1.0 / i);
        }

        System.out.println(sum);
    }

}
