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
public class DatabaseHelperFacade {

    private static final Logger log = LoggerFactory.getLogger(DatabaseHelperFacade.class);

    public static void generateReport(DBTypes dbType, ReportTypes reportType) {
        Connection con = null;
        switch (dbType) {
            case MYSQL:
                MySqlHelper mySqlHelper = new MySqlHelper();
                con = mySqlHelper.getConnection();
                switch (reportType) {
                    case HTML:
                        mySqlHelper.generateHtmlReport(con);
                        break;
                    case PDF:
                        mySqlHelper.generatePdfReport(con);
                        break;
                }
                break;
            case ORACLE:
                OracleHelper oracleHelper = new OracleHelper();
                con = oracleHelper.getConnection();
                switch (reportType) {
                    case HTML:
                        oracleHelper.generateHtmlReport(con);
                        break;
                    case PDF:
                        oracleHelper.generatePdfReport(con);
                        break;
                }
                break;
        }

    }

    public static enum DBTypes {
        MYSQL, ORACLE;
    }

    public static enum ReportTypes {
        HTML, PDF;
    }
}
