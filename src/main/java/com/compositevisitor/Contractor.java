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
public class Contractor extends EmployeeAbstract {

    private static final Logger log = LoggerFactory.getLogger(Contractor.class);

    Employee employee;
    SalaryCalculator visitor;

    private Contractor(String name, int hours, double rate) {
        super.setName(name);
        super.setSalary(hours * rate);
    }

    public static Employee makeEmployee() {
        return new Contractor("Jim the SContractor", 8, 125);
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
