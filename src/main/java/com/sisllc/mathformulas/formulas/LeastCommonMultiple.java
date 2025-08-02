/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.formulas;

import com.sisllc.mathformulas.impl.EuclideanAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class LeastCommonMultiple {

    private static final Logger log = LoggerFactory.getLogger(LeastCommonMultiple.class);

    public static void main(String[] args) {
        System.out.println("\n  1 and  2: " + lcm0(1, 2));
        System.out.println("\n 15 and 25: " + lcm0(15, 25));
        System.out.println("\n 15 and 25: " + lcm(15, 25));
        System.out.println("\n 15 and 25: " + lcm2(15, 25));

        System.out.println("\n 36 and 82: " + lcm0(36, 82));
        System.out.println("\n 36 and 82: " + lcm(36, 82));
        System.out.println("\n 36 and 82: " + lcm2(36, 82));
    }

    public static int lcm0(int a, int b) {
        //set lvm to the larger of the a and b
        int lcm = (a > b) ? a : b;

        //check to see if lcm can be divided by both
        while (true) {
            if (lcm % a == 0 && lcm % b == 0) {
                break;
            }
            //if not divisible by either increase lcm by = and keep looping
            ++lcm;
        }
        return lcm;
    }

    public static int lcm(int a, int b) {
        return (a * b) / EuclideanAlgorithm.gcd(a, b);
    }

    public static int lcm2(int a, int b) {
        return (a * b) / GreatestCommonDivisor.gcd(a, b);
    }
}
