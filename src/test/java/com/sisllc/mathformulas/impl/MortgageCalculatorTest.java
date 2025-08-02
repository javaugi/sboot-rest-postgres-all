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
public class MortgageCalculatorTest extends ProjectTest {

    @Test
    public void whenTerm30YearsThen360Months() {
        MortgageCalculator calc = new MortgageCalculator();
        Assert.assertTrue(360 == calc.termInMonths(30));
    }

    @Test
    public void whenRate6Then0dot005() {
        MortgageCalculator calc = new MortgageCalculator();
        Assert.assertTrue(0.005 == calc.rateInMonths(6));
    }

    @Test
    public void whenRate6Term1Then0dot005() {
        MortgageCalculator calc = new MortgageCalculator();
        System.out.println("payment=" + calc.printPayments(30, 200000, 6));
        Assert.assertTrue(1199.1 == calc.printPayments(30, 200000, 6));
    }
}
