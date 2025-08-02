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
 * The composite pattern is meant to allow treating individual objects and
 * compositions of objects, or “composites” in the same way. * It can be viewed
 * as a tree structure made up of types that inherit a base type, and it can
 * represent a single part or a whole hierarchy of objects.
 *
 * WorkFlow: Client use the component class interface to interact with objects
 * in the composition structure.if recipient is a leaf then request is handled
 * directly.If recipient is a composite,then it usually forwards request to its
 * child components,possibly performing additional operations before and after
 * forwarding.
 *
 * Recursion: What makes the Composite pattern one of the most beautiful is the
 * power of recursion. I can explain this with the same organization example.
 * You want to find the total salary paid to all employees of the organization.
 * It is nothing but the salary of CEO + the salary paid to all the departments.
 * What is the salary of a department? It is the salary of the department head +
 * the salary of all projects. What is the total salary of a project? It is the
 * salary of the project manager + the salary of all the project members. In
 * short, the salary of anything is the salary of self + the salary of all its
 * sub groups.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
 * We can break the pattern down into:
 *
 * component – is the base interface for all the objects in the composition. It
 *      should be either an interface or an abstract class with the common methods to
 *      manage the child composites.
 * leaf – implements the default behavior of the base component. It doesn’t
 *      contain a reference to the other objects.
 * composite – has leaf elements. It implements the base component methods and
 *      defines the child-related operations.
 * client – has access to the composition elements by using the base component object.

Composite Pattern Important Points
Composite pattern should be applied only when the group of objects should behave
    as the single object.
Composite design pattern can be used to create a tree like structure.

java.awt.Container#add(Component) is a great example of Composite pattern in java and used a lot in Swing.
 *
 */
public class CompositePattern {

    private static final Logger log = LoggerFactory.getLogger(CompositePattern.class);

    public static void main(String[] args) {
        demoCompositeDrawing();
        demoCompositeEmployee();
        demoCompositeProperty();
    }

    private static void demoCompositeDrawing() {
        Shape tri = new Triangle();
        Shape tri1 = new Triangle();
        Shape cir = new Circle();

        CompositeDrawing drawing = new CompositeDrawing();
        drawing.add(tri1);
        drawing.add(tri1);
        drawing.add(cir);

        drawing.draw("Red");

        drawing.clear();

        drawing.add(tri);
        drawing.add(cir);
        drawing.draw("Green");
    }

    private static void demoCompositeEmployee() {
        Employee emp1 = new Developer("John", 10000);
        Employee emp2 = new Developer("David", 15000);
        Employee manager1 = new Manager("Daniel", 25000);
        manager1.add(emp1);
        manager1.add(emp2);
        Employee emp3 = new Developer("Michael", 20000);
        Manager generalManager = new Manager("Mark", 50000);
        generalManager.add(emp3);
        generalManager.add(manager1);
        generalManager.print();
    }

    private static void demoCompositeProperty() {
        PropertyFactoryImpl factory = PropertyFactoryImpl.getInstance();
        Property property = factory.makeProperty(PropertyType.Office);
        property.add(factory.makeProperty(PropertyType.Apartment));
        property.add(factory.makeProperty(PropertyType.Tenement));
        property.add(factory.makeProperty(PropertyType.Bungalow));
        property.print();
    }

}
