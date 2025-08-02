package com.sisllc.mathformulas.impl;

import com.spring5.ProjectTest;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ProductOfPrimesBetweenLToRTest extends ProjectTest {

    PrimeNumbers pn = null;

    @Before
    public void setup() {
        pn = new PrimeNumbers();
    }

    @Test
    public void when3to5Then15() {
        Assert.assertTrue(15 == pn.product(3, 5));
    }

    @Test
    public void when37Then105() {
        System.out.println("product=" + pn.product(3, 7));
        Assert.assertTrue(105 == pn.product(3, 7));
    }
}
