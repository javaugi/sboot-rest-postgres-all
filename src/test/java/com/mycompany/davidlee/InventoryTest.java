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

import com.mycompany.davidlee.api.Inventory;
import com.mycompany.davidlee.api.Item.Category;
import com.mycompany.davidlee.impl.InventoryImpl;
import com.mycompany.davidlee.impl.ItemImpl;
import com.mycompany.davidlee.impl.WeightItem;
import com.spring5.ProjectTest;
import org.junit.Before;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class InventoryTest extends ProjectTest {

    Inventory impl = null;

    @Before
    public void setup() {
        impl = new InventoryImpl();
    }

    @Test
    public void whenAdd1ThenSize1() {
        impl.addItem(new ItemImpl("ID001", 1.25));
        Assert.assertEquals(1, impl.size());
    }

    @Test
    public void whenAddScannedThenCategoryScanned() {
        impl.addItem(new ItemImpl("ID001", 1.25));
        Assert.assertTrue(Category.Bagged.equals(impl.getItemByName("ID001").getCategory()));
    }

    @Test
    public void whenAddWeightThenCategoryWeight() {
        impl.addItem(new WeightItem("ID001", 1.25, 1.25));
        System.out.println("category = " + impl.getItemByName("ID001").getCategory());
        Assert.assertTrue(Category.Weight.equals(impl.getItemByName("ID001").getCategory()));
    }
}
