/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.misc.nullobject;

/**
 * In Null Object pattern, a null object replaces check of NULL object instance.
 * Instead of putting if check for a null value, Null Object reflects a do
 * nothing relationship. Such Null object can also be used to provide default
 * behaviour in case data is not available.
 *
 * In Null Object pattern, we create an abstract class specifying various
 * operations to be done, concrete classes extending this class and a null
 * object class providing do nothing implemention of this class and will be used
 * seemlessly where we need to check null value.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class NullObjectPattern {

    public static void main(String[] args) {

        AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
        AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");
        AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
        AbstractCustomer customer4 = CustomerFactory.getCustomer("Laura");

        System.out.println("Customers");
        System.out.println(customer1.getName());
        System.out.println(customer2.getName());
        System.out.println(customer3.getName());
        System.out.println(customer4.getName());
    }
}
