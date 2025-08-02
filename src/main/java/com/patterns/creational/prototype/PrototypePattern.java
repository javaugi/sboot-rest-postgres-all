/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.creational.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prototype pattern refers to creating duplicate object while keeping
 * performance in mind. This type of design pattern comes under creational
 * pattern as this pattern provides one of the best ways to create an object.
 *
 * This pattern involves implementing a prototype interface which tells to
 * create a clone of the current object. This pattern is used when creation of
 * object directly is costly. For example, an object is to be created after a
 * costly database operation. We can cache the object, returns its clone on next
 * request and update the database as and when needed thus reducing database
 * calls.
 *
 * When Would I Use This Pattern?
 *
 * The Prototype pattern should be considered when
 *
 * Composition, creation and representation of objects should be decoupled from
 * the system
 *
 * Classes to be created are specified at runtime
 *
 * You need to hide the complexity of creating new instance from the client
 *
 * Creating an object is an expensive operation and it would be more efficient
 * to copy an object.
 *
 * Objects are required that are similar to existing objects.
 *
 *
 * The pattern is used by the Clonable interface in Java. Cloneable is
 * implemented as a marker interface to show what objects can be cloned, as
 * Object already defined a protected clone() method. Client can override, or
 * call the superclass implementation, of this clone method to do the copy.
 *
 *
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Step 1
Create an abstract class implementing Clonable interface.
Step 2
Create concrete classes extending the above class.
Step 3
Create a class to get concrete classes from database and store them in a Hashtable.
Step 4
PrototypePattern uses ShapeCache class to get clones of shapes stored in a Hashtable.

 */
public class PrototypePattern {

    private static final Logger log = LoggerFactory.getLogger(PrototypePattern.class);

    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}
