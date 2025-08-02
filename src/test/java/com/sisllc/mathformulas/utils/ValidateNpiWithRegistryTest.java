/*
 * Copyright (C) 2021 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.sisllc.mathformulas.utils;

import com.abc.utils.OpenCsvNpiValidation;
import com.spring5.ProjectTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

/**
 *
 *
 * @author bill
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ValidateNpiWithRegistryTest extends ProjectTest {

    private static final Logger log = LoggerFactory.getLogger(ValidateNpiWithRegistryTest.class);

    @Test
    public void validNPI() {
        boolean isValidNPI = true;
        if (npiRegistryEnabled) {
            isValidNPI = OpenCsvNpiValidation.validateNPIWithRegistry(validNpiValue);
        }
        Assert.assertTrue("npiRegistryEnabled=" + npiRegistryEnabled, isValidNPI);
    }

    @Test
    public void invalidNPI() {
        boolean isValidNPI = false;
        if (npiRegistryEnabled) {
            isValidNPI = OpenCsvNpiValidation.validateNPIWithRegistry(invalidNpiValue);
        }
        Assert.assertTrue("npiRegistryEnabled=" + npiRegistryEnabled, !isValidNPI);
    }
}
