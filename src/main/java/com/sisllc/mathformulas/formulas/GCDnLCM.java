/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.formulas;

import java.util.Arrays;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class GCDnLCM {

    public static void main(String[] args) {
        int gcd1 = getGCD(10, 25);
        System.out.println("GCD of 10 and 25: " + gcd1);
        int gcd2 = getGCD(gcd1, 45);
        System.out.println("GCD of " + gcd1 + " and 45: " + gcd2);

        int lcm1 = getLCM(10, 25);
        System.out.println("LCM of 10 and 25: " + lcm1);
        int lcm2 = getLCM(lcm1, 45);
        System.out.println("LCM of " + lcm1 + " and 45: " + lcm2);

        int[] arr = {5, 15, 35, 50, 55};
        int lcm = 1;
        for (int i = 0; i < arr.length; i++) {
            lcm = getLCM(lcm, arr[i]);
        }
        System.out.println("The LCM of " + Arrays.toString(arr) + " is " + lcm);
        int gcd = getMax(arr);
        for (int i = 0; i < arr.length; i++) {
            gcd = getGCD(gcd, arr[i]);
        }
        System.out.println("The GCD of " + Arrays.toString(arr) + " is " + gcd);
    }

    private static int getMax(int[] arr) {
        int rv = arr[0];
        for (int i = 1; i < arr.length; i++) {
            rv = (rv > arr[i]) ? rv : arr[i];
        }

        return rv;
    }

    public static int getGCD(int a, int b) {
        int rv = (a > b) ? b : a;
        while (rv > 1) {
            if (a % rv == 0 && b % rv == 0) {
                break;
            }
            rv--;
        }
        return rv;
    }

    public static int getLCM(int a, int b) {
        int rv = (a > b) ? a : b;
        while (true) {
            if (rv % a == 0 && rv % b == 0) {
                break;
            }
            rv++;
        }
        return rv;
    }
}
