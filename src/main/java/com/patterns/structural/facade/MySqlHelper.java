/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.facade;

import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class MySqlHelper implements DatabaseHelper {

    private static final Logger log = LoggerFactory.getLogger(MySqlHelper.class);

    public Connection getConnection() {
        return null;
    }

    public void generatePdfReport(Connection con) {
        System.out.println("MySqlHelper.generatePdfReport");
    }

    public void generateHtmlReport(Connection con) {
        System.out.println("MySqlHelper.generateHtmlReport");
    }
}
