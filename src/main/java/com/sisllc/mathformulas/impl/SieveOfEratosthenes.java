package com.sisllc.mathformulas.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Sieve Of Eratosthenes This java example shows the calculation of prime
 * numbers with the Sieve of Eratosthenes.
 *
 *
 * In mathematics, the sieve of Eratosthenes is a simple, ancient algorithm for
 * finding all prime numbers up to any given limit.
 *
 * It does so by iteratively marking as composite (i.e., not prime) the
 * multiples of each prime, starting with the first prime number, 2. The
 * multiples of a given prime are generated as a sequence of numbers starting
 * from that prime, with constant difference between them that is equal to that
 * prime.[1] This is the sieve's key distinction from using trial division to
 * sequentially test each candidate number for divisibility by each prime.[2]
 *
 * @author david
 *
 */
public class SieveOfEratosthenes {

    public static void init(boolean[] flags) {
        flags[0] = false;
        flags[1] = false;
        // 0 and 1 are not prime and set all other as prime
        for (int i = 2; i < flags.length; i++) {
            flags[i] = true;
        }
    }

    public static List<Integer> calculate(int n) {
        List<Integer> returnValue = new ArrayList<Integer>();

        boolean[] prime = new boolean[n + 1];
        init(prime);

        //starts with 2 
        for (int i = 2; i < n; i++) {
            if (prime[i]) {
                returnValue.add(i);
                for (int j = i; j * i <= n; j++) {
                    prime[i * j] = false;
                }
            }
        }
        return returnValue;
    }

    public static void main(String[] args) {
        List<Integer> list = calculate(100);
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
