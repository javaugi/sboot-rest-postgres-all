/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.mycompany.davidlee.impl;

import com.mycompany.davidlee.api.ShoppingCart;
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
public class ShoppingCartImpl implements ShoppingCart {

    private List<Item> items;
    private List<Item> coupons;

    public ShoppingCartImpl() {
        items = new ArrayList();
        coupons = new ArrayList();
    }

    @Override
    public void addItem(Item item) {
        switch (item.getCategory()) {
            case Bagged:
                items.add(item);
                break;
            case Weight:
                items.add(item);
                break;
            case Discount:
                coupons.add(item);
                break;
            case BuyGetFree:
                coupons.add(item);
                break;
        }
    }

    @Override
    public void removeItem(Item item) {
        switch (item.getCategory()) {
            case Bagged:
                items.remove(item);
                break;
            case Weight:
                items.remove(item);
                break;
            case Discount:
                coupons.remove(item);
                break;
            case BuyGetFree:
                coupons.remove(item);
                break;
        }
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public List<Item> getCoupons() {
        return coupons;
    }

    @Override
    public void setCoupons(List<Item> coupons) {
        this.coupons = coupons;
    }

}
