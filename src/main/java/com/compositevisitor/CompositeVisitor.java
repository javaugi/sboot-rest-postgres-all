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
public class CompositeVisitor {

    private static final Logger log = LoggerFactory.getLogger(CompositeVisitor.class);

    public static void main(String[] args) {
        CompositeVisitor cv = new CompositeVisitor();
        cv.test();
    }

    public void test() {
        SalaryCalculatorImpl v = new SalaryCalculatorImpl();
        Employee e = EmployeeFactory.makeProperty(EmployeeType.Employee);
        e.accept(v);
        Employee c = EmployeeFactory.makeProperty(EmployeeType.Contractor);
        c.accept(v);
        Employee m = EmployeeFactory.makeProperty(EmployeeType.Manager);
        m.accept(v);
        m.add(e);
        m.add(c);
        m.printSalary();
    }
}
