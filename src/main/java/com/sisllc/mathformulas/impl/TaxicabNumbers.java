package com.sisllc.mathformulas.impl;

import java.util.PriorityQueue;

/**
 * Taxicab Numbers A taxicab number is a positive integer that can be expressed
 * as the sum of two positive cubes in more than one way.
 *
 * The first taxicab number is 1729:
 *
 * 1729 = 13 + 123 = 93 + 103
 *
 * @author david
 *
 */
public class TaxicabNumbers implements Comparable<TaxicabNumbers> {

    private final long sum;
    private final int i;
    private final int j;

    // create a new tuple (i, j, i^3 + j^3)
    public TaxicabNumbers(int i, int j) {
        this.sum = (long) i * i * i + (long) j * j * j;
        this.i = i;
        this.j = j;
    }

    @Override
    public int compareTo(TaxicabNumbers that) {
        if (this.sum < that.sum) {
            return -1;
        } else if (this.sum > that.sum) {
            return +1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return i + "^3 + " + j + "^3";
    }

    public static void main(String[] args) {

        int N = 50; // Integer.parseInt(args[0]);

        // initialize priority queue
        PriorityQueue<TaxicabNumbers> pq = new PriorityQueue<TaxicabNumbers>();
        for (int i = 1; i <= N; i++) {
            pq.add(new TaxicabNumbers(i, i));
        }

        // enumerate sums in ascending order, look for repeated sums
        int run = 1;
        TaxicabNumbers prev = new TaxicabNumbers(0, 0); // sentinel
        while (!pq.isEmpty()) {
            TaxicabNumbers s = pq.poll();

            // sum is same as previous one
            if (prev.sum == s.sum) {
                run++;
                if (run == 2) {
                    System.out.print(prev.sum + " = " + prev);
                }
                System.out.print(" = " + s);
            } else {
                if (run > 1) {
                    System.out.println();
                }
                run = 1;
            }
            prev = s;

            if (s.j < N) {
                pq.add(new TaxicabNumbers(s.i, s.j + 1));
            }
        }
        if (run > 1) {
            System.out.println();
        }
    }

}
