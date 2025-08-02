package com.sisllc.mathformulas.impl;

/**
 * The first two numbers in the Fibonacci sequence are 0 and 1, and each
 * subsequent number is the sum of the previous two.
 *
 * @author david
 *
 */
public class FibonacciRecursion {

    private static long fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    private static void printFib(int n) {
        // for (int i = 0; i <= n; i++) {
        for (int i = 1; i <= n; i++) {
            long total = 0;
            for (int j = 1; j <= i; j++) {
                total = fib(j);
            }
            System.out.print(total + ", ");
        }
        System.out.println("...");
    }

    public static void main(String[] args) {
        printFib(8);
        printFib(12);
    }
}
