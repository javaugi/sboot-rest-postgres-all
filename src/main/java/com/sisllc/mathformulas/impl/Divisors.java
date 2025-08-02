package com.sisllc.mathformulas.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * List Of Divisors A divisor is an integer that can be multiplied by some other
 * integer to produce n. The following java program list all of the divisors of
 * the numbers 1 to the given number.
 *
 * @author david
 *
 */
// 1: 1
// 2: 1 2
// 3: 1 3
// 4: 1 2 4
// 5: 1 5
// 6: 1 2 3 6
// 7: 1 7
// 8: 1 2 4 8
// 9: 1 3 9
// 10: 1 2 5 10
// 11: 1 11
// 12: 1 2 3 4 6 12
// 13: 1 13
// 14: 1 2 7 14
// 15: 1 3 5 15
// 25: 1 5 5 25
public class Divisors {

    public static void main(String[] args) {
        int N = 25; // Integer.parseInt(args[0]);

        System.out.println("" + getDivisors2(N));

        for (int i = 1; i <= N; i++) {
            System.out.print(i + ": ");
            for (int j = 1; j <= N; j++) {
                if (i % j == 0) {
                    System.out.print(j + " ");
                }
            }
            System.out.println("\n " + getDivisors2(i));
        }
    }

    public static List<Integer> getDivisors(int n) {
        List<Integer> rv = new ArrayList<>();

        return rv;
    }

    public static List<Integer> getDivisors2(int n) {
        List<Integer> rv = new ArrayList<>();

        for (int j = 1; j <= n; j++) {
            if (n % j == 0) {
                rv.add(j);
            }
        }
        return rv;
    }
}
