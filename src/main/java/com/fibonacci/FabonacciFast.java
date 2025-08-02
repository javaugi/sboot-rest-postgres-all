/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.fibonacci;

import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class FabonacciFast {

    private static final Logger log = LoggerFactory.getLogger(FabonacciFast.class);

    private static BigInteger[] answers;
    private static BigInteger one;
    private static BigInteger zero;

    public static void main(String[] args) {

        int n;
        long time, newTime;
        BigInteger answer;

        // Prompt the user
        System.out.println("Type a positive integer.");
        try {
            n = 12;

            zero = new BigInteger("0");
            one = new BigInteger("1");

            // Initializing answers
            answers = new BigInteger[n];
            answers[0] = new BigInteger("1");
            answers[1] = new BigInteger("1");
            for (int i = 2; i < n; i++) {
                answers[i] = new BigInteger("0");
            }

            time = System.currentTimeMillis();
            answer = fastFibonacci(n);
            newTime = System.currentTimeMillis();

            System.out.println("The " + n + "th Fibonacci number is " + answer + "-answers=" + answers);
            System.out.println("It took " + (newTime - time) + " milliseconds to compute it.");
            for (BigInteger bi : answers) {
                System.out.println(bi + ", ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    } // end of main

    // The "fast" Fibonacci computation function. This is also recursive, but
    // it consults the table of answers *before* making the recursive call, thus
    // saving a huge amount of time.
    public static BigInteger fastFibonacci(int n) {
        // answers[0] is initialized to 1.
        if ((n == 1) || (n == 2)) {
            return answers[0];
        }

        // If answers[n-1] is non-zero, then the answer has already been
        // computed, so just return the value in this slot; no need to
        // make a recursive call.
        if (answers[n - 1].compareTo(zero) != 0) {
            return answers[n - 1];
        }

        // Otherwise, find fibonacci(n-1), either by
        // looking it up in answers[n-2] or computing it for the first time
        if (answers[n - 2].compareTo(zero) == 0) {
            answers[n - 2] = fastFibonacci(n - 1);
        }

        // Find fibonacci(n-2), either by
        // looking it up in answers[n-3] or computing it for the first time
        if (answers[n - 3].compareTo(zero) == 0) {
            answers[n - 3] = fastFibonacci(n - 2);
        }

        return answers[n - 2].add(answers[n - 3]);
    }

}
