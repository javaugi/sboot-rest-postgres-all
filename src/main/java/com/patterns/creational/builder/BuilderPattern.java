/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.creational.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder pattern builds a complex object using simple objects and using a step
 * by step approach. This type of design pattern comes under creational pattern
 * as this pattern provides one of the best ways to create an object.
 *
 * A Builder class builds the final object step by step. This builder is
 * independent of other objects.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Step 1 Create an interface Item representing food item and packing.
Step 2 Create concrete classes implementing the Packing interface.
Step 3 Create abstract classes implementing the item interface providing default functionalities.
Step 4 Create concrete classes extending Burger and ColdDrink classes
Step 5 Create a Meal class having Item objects defined above.
Step 6 Create a MealBuilder class, the actual builder class responsible to create Meal objects.
Step 7 BuiderPatternDemo uses MealBuider to demonstrate builder pattern.


Builder Design Pattern in Java
Let’s see how we can implement builder design pattern in java.

First of all you need to create a static nested class and then copy all the arguments
    from the outer class to the Builder class. We should follow the naming convention
    and if the class name is Computer then builder class should be named as ComputerBuilder.
Java Builder class should have a public constructor with all the required attributes
    as parameters.
Java Builder class should have methods to set the optional parameters and it should
    return the same Builder object after setting the optional attribute.
The final step is to provide a build() method in the builder class that will return
    the Object needed by client program. For this we need to have a private constructor in the Class with Builder class as argument.

Builder Design Pattern Example in JDK
Some of the builder pattern example in Java classes are;

    java.lang.StringBuilder#append() (unsynchronized)
    java.lang.StringBuffer#append() (synchronized)

 */
public class BuilderPattern {

    private static final Logger log = LoggerFactory.getLogger(BuilderPattern.class);

    public static void main(String[] args) {

        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }

}
