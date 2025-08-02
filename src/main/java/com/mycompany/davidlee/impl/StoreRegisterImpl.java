/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.mycompany.davidlee.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mycompany.davidlee.api.Inventory;
import com.mycompany.davidlee.api.Item;
import com.mycompany.davidlee.api.Receipt;
import com.mycompany.davidlee.api.ShoppingCart;
import com.mycompany.davidlee.api.StoreRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class StoreRegisterImpl implements StoreRegister {

    private static final Logger log = LoggerFactory.getLogger(StoreRegisterImpl.class);

    Receipt receipt = null;

    public StoreRegisterImpl() {
        receipt = new ReceiptImpl();
    }

    @Override
    public Receipt checkout(ShoppingCart cart) {
        for (Item item : cart.getItems()) {
            receipt.addItem(item);
        }
        List<Item> coupons = cart.getCoupons();
        if (coupons != null && !coupons.isEmpty()) {
            receipt.applyCoupons(coupons);
        }
        return receipt;
    }

    public static void main(String[] args) {
        StoreRegisterImpl reg = new StoreRegisterImpl();
        ShoppingCart cart = populateShoppingCart();
        Receipt receipt = reg.checkout(cart);
        log.info("Here is the Receipt Detail: \n" + receipt);
    }

    private static ShoppingCart populateShoppingCart() {
        ShoppingCart cart = new ShoppingCartImpl();

        Inventory inv = populateInventory();
        List<Item> items = inv.getItems();
        List<Integer> givenList = Arrays.asList(1, 2, 3, 4, 5, 6);
        Random rand = new Random();

        for (int i = 0; i < 7; i++) {
            Item randomItem = items.get(rand.nextInt(items.size()));
            int addItemTimes = givenList.get(rand.nextInt(givenList.size()));
            for (int j = 0; j < addItemTimes; j++) {
                cart.addItem(randomItem);
            }
            if (i / 2 == 0) {
                if (randomItem.getCategory().equals(Item.Category.Bagged)) {
                    DiscountItem discount = new DiscountItem(randomItem.getName(), randomItem.getPrice(), 0.20);
                    cart.addItem(discount);
                } else {
                    BuyGetFreeItem guyFree = new BuyGetFreeItem(randomItem.getName(), randomItem.getPrice(), 1, 1);
                    cart.addItem(guyFree);
                }
            }
        }

        return cart;
    }

    private static Inventory populateInventory() {
        InventoryImpl inv = new InventoryImpl();

        inv.addItem(new ItemImpl("Something Wired", 10.25));
        inv.addItem(new ItemImpl("Pride and Prejudice", 12.35));
        inv.addItem(new ItemImpl("Zombies", 15.45));
        inv.addItem(new ItemImpl("Dog Times", 111.55));
        inv.addItem(new ItemImpl("There would be no Cake", 18.65));

        inv.addItem(new ItemImpl("No Free Lunch", 19.75));
        inv.addItem(new ItemImpl("Chocolate Bunnie", 9.85));
        inv.addItem(new ItemImpl("Kill a Mockingbird", 8.15));
        inv.addItem(new ItemImpl("The Wild Thing", 13.25));
        inv.addItem(new ItemImpl("Long Dark Tea", 17.35));

        inv.addItem(new WeightItem("Chicken", 2.25, 3.45));
        inv.addItem(new WeightItem("Beef", 3.55, 5.55));
        inv.addItem(new WeightItem("Chicken Breast", 5.65, 2.65));
        inv.addItem(new WeightItem("Pork", 4.75, 3.75));
        inv.addItem(new WeightItem("Veal", 5.85, 4.85));

        inv.addItem(new WeightItem("Venison", 3.15, 8.15));
        inv.addItem(new WeightItem("Lamb and Mutton", 3.35, 7.75));
        inv.addItem(new WeightItem("Bacon", 2.45, 2.65));
        inv.addItem(new WeightItem("Chuck Steak", 4.15, 3.85));
        inv.addItem(new WeightItem("Guanciale", 5.15, 6.15));
        return inv;
    }

}
