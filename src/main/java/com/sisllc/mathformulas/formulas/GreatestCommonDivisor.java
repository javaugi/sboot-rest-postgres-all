/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.formulas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class GreatestCommonDivisor {

    private static final Logger log = LoggerFactory.getLogger(GreatestCommonDivisor.class);

    public static int gcd(int a, int b) {
        // Everything divides 0
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }

        // base case
        if (a == b) {
            return a;
        }

        // a is greater
        if (a > b) {
            return gcd(a - b, b);
        }
        return gcd(a, b - a);
    }

    public static void main(String[] args) {
        System.out.print("\n  2 and 10: " + gcd(2, 10));
        System.out.print("\n 15 and 25: " + gcd(15, 25));
        System.out.print("\n 36 and 60: " + gcd(36, 60));
        System.out.print("\n  8 and 16: " + gcd(8, 16));
        System.out.print("\n 98 and 56: " + gcd(98, 56));
    }
}
