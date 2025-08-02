/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.mycompany.davidlee.impl;



/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class BuyGetFreeItem extends ItemImpl {

    private int getAmount;
    private int freeAmout;
    private int limitAmout;
    public static final int LIMIT_AMOUNT = 1000;

    public BuyGetFreeItem(String name, double price, int getAmount, int freeAmout) {
        this(name, price, getAmount, freeAmout, LIMIT_AMOUNT);
    }

    public BuyGetFreeItem(String name, double price, int getAmount, int freeAmout, int limitAmout) {
        super(name, price);
        super.setCategory(Category.BuyGetFree);
        this.getAmount = getAmount;
        this.freeAmout = freeAmout;
        this.limitAmout = limitAmout;
    }

    public int getGetAmount() {
        return getAmount;
    }

    public void setGetAmount(int getAmount) {
        this.getAmount = getAmount;
    }

    public int getFreeAmout() {
        return freeAmout;
    }

    public void setFreeAmout(int freeAmout) {
        this.freeAmout = freeAmout;
    }

    public int getLimitAmout() {
        return limitAmout;
    }

    public void setLimitAmout(int limitAmout) {
        this.limitAmout = limitAmout;
    }
}
