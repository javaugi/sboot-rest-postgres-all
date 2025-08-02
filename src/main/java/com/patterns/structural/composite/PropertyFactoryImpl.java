/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.composite;

import com.patterns.structural.composite.AbstractProperty.PropertyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class PropertyFactoryImpl implements PropertyFactory {

    private static final Logger log = LoggerFactory.getLogger(PropertyFactoryImpl.class);

    private static PropertyFactoryImpl instance = null;

    private static final class CreateSingleton {

        private static final PropertyFactoryImpl INSTANCE = new PropertyFactoryImpl();
    }

    public static PropertyFactoryImpl getInstance() {
        return CreateSingleton.INSTANCE;
    }

    public Property makeProperty(PropertyType type) {
        switch (type) {
            case Apartment:
                return Apartment.makePriperty();
            case Tenement:
                return Tenement.makePriperty();
            case Bungalow:
                return Bungalow.makePriperty();
            case Office:
                return Office.makePriperty();
            default:
                return null;
        }
    }
}
