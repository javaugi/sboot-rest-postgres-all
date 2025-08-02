package com.sisllc.mathformulas.stringmanipulation;

import com.spring5.ProjectTest;
import org.junit.Assert;
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
public class CapitalizeFirstLetterTest extends ProjectTest {

    private static final Logger log = LoggerFactory.getLogger(CapitalizeFirstLetterTest.class);

    @Test
    public void whenscopeThenScope() {
        CapitalizeFirstLetter cap = new CapitalizeFirstLetter();
        Assert.assertTrue("Scope".equals(cap.camelizeWord("scope")));
    }

    @Test
    public void whengoodmorningThenGoodMorning() {
        CapitalizeFirstLetter cap = new CapitalizeFirstLetter();
        System.out.println("value=" + cap.camelizeSentence("good morning"));
        Assert.assertTrue("Good Morning".equals(cap.camelizeSentence("good morning")));
    }
}
