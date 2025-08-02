/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.mycompany.davidlee;

import org.junit.Assert;
import org.junit.Test;

import com.mycompany.davidlee.api.Receipt;
import com.mycompany.davidlee.impl.ItemImpl;
import com.mycompany.davidlee.impl.ReceiptImpl;
import org.junit.Before;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ReceiptTest {

    Receipt rec = null;

    @Before
    public void setup() {
        rec = new ReceiptImpl();
    }

    @Test
    public void whenAdd3ThenPriceTotal3Times() {
        rec.addItem(new ItemImpl("ID001", 1.5));
        rec.addItem(new ItemImpl("ID001", 1.5));
        rec.addItem(new ItemImpl("ID001", 1.5));
        Assert.assertTrue(4.5 == rec.getTotal());
    }
}
