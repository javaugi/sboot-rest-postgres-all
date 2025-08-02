package com.sisllc.mathformulas.impl;

import com.spring5.ProjectTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class FibonacciRecursiveTest extends ProjectTest {

    @Test
    public void whenFib0Then0() {
        FibonacciRecursive fib = new FibonacciRecursive();
        log.debug("\n whenFib0Then0 fib(0) {}", fib.fib(0));
        Assert.assertTrue(0 == fib.fib(0));
    }

    @Test
    public void whenFib1Then1() {
        FibonacciRecursive fib = new FibonacciRecursive();
        log.debug("\n whenFib1Then1 fib(1) {}", fib.fib(1));
        Assert.assertTrue(1 == fib.fib(1));
    }

    @Test
    public void whenFib2Then1() {
        FibonacciRecursive fib = new FibonacciRecursive();
        log.debug("\n whenFib2Then1 fib(2) {}", fib.fib(2));
        Assert.assertTrue(1 == fib.fib(2));
    }

    @Test
    public void whenFib3Then2() {
        FibonacciRecursive fib = new FibonacciRecursive();
        log.debug("\n whenFib3Then2 fib(3) {}", fib.fib(3));
        Assert.assertTrue(2 == fib.fib(3));
    }

    @Test
    public void whenFib4Then3() {
        FibonacciRecursive fib = new FibonacciRecursive();
        log.debug("\n whenFib4Then3 fib(4) {}", fib.fib(4));
        Assert.assertTrue(3 == fib.fib(4));
    }

    @Test
    public void whenFib5Then5() {
        FibonacciRecursive fib = new FibonacciRecursive();
        log.debug("\n whenFib5Then5 fib(5) {}", fib.fib(5));
        Assert.assertTrue(5 == fib.fib(5));
    }

    @Test
    public void whenFib6Then8() {
        FibonacciRecursive fib = new FibonacciRecursive();
        log.debug("\n whenFib6Then8 fib(6) {}", fib.fib(6));
        Assert.assertTrue(8 == fib.fib(6));
    }

    @Test
    public void whenFib7Then13() {
        FibonacciRecursive fib = new FibonacciRecursive();
        log.debug("\n whenFib7Then13 fib(7) {}", fib.fib(7));
        Assert.assertTrue(13 == fib.fib(7));
    }

    @Test
    public void whenPrintFib7Then0Through13() {
        FibonacciRecursive fib = new FibonacciRecursive();
        log.debug("\n whenPrintFib7Then0Through13 fib(7) {}", fib.fib(7));
        Assert.assertTrue("0, 1, 1, 2, 3, 5, 8, 13,".equals(fib.printFib(7)));
    }
}
