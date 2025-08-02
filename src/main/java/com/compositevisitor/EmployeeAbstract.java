/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.compositevisitor;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public abstract class EmployeeAbstract implements Employee {

    private String name;
    private int hoursWorked;
    private double hourlyRtae;

    private double salary;
    private double insurance;

    private static final Logger log = LoggerFactory.getLogger(EmployeeAbstract.class);

    List<Employee> employeeList = new ArrayList();

    @Override
    public void add(Employee employee) {
        employeeList.add(employee);
    }

    @Override
    public void remove(Employee employee) {
        employeeList.remove(employee);
    }

    @Override
    public Employee getChild(int i) {
        return employeeList.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRtae() {
        return hourlyRtae;
    }

    public void setHourlyRtae(double hourlyRtae) {
        this.hourlyRtae = hourlyRtae;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public void printSalary() {
        for (Employee emp : employeeList) {
            emp.printSalary();
        }
    }

    @Override
    public abstract void accept(SalaryCalculator visitor);
}
