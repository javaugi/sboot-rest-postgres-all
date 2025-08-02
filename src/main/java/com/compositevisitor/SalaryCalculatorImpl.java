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
public class SalaryCalculatorImpl implements SalaryCalculator {

    private static final Logger log = LoggerFactory.getLogger(SalaryCalculatorImpl.class);

    @Override
    public double calculateSalary(Employee employee) {
        return employee.getSalary();
    }
}
