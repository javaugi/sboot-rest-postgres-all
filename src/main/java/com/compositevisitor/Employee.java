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
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface Employee {

    public void add(Employee employee);

    public void remove(Employee employee);

    public Employee getChild(int i);

    public String getName();

    public double getSalary();

    public void printSalary();

    void accept(SalaryCalculator visitor);
}
