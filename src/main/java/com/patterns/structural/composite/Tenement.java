/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Tenement extends AbstractProperty {

    private static final Logger log = LoggerFactory.getLogger(Apartment.class);

    private Tenement(String builder, String address, float price) {
        this.builder = builder;
        this.address = address;
        this.price = price;
        this.type = PropertyType.Tenement;
    }

    public static Property makePriperty() {
        return new Tenement("Town & Country", "200 Main", 102000);
    }

    @Override
    public void add(Property property) {
        // TODO Auto-generated method stub
    }

    @Override
    public void remove(Property property) {
        // TODO Auto-generated method stub
    }

    @Override
    public void purchase() {
        // TODO Auto-generated method stub
    }

    @Override
    public void sell() {
        // TODO Auto-generated method stub
    }

    @Override
    public void print() {
        System.out.println("Type " + type + " - The tenement built by " + builder + " is located at " + address + " sells for " + price);
    }
}
