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
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class SingleObject {

    private static final Logger log = LoggerFactory.getLogger(SingleObject.class);

    //create an object of SingleObject
    private static SingleObject instance = new SingleObject();

    //make the constructor private so that this class cannot be
    //instantiated
    private SingleObject() {
    }

    private static final class CreateSingleton {

        private static final SingleObject INSTANCE = new SingleObject();
    }

    //  When the singleton class is loaded, inner class is not loaded and hence
    //  doesn’t create object when loading the class. Inner class is created only
    //  when getInstance() method is called. So it may seem like eager initialization
    //  but it is lazy initialization. This is the most widely used approach as it doesn’t use synchronization.
    public static SingleObject getInstancePrefered() {
        return CreateSingleton.INSTANCE;
    }

    //Get the only object available
    public static SingleObject getInstance() {
        if (instance == null) {
            synchronized (SingleObject.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new SingleObject();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}
