package com.sisllc.mathformulas.impl;

public class FibonacciNumbers {

    public static void main(String[] args) {
        System.out.println("Printing 8");
        fib(8);
        System.out.println("\n Printing 12");
        fib(12);
    }

    private static void fib(int n) {
        long n1 = 0;
        long n2 = 1;
        long n3;

        // for (int i = 1; i <= n; i++) {
        for (int i = 1; i <= n; i++) {
            if (i <= 1) {
                System.out.print(" " + i);
                continue;
            }

            n3 = n1 + n2;
            System.out.print(", " + n3);

            n1 = n2;
            n2 = n3;
        }
    }
}
