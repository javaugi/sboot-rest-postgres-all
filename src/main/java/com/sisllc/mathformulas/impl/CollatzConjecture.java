package com.sisllc.mathformulas.impl;

/**
 *
 * @author david
 *
 * The Collatz conjecture is a conjecture in mathematics named after Lothar
 * Collatz
 *
 * Take any natural number n. If n is even, divide it by 2 to get n / 2. If n is
 * odd, multiply it by 3 and add 1 to obtain 3n + 1. Repeat the process
 * indefinitely. The conjecture is that no matter what number you start with,
 * you will always eventually reach 1.
 *
 */
public class CollatzConjecture {

    public static void main(String[] args) {
        int n = 10; // Integer.parseInt(args[0]);
        collatz(n);
        System.out.println();

        n = 20; // Integer.parseInt(args[0]);
        collatz(n);
        System.out.println();
    }

    public static void collatz(int n) {
        System.out.print(n + " ");
        if (n == 1) {
            return;
        } else if (n % 2 == 0) {
            collatz(n / 2);
        } else {
            collatz(3 * n + 1);
        }
    }
}
