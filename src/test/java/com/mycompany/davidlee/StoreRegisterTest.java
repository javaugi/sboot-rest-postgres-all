/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.mycompany.davidlee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mycompany.davidlee.api.Receipt;
import com.mycompany.davidlee.api.ShoppingCart;
import com.mycompany.davidlee.api.StoreRegister;
import com.mycompany.davidlee.impl.ItemImpl;
import com.mycompany.davidlee.impl.ShoppingCartImpl;
import com.mycompany.davidlee.impl.StoreRegisterImpl;
import com.spring5.ProjectTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class StoreRegisterTest extends ProjectTest {

    private static final Logger log = LoggerFactory.getLogger(StoreRegisterTest.class);

    StoreRegister reg = null;
    ShoppingCart cart = null;

    @Before
    public void setup() {
        log.info("SETUP");
        reg = new StoreRegisterImpl();
        cart = new ShoppingCartImpl();
    }

    @Test
    public void whenCartEmptyThenTotalZero() {
        log.info("whenCartEmptyThenTotalZero");
        Receipt receipt = reg.checkout(cart);
        Assert.assertTrue(0 == receipt.getTotal());
    }

    @Test
    public void whenAdd1ThenTotal1Item() {
        log.info("whenAdd1ThenTotal1Item");
        cart.addItem(new ItemImpl("Id100", 1.25));
        reg = new StoreRegisterImpl();
        Receipt receipt = reg.checkout(cart);
        Assert.assertTrue(1.25 == receipt.getTotal());
    }

    @Test
    public void whenAdd2ThenTotal2Items() {
        cart.addItem(new ItemImpl("Id100", 1.25));
        cart.addItem(new ItemImpl("Id101", 1.55));
        reg = new StoreRegisterImpl();
        Receipt receipt = reg.checkout(cart);
        Assert.assertTrue(2.80 == receipt.getTotal());
    }

    @Test
    public void whenAdd2Remove1ThenTotal1Item() {
        //ShoppingCart cart = new ShoppingCartImpl();
        cart.addItem(new ItemImpl("Id100", 1.25));
        cart.addItem(new ItemImpl("Id101", 1.55));
        cart.removeItem(new ItemImpl("Id100", 1.25));
        reg = new StoreRegisterImpl();
        Receipt receipt = reg.checkout(cart);
        log.debug("receipt total=" + receipt.getTotal() + " with items =" + receipt.getItems());
        Assert.assertTrue(1.55 == receipt.getTotal());
    }
}
