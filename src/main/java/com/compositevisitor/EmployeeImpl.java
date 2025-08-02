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
public class EmployeeImpl extends EmployeeAbstract {

    private static final Logger log = LoggerFactory.getLogger(EmployeeImpl.class);
    Employee employee;
    SalaryCalculator visitor;

    private EmployeeImpl(String name, double salary) {
        super.setName(name);
        super.setSalary(salary);
    }

    public static Employee makeEmployee() {
        double sal = 120000;
        return new EmployeeImpl("Mike the Employee ", sal);
    }

    @Override
    public void printSalary() {
        System.out.println("Salary for " + super.getName() + " is " + visitor.calculateSalary((Employee) this));
    }

    @Override
    public void accept(SalaryCalculator visitor) {
        this.visitor = visitor;
    }
}
