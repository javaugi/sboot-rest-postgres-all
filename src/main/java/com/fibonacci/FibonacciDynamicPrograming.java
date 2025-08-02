/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.fibonacci;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * https://medium.com/@codingfreak/top-50-dynamic-programming-practice-problems-4208fed71aa3
 * https://www.geeksforgeeks.org/dynamic-programming/
 *
 * Dynamic Programming
 *
 * Dynamic Programming is mainly an optimization over plain recursion. Wherever
 * we see a recursive solution that has repeated calls for same inputs, we can
 * optimize it using Dynamic Programming. The idea is to simply store the
 * results of subproblems, so that we do not have to re-comupute them when
 * needed later. This simple optimization reduces time complexities from
 * exponential to polynomial. For example, if we write simple recursive solution
 * for Fibonacci Numbers, we get exponential time complexity and if we optimize
 * it by storing solutions of subproblems, time complexity reduces to linear.
 *
 * Dynamic programming (usually referred to as DP ) is a very powerful technique
 * to solve a particular class of problems. It demands very elegant formulation
 * of the approach and simple thinking and the coding part is very easy. The
 * idea is very simple, If you have solved a problem with the given input, then
 * save the result for future reference, so as to avoid solving the same problem
 * again.. shortly 'Remember your Past' :) . If the given problem can be broken
 * up in to smaller sub-problems and these smaller subproblems are in turn
 * divided in to still-smaller ones, and in this process, if you observe some
 * over-lapping subproblems, then its a big hint for DP. Also, the optimal
 * solutions to the subproblems contribute to the optimal solution of the given
 * problem
 *
 * There are two ways of doing this.
 *
 * 1.) Top-Down : Start solving the given problem by breaking it down. If you
 * see that the problem has been solved already, then just return the saved
 * answer. If it has not been solved, solve it and save the answer. This is
 * usually easy to think of and very intuitive. This is referred to as
 * Memoization.
 *
 * 2.) Bottom-Up : Analyze the problem and see the order in which the
 * sub-problems are solved and start solving from the trivial subproblem, up
 * towards the given problem. In this process, it is guaranteed that the
 * subproblems are solved before solving the problem. This is referred to as
 * Dynamic Programming.
 *
 * Note that divide and conquer is slightly a different technique. In that, we
 * divide the problem in to non-overlapping subproblems and solve them
 * independently, like in mergesort and quick sort.
 *
 * Complementary to Dynamic Programming are Greedy Algorithms which make a
 * decision once and for all every time they need to make a choice, in such a
 * way that it leads to a near-optimal solution. A Dynamic Programming solution
 * is based on the principal of Mathematical Induction greedy algorithms require
 * other kinds of proof.
 *
 * Cold War between Systematic Recursion and Dynamic programming
 *
 * Recursion uses the top-down approach to solve the problem i.e. It begin with
 * core(main) problem then breaks it into subproblems and solve these
 * subproblems similarily. In this approach same subproblem can occur multiple
 * times and consume more CPU cycle ,hence increase the time complexity. Whereas
 * in Dynamic programming same subproblem will not be solved multiple times but
 * the prior result will be used to optimise the solution. eg. In fibonacci
 * series :-
 *
 *
 * Fib(4) = Fib(3) + Fib(2)
 *
 * = (Fib(2) + Fib(1)) + Fib(2)
 *
 * l"> =((Fib(1) + Fib(0)) + Fib(1)) + Fib(2)
 *
 * =((Fib(1) + Fib(0)) + Fib(1)) + (Fib(1) + Fib(0))
 *
 * Here, call to Fib(1) and Fib(0) is made multiple times.In the case of
 * Fib(100) these calls would be count for million times. Hence there is lots of
 * wastage of resouces(CPU cycles & Memory for storing information on stack).
 *
 * In dynamic Programming all the subproblems are solved even those which are
 * not needed, but in recursion only required subproblem are solved. So solution
 * by dynamic programming should be properly framed to remove this ill-effect.
 *
 * Fibonacci solve this problem:
 *
 * There are n stairs, you can clime one stair or two-satairs at a time. How
 * many way are there to clime the stairs?
 *
 * There are n -1 ways to clime the n-stairs for one-stair at a time
 *
 * There are n -2 ways to clime the n-stairs for two-stairs at a time
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class FibonacciDynamicPrograming {

    private static final Logger log = LoggerFactory.getLogger(FibonacciDynamicPrograming.class);

    public static void main(String[] args) {
        FibonacciDynamicPrograming fib = new FibonacciDynamicPrograming();
        fib.printFib1(8);
        System.out.println("\n printFib2 \n ");
        fib.printFib2(8);
        System.out.println("\n fib2 \n ");
        fib2(8);
        System.out.println("\n printFib3 \n ");
        System.out.println(fib.printFib3(8));

        System.out.println("\n fib3 \n  ");
        System.out.println(fib.fib3(8));

        System.out.println("\n fib3 again \n  ");
        for (int i = 0; i <= 8; i++) {
            System.out.print(fib.fib3(i) + ", ");
        }
        System.out.println("\n fib4 \n  ");
        System.out.println(fib.fib4(8));
        System.out.println("\n fib5 \n  ");
        System.out.println(fib.fib5(8));
    }

    private void printFib1(int n) {
        for (int i = 0; i <= n; i++) {
            long total = 0;
            for (int j = 1; j <= i; j++) {
                total = fib(j);
            }
            System.out.print(total + ", ");
        }
    }

    public void printFib2(int n) {
        StringBuilder sb = new StringBuilder();
        // for (int i = 0; i <= n; i++) {
        for (int i = 0; i <= n; i++) {
            System.out.print(fib(i) + ", ");
        }
    }

    public String printFib3(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            sb.append(fib(i) + ", ");
        }

        return sb.toString().trim();
    }

    public long fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    private static void fib2(int n) {
        long n1 = 0;
        long n2 = 1;
        long n3 = n1 + n2;

        for (int i = 0; i <= n; i++) {
            if (i <= 1) {
                System.out.print(i + ", ");
                continue;
            }

            n3 = n1 + n2;
            System.out.print(n3 + ", ");

            n1 = n2;
            n2 = n3;
        }
    }

    private static Map<Integer, Integer> CACHE = new HashMap<>();
    private static Map<Integer, BigInteger> CACHEBi = new HashMap<>();

    public int fib3(int n) {
        return (n <= 1) ? n : (fib3(n - 1) + fib3(n - 2));
    }

    public int fib4(int n) {
        return CACHE.computeIfAbsent(n, k -> (k <= 1 ? k : (fib4(k - 1) + fib4(k - 2))));
    }

    private static Map<Integer, BigInteger> CACHEB = new HashMap<>();

    public BigInteger fib5(int n) {
        return CACHEB.computeIfAbsent(n, k -> (k <= 2 ? new BigInteger(k.toString()) : (fib5(k - 1).add(fib5(k - 2)))));
    }
}
