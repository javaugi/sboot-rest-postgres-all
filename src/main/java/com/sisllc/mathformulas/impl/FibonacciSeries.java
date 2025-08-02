package com.sisllc.mathformulas.impl;

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
 *
 * Q: There are stairs, each time one can clime 1 or 2, how many different ways
 * to clibe the stairs
 *
 * Step 1. Finding the relationships before n and n - 1. To get n there are two
 * ways, one 1-stair from n-1 or 2-stairs from n-2. If the f(n) is the number of
 * ways to clime to n then f(n) = f(n-1) + f(n-2)
 *
 * Make sure the start condition is correct:
 *
 * f(0) = 0; f(1) = 1
 *
 * Dynamic programing - is a technique for solving the problems with the
 * following properties
 *
 * 1. An instance is solved using the samll instances
 *
 * 2. The solution for a smaller instance might be needed multiple times
 *
 * 3. The solutions to small instances is stored in a table, so that each
 * smaller instance is solved only once
 *
 * 4. attional space is used to save time
 *
 */
public class FibonacciSeries {

    public static int[] A = new int[1000];

    public static int f3(int n) {
        //System.out.println("n=" + n);
        if (n <= 1) {
            //System.out.print(", " + n);
            return n;
        }

        if (A[n] > 0) {
            //System.out.print(", " + A[n]);
            return A[n];
        } else {
            A[n] = f3(n - 1) + f3(n - 2);
        }

        System.out.print(", " + A[n]);
        return A[n];
    }

    public static void main(String[] args) {
        int count = 8;
        fibonacci(count);
        count = 12;
        fibonacci(count);
        count = 2;
        fibonacci(count);

        System.out.print("\n\n f3(n) ...");
        count = 12;
        f3(count);
        //System.out.print("\n " + f3(count));

        int n = 12;
        System.out.print("\n\n fib(12) ...");
        for (int i = 0; i <= n; ++i) {
            System.out.print("  " + fib(i));
        }
        System.out.print("\n Done"); // printing 0 and 1
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

    public static long fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
