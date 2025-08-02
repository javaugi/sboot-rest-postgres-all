/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Decorator pattern allows a user to add new functionality to an existing
 * object without altering its structure. This type of design pattern comes
 * under structural pattern as this pattern acts as a wrapper to existing class.
 *
 * This pattern creates a decorator class which wraps the original class and
 * provides additional functionality keeping class methods signature intact.
 *
 * We are demonstrating the use of decorator pattern via following example in
 * which we will decorate a shape with some color without alter shape class.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Step 1
Create an interface.
Step 2
Create concrete classes implementing the same interface.
Step 3
Create abstract decorator class implementing the Shape interface.
Step 4
Create concrete decorator class extending the ShapeDecorator class.
Step 5
Use the RedShapeDecorator to decorate Shape objects.


Decorator Design Pattern – Important Points
Decorator design pattern is helpful in providing runtime modification
    abilities and hence more flexible. Its easy to maintain and extend when the
    number of choices are more.
The disadvantage of decorator design pattern is that it uses a lot of similar
    kind of objects (decorators).
Decorator pattern is used a lot in Java IO classes, such as FileReader, BufferedReader etc.


 */
public class DecoratorPattern {

    private static final Logger log = LoggerFactory.getLogger(DecoratorPattern.class);

    public static void main(String[] args) {

        Shape circle = new Circle();

        Shape redCircle = new RedShapeDecorator(new Circle());

        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
