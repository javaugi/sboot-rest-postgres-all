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
public class PrimeNumbersTest extends ProjectTest {

    @Test
    public void when1ThenTrue() {
        PrimeNumbers pn = new PrimeNumbers();
        Assert.assertTrue(pn.isPrime(1));
    }

    @Test
    public void when2ThenTrue() {
        PrimeNumbers pn = new PrimeNumbers();
        Assert.assertTrue(pn.isPrime(2));
    }

    @Test
    public void when3ThenTrue() {
        PrimeNumbers pn = new PrimeNumbers();
        Assert.assertTrue(pn.isPrime(3));
    }

    @Test
    public void when4ThenFalse() {
        PrimeNumbers pn = new PrimeNumbers();
        Assert.assertTrue(!pn.isPrime(4));
    }
}
