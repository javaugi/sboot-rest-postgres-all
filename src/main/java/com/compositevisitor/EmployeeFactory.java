/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.compositevisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class EmployeeFactory {

    private static final Logger log = LoggerFactory.getLogger(EmployeeFactory.class);

    private static final class CreateSingleton {

        private static final EmployeeFactory INSTANCE = new EmployeeFactory();
    }

    public static EmployeeFactory getInstance() {
        return CreateSingleton.INSTANCE;
    }

    public static Employee makeProperty(EmployeeType type) {
        switch (type) {
            case Employee:
                return EmployeeImpl.makeEmployee();
            case Manager:
                return Manager.makeEmployee();
            case Contractor:
                return Contractor.makeEmployee();
            default:
                return null;
        }
    }
}
