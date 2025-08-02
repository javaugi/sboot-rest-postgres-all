package com.sisllc.mathformulas.impl;

/**
 * Sum Of Squares The sum of squares is defined as: int sum = 1² + 2² + 3² + n²
 *
 * Or another way: int sum = n(n+1)(2n+1)/6
 *
 * @author david
 *
 */
public class SumOfSquares {

    public static void main(String[] args) {

        int n = 10;

        System.out.println("sum1: " + sum(10));

        int sum2 = n * (n + 1) * ((2 * n) + 1) / 6;
        System.out.println("sum2: " + sum2);

        System.out.println("sumRecursive: " + sumRecursive(10));
    }

    public static int sum(int number) {
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            sum += (i * i);
        }
        return sum;
    }

    public static int sumRecursive(int number) {
        if (number < 1) {
            return 0;
        }
        return number * number + sumRecursive(number - 1);
    }
}
