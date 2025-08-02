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
public class Apartment extends AbstractProperty {

    private static final Logger log = LoggerFactory.getLogger(Apartment.class);

    private Apartment(String builder, String address, float price) {
        this.builder = builder;
        this.address = address;
        this.price = price;
        this.type = PropertyType.Apartment;
    }

    public static Property makePriperty() {
        return new Apartment("Town & Country", "100 Main", 202000);
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
        System.out.println("Type " + type + " - The apartment built by " + builder + " is located at " + address + " sells for " + price);
    }
}
