/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * In Visitor pattern, we use a visitor class which changes the executing
 * algorithm of an element class. By this way, execution algorithm of element
 * can vary as and when visitor varies. This pattern comes under behavior
 * pattern category. As per the pattern, element object has to accept the
 * visitor object so that visitor object handles the operation on the element
 * object. Visitor pattern is used when we have to perform an operation on a
 * group of similar kind of Objects. With the help of visitor pattern, we can
 * move the operational logic from the objects to another class.
 *
 * For example, think of a Shopping cart where we can add different type of
 * items (Elements). When we click on checkout button, it calculates the total
 * amount to be paid. Now we can have the calculation logic in item classes or
 * we can move out this logic to another class using visitor pattern. Let’s
 * implement this in our example of visitor pattern.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class VisitorPattern {

    private static final Logger log = LoggerFactory.getLogger(VisitorPattern.class);

    public static void main(String[] args) {

        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartVisitorImpl());

        System.out.println("\n\n\n shoppingcart ...");
        ItemElement[] items = new ItemElement[]{new Book(20, "1234"), new Book(100, "5678"),
            new Fruit(10, 2, "Banana"), new Fruit(5, 5, "Apple")};

        int total = calculatePrice(items);
        System.out.println("Total Cost = " + total);
    }

    private static int calculatePrice(ItemElement[] items) {
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int sum = 0;
        for (ItemElement item : items) {
            sum = sum + item.accept(visitor);
        }
        return sum;
    }
}
