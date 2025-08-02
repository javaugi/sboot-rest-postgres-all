/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.mycompany.davidlee.api;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface Item {

    public static enum Category {
        Bagged, Weight, Discount, BuyGetFree;
    }

    public String getName();

    public void setName(String name);

    public Category getCategory();

    public void setCategory(Category category);

    public double getPrice();

    public void setPrice(double price);

}
