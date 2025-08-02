/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.bridge;

import com.sisllc.mathformulas.ci.ch08.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GOF: Decouple an abstraction from its implementation so that the two can vary
 * independently
 *
 * Bridge is used when we need to decouple an abstraction from its
 * implementation so that the two can vary independently. This type of design
 * pattern comes under structural pattern as this pattern decouples
 * implementation class and abstract class by providing a bridge structure
 * between them.
 *
 * This pattern involves an interface which acts as a bridge which makes the
 * functionality of concrete classes independent from interface implementer
 * classes. Both types of classes can be altered structurally without affecting
 * each other.
 *
 * The Bridge design pattern allows you to separate the abstraction from the
 * implementation.It is a structural design pattern. There are 2 parts in Bridge
 * design pattern :
 *
 * 1. Abstraction
 *
 * 2. Implementation
 *
 *
 * This is a design mechanism that encapsulates an implementation class inside
 * of an interface class.
 *
 * The bridge pattern allows the Abstraction and the Implementation to be
 * developed independently and the client code can access only the Abstraction
 * part without being concerned about the Implementation part.
 *
 * The abstraction is an interface or abstract class and the implementor is also
 * an interface or abstract class.
 *
 * The abstraction contains a reference to the implementor. Children of the
 * abstraction are referred to as refined abstractions, and children of the
 * implementor are concrete implementors. Since we can change the reference to
 * the implementor in the abstraction, we are able to change the abstraction’s
 * implementor at run-time. Changes to the implementor do not affect client
 * code.
 *
 * It increases the loose coupling between class abstraction and it’s
 * implementation.
 *
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Step 1
Create bridge implementer interface.
Step 2
Create concrete bridge implementer classes implementing the DrawAPI interface.
Step 3
Create an abstract class Shape using the DrawAPI interface.
Step 4
Create concrete class implementing the Shape interface.

We have a DrawApiBridge interface which is acting as a bridge implementer and concrete
    classes RedCircle, GreenCircle implementing the DrawApiBridge interface.
Shape is an abstract class and will use object of DrawAPI.

BridgePattern,  our demo class will use Shape class to draw different colored circle.
 */
public class BridgePattern {

    private static final Logger log = LoggerFactory.getLogger(BridgePattern.class);

    public static void main(String[] args) {
        System.out.println("\n\n Bridge Pattern 1 ...");
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();

        System.out.println("\n\n Bridge Pattern 2 ...");
        Shape1 tri = new Triangle(new RedColor());
        tri.applyColor();

        Shape1 pent = new Pentagon(new GreenColor());
        pent.applyColor();

        System.out.println("\n\n Bridge Pattern 3 ...");
        //  VehicleBridge - abstraction in bridge pattern
        //  Car - Refine abstraction 1 in bridge pattern
        //  Bike - Refine abstraction 2 in bridge pattern
        // Workshop - Implementor for bridge pattern
        // ProduceBridgeImplementor - Implementor 1 for bridge pattern
        // AssembleBridgeImplementor - Implementor 2 for bridge pattern
        VehicleBridgeAbstraction vehicle1 = new Car(new ProduceBridgeImplementor(), new AssembleBridgeImplementor());
        vehicle1.manufacture();
        VehicleBridgeAbstraction vehicle2 = new Bike(new ProduceBridgeImplementor(), new AssembleBridgeImplementor());
        vehicle2.manufacture();
    }
}
