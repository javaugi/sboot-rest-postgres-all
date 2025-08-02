package com.fibonacci;

/**
 *
 * @author david
 *
 * Fibonacci series in Java
 *
 * In fibonacci series, next number is the sum of previous two numbers for
 * example 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 etc. The first two numbers of
 * fibonacci series are 0 and 1.
 *
 * There are two ways to write the fibonacci series program in java:
 *
 * Fibonacci Series without using recursion Fibonacci Series using recursion
 */
public class FibonacciSeries {

    public static void main(String[] args) {
        int count = 8;
        fibonacci(count);
        count = 12;
        fibonacci(count);
    }

    private static void fibonacci(int n) {
        System.out.print("\n Fibonacci Series with count " + n + "\n"); // printing

        int n1 = 0, n2 = 1, n3 = n1 + n2;

        for (int i = 0; i <= n; ++i) {
            if (i <= 1) {
                System.out.print("  " + i);
                continue;
            }
            n3 = n1 + n2;
            System.out.print("  " + n3);

            n1 = n2;
            n2 = n3;
        }
        System.out.print("\n Done"); // printing 0 and 1
    }
}
