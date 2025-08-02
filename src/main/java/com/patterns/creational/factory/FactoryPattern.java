/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.creational.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://www.tutorialspoint.com/design_pattern/design_pattern_overview.htm
 * Factory pattern is one of the most used design patterns in Java. This type of
 * design pattern comes under creational pattern as this pattern provides one of
 * the best ways to create an object.
 *
 * In Factory pattern, we create object without exposing the creation logic to
 * the client and refer to newly created object using a common interface.
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Factory Design Pattern Advantages
Factory design pattern provides approach to code for interface rather than implementation.
Factory pattern removes the instantiation of actual implementation classes from
    client code. Factory pattern makes our code more robust, less coupled and easy
    to extend. For example, we can easily change PC class implementation because client program is unaware of this.
Factory pattern provides abstraction between implementation and client classes through inheritance.

Factory Design Pattern Examples in JDK
java.util.Calendar, ResourceBundle and NumberFormat getInstance() methods uses Factory pattern.

valueOf() method in wrapper classes like Boolean, Integer etc.

 */
public class FactoryPattern {

    private static final Logger log = LoggerFactory.getLogger(FactoryPattern.class);

    public static void main(String[] args) {
        // Step 1 Create an interface.
        // Step 2 Create concrete classes implementing the same interface.
        // Step 3 Create a Factory to generate object of concrete class based on given information.
        FactoryPattern fact = new FactoryPattern();
        // Step 4 Use the Factory to get object of concrete class by passing an information such as type.
        fact.runExampe();
    }

    void runExampe() {
        ShapeFactory shapeFactory = new ShapeFactory();

        //get an object of Circle and call its draw method.
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //call draw method of Circle
        shape1.draw();

        //get an object of Rectangle and call its draw method.
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //call draw method of Rectangle
        shape2.draw();

        //get an object of Square and call its draw method.
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //call draw method of square
        shape3.draw();
    }

}
