/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.creational;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton pattern is one of the simplest design patterns in Java. This type
 * of design pattern comes under creational pattern as this pattern provides one
 * of the best ways to create an object.
 *
 * This pattern involves a single class which is responsible to create an object
 * while making sure that only single object gets created. This class provides a
 * way to access its only object which can be accessed directly without need to
 * instantiate the object of the class.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class SingletonPattern {

    private static final Logger log = LoggerFactory.getLogger(SingletonPattern.class);

    public static void main(String[] args) {
        SingletonPattern pat = new SingletonPattern();
        pat.runExample();
    }

    void runExample() {
        SingleObject object = SingleObject.getInstance();

        //show the message
        object.showMessage();
    }

}
