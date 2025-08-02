/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.facade;

import com.patterns.structural.decorator.*;
import com.patterns.structural.composite.*;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Triangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Drawing Triangle ");
    }
}
