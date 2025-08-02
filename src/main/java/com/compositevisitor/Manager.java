/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.compositevisitor;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Manager extends EmployeeAbstract {

    Employee employee;
    SalaryCalculator visitor;

    private Manager(String name, double salary) {
        super.setName(name);
        super.setSalary(salary);
    }

    public static Employee makeEmployee() {
        double sal = 150000;
        return new Manager("Jonh the Manager ", sal);
    }

    @Override
    public void printSalary() {
        System.out.println("Salary for " + super.getName() + " is " + visitor.calculateSalary((Employee) this));
        super.printSalary();
    }

    @Override
    public void accept(SalaryCalculator visitor) {
        this.visitor = visitor;
    }
}
