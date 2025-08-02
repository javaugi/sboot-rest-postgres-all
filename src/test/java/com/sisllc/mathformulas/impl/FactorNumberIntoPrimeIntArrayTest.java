/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.impl;

import com.spring5.ProjectTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class FactorNumberIntoPrimeIntArrayTest extends ProjectTest {

    private static final Logger log = LoggerFactory.getLogger(FactorNumberIntoPrimeIntArrayTest.class);

    int[] returnValue = null;

    FactorNumberIntoPrimeIntArray primeFactor = null;

    @Before
    public void setup() {
        primeFactor = new FactorNumberIntoPrimeIntArray();

    }

    //prime numbers: 2, 3, 5, 7, 11, 13, 17, 19, 23 and 29.
    // 2    [2]
    // 3    [3]
    // 4    [2, 2]
    // 5    [5]
    // 6    [2, 3]
    // 7    [7]
    // 8    [2, 2, 2]
    // 10   [2, 5]
    @Test
    public void when2Then2() {
        int[] expected = {2};
        returnValue = primeFactor.factorN(2);
        Assert.assertTrue(Arrays.equals(returnValue, expected));
        returnValue = primeFactor.factorN2Primes(2);
        Assert.assertTrue(Arrays.equals(returnValue, expected));
        returnValue = primeFactor.factorNumberToPrimes(2);
        Assert.assertTrue(Arrays.equals(returnValue, expected));

        List<Integer> list = new ArrayList<>();
        returnValue = primeFactor.doFactoringRecursive(list, 2, 2);
        Assert.assertTrue(Arrays.equals(returnValue, expected));
    }

    @Test
    public void when3Then3() {
        int[] expected = {3};
        returnValue = primeFactor.factorN(3);
        Assert.assertTrue(Arrays.equals(returnValue, expected));
        returnValue = primeFactor.factorN2Primes(3);
        Assert.assertTrue(Arrays.equals(returnValue, expected));
        returnValue = primeFactor.factorNumberToPrimes(3);
        Assert.assertTrue(Arrays.equals(returnValue, expected));

        List<Integer> list = new ArrayList<>();
        returnValue = primeFactor.doFactoringRecursive(list, 3, 2);
        Assert.assertTrue(Arrays.equals(returnValue, expected));
    }

    @Test
    public void when4Then22() {
        int[] expected = {2, 2};
        returnValue = primeFactor.factorN(4);
        Assert.assertTrue(Arrays.equals(returnValue, expected));
        returnValue = primeFactor.factorN2Primes(4);
        Assert.assertTrue(Arrays.equals(returnValue, expected));
        returnValue = primeFactor.factorNumberToPrimes(4);
        Assert.assertTrue(Arrays.equals(returnValue, expected));

        List<Integer> list = new ArrayList<>();
        returnValue = primeFactor.doFactoringRecursive(list, 4, 2);
        Assert.assertTrue(Arrays.equals(returnValue, expected));
    }
}
