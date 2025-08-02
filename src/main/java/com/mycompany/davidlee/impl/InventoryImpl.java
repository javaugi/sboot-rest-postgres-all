/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.mycompany.davidlee.impl;

import com.mycompany.davidlee.api.Inventory;
import com.mycompany.davidlee.api.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class InventoryImpl implements Inventory {

    private static List<Item> items = null;

    public InventoryImpl() {
        items = new ArrayList();
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public Item getItemByName(String name) {
        for (Item item : items) {
            if (name.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }
}
