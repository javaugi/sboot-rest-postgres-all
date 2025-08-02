/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public abstract class Shape1 {
    //Composition - implementor

    protected ColorBridge color;

    //constructor with implementor as input argument
    public Shape1(ColorBridge c) {
        this.color = c;
    }

    abstract public void applyColor();
}
