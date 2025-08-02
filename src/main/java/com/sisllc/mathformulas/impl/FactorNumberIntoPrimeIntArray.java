/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class FactorNumberIntoPrimeIntArray {

    private static final Logger log = LoggerFactory.getLogger(FactorNumberIntoPrimeIntArray.class);

    public int[] factorN(int n) {
        List<Integer> list = new ArrayList<>();

        // Print the number of 2s that divide n
        while (n % 2 == 0) {
            list.add(2);
            n /= 2;
        }
        // n must be odd at this point.  So we can skip one element (Note i = i +2)
        // and look at all the odd numbers 3, 5, 7, etc

        // the for loop can use either n or Math.sqrt(n)
        // for (int i = 3; i <= Math.sqrt(n); i += 2) {
        for (int i = 3; i <= n; i += 2) {
            // While i divides n, print i and divide n - print all the numbers
            // of i that devide n
            while (n % i == 0) {
                list.add(i);
                n /= i;
            }
        }

        // This condition is to handle the case when n is a prime number greater than 2
        if (n > 2) {
            list.add(n);
        }

        return convertListToIntArray(list);
    }

    private static int[] convertListToIntArray(List<Integer> list) {
        //System.out.println("convertListToIntArray list=" + list);
        int[] returnValue = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            returnValue[i] = list.get(i);
        }
        return returnValue;
    }

    public static void main(String[] args) {
        //prime numbers: 2, 3, 5, 7, 11, 13, 17, 19, 23 and 29.
        // 2    [2]
        // 3    [3]
        // 4    [2, 2]
        // 5    [5]
        // 6    [2, 3]
        // 7    [7]
        // 8    [2, 2, 2]
        // 10   [2, 5]
        int[] arr = {2, 3, 4, 5, 6, 7, 8, 9, 10, 315};
        FactorNumberIntoPrimeIntArray pgm = new FactorNumberIntoPrimeIntArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.print("\n first prime  of i:   " + arr[i] + "=: " + Arrays.toString(pgm.factorN(arr[i])));
            System.out.print("\n primeFactors  of i:  " + arr[i] + "=: " + Arrays.toString(pgm.factorN2Primes(arr[i])));
            System.out.print("\n primeFactors2 of i:  " + arr[i] + "=: " + Arrays.toString(pgm.factorNumberToPrimes(arr[i])));
            System.out.print("\n doFactoring of i:    " + arr[i] + "=: " + Arrays.toString(pgm.doFactoring(arr[i])));

            List<Integer> list = new ArrayList<>();
            System.out.print("\n primeFactors2 of i:  " + arr[i] + "=: " + pgm.factorRecursive(list, arr[i], 2));
        }
    }

    public int[] factorN2Primes(int number) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
                list.add(i); // prime factorN
                number /= i;
                i--;
            }
        }
        return convertListToIntArray(list);
    }

    public int[] factorNumberToPrimes(int number) {
        List<Integer> list = new ArrayList<>();

        for (int i = 2; i <= number; i++) {
            while (number > 0 && number % i == 0) {
                list.add(i); // prime factorN
                number /= i;
            }
        }
        return convertListToIntArray(list);
    }

    public int[] doFactoringRecursive(List<Integer> list, int number, int f) {
        return convertListToIntArray(factorRecursive(list, number, f));
    }

    public List<Integer> factorRecursive(List<Integer> list, int number, int f) {
        if (number < 2) {
            return list;
        }
        if (number % f == 0) {
            list.add(f);
            number /= f;
            f--;
        }

        return factorRecursive(list, number, ++f);
    }

    public int[] doFactoring(int n) {
        List<Integer> rv = new ArrayList();
        return convertListToIntArray(doFactoring(rv, n, 2));
    }

    public List<Integer> doFactoring(List<Integer> list, int n, int i) {
        if (n < 2) {
            return list;
        }
        if (n % i == 0) {
            list.add(i);
            n /= i;
            //find all numbers that devide i
            i--;
        }
        return doFactoring(list, n, ++i);
    }
}
