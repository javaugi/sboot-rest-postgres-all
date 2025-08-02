package com.sisllc.mathformulas.impl;

/**
 * Euclidean Algorithm The Euclidean algorithm is a way to find the greatest
 * common divisor of two positive integers.
 *
 * Program to find GCD or HCF of two numbers GCD (Greatest Common Divisor) or
 * HCF (Highest Common Factor) of two numbers is the largest number that divides
 * both of them.
 *
 * GCD of two numbers is the largest number that divides both of them. A simple
 * way to find GCD is to factorize both numbers and multiply common factors.
 *
 * @author david
 *
 */
public class EuclideanAlgorithm {
    // find greatest common divisor/Highest Common Factor

    //The algorithm is based on below facts.
    // If we subtract smaller number from larger(we reduce larger number), GCD doesn’t change.
    // So if we keep subtracting repeatedly the larger of two, we end up with GCD.
    public static int gcd(int a, int b) {

        // base case or everything divides 0
        if (a == b || a == 0 || b == 0) {
            return Integer.max(a, b);
        }

        // a is greater
        if (a > b) {
            return gcd(a - b, b);
        }
        return gcd(a, b - a);
    }

    public static int gcd0(int a, int b) {
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

    //Now instead of subtraction, if we divide smaller number, the algorithm stops when we find remainder 0.
    public static int gcd2(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd2(b % a, a);
    }

    public static int gcd3(int a, int b) {
        int gcd = (a > b) ? b : a;
        while (gcd > 1) {
            if (a % gcd == 0 && b % gcd == 0) {
                break;
            }
            gcd--;
        }

        return gcd;
    }

    public static void main(String[] args) {
        System.out.println("Common Divisors:");
        System.out.println("   1  3 =? 1: " + gcd(1, 3));
        System.out.println("   5  7 =? 1: " + gcd(5, 7));
        System.out.println("  99  6 =? 3: " + gcd(99, 6));
        System.out.println(" 100 10 =? 10: " + gcd(100, 10));
        System.out.println(" 990 77 =? 11: " + gcd(990, 77));

        System.out.println("Common Divisors:");
        System.out.println("   1  3 =? 1: " + gcd0(1, 3));
        System.out.println("   5  7 =? 1: " + gcd0(5, 7));
        System.out.println("  99  6 =? 3: " + gcd0(99, 6));
        System.out.println(" 100 10 =? 10: " + gcd0(100, 10));
        System.out.println(" 990 77 =? 11: " + gcd0(990, 77));

        System.out.println("Common Divisors:");
        System.out.println("   1  3 =? 1: " + gcd2(1, 3));
        System.out.println("   5  7 =? 1: " + gcd2(5, 7));
        System.out.println("  99  6 =? 3: " + gcd2(99, 6));
        System.out.println(" 100 10 =? 10: " + gcd2(100, 10));
        System.out.println(" 990 77 =? 11: " + gcd2(990, 77));

        System.out.println("Common Divisors:");
        System.out.println("   1  1 =? 1: " + gcd3(1, 1));
        System.out.println("   3  3 =? 1: " + gcd3(3, 3));
        System.out.println("   1  3 =? 1: " + gcd3(1, 3));
        System.out.println("   5  7 =? 1: " + gcd3(5, 7));
        System.out.println("  99  6 =? 3: " + gcd3(99, 6));
        System.out.println(" 100 10 =? 10: " + gcd3(100, 10));
        System.out.println(" 990 77 =? 11: " + gcd3(990, 77));

        int[] arr1 = {1, 2, 8, 3};
        // Output : 24

        int[] arr2 = {2, 7, 3, 9, 4};
        //Output : 252

        int output = 0;
        for (int i = 0; i < arr1.length - 1; i++) {
            output = gcd(arr1[i], arr1[i + 1]);
            System.out.println("output=" + output);
        }
        System.out.println("output=" + output);
    }
}
