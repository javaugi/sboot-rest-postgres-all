package com.sisllc.mathformulas.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class FibonacciRecursive {

    private static final Logger log = LoggerFactory.getLogger(FibonacciRecursive.class);

    public long fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public String printFib(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            sb.append(fib(i) + ", ");
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        FibonacciRecursive fib = new FibonacciRecursive();
        log.info(fib.printFib(8));
    }
}
