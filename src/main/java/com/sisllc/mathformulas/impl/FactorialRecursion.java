package com.sisllc.mathformulas.impl;

/**
 * A factorial of a non-negative integer n, denoted by n!, is the product of all
 * positive integers less than or equal to n.
 *
 * Example: 5! = 5 * 4 * 3 * 2 * 1 = 120
 *
 * @author david
 *
 */
public class FactorialRecursion {

    public static void main(String args[]) {
        int x = 5; // Integer.parseInt(args[0]);
        int result = fact(x);
        System.out.println("The factorial of " + x + ": " + result);

        x = 8; // Integer.parseInt(args[0]);
        result = fact(x);
        System.out.println("The factorial of " + x + ": " + result);
    }

    static int fact(int b) {
        System.out.println("factoring " + b);
        if (b <= 1) {
            return 1;
        } else {
            return b * fact(b - 1);
        }
    }
}
