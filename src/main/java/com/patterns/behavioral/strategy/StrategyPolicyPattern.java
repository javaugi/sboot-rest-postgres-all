/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * In Strategy pattern, a class behavior or its algorithm can be changed at run
 * time. This type of design pattern comes under behavior pattern.
 *
 * In Strategy pattern, we create objects which represent various strategies and
 * a context object whose behavior varies as per its strategy object. The
 * strategy object changes the executing algorithm of the context object.
 *
 *
 * Strategy pattern is also known as Policy Pattern. We define multiple
 * algorithms and let client application pass the algorithm to be used as a
 * parameter.
 *
 * One of the best example of strategy pattern is Collections.sort() method that
 * takes Comparator parameter. Based on the different implementations of
 * Comparator interfaces, the Objects are getting sorted in different ways.
 *
 * For our example, we will try to implement a simple Shopping Cart where we
 * have two payment strategies – using Credit Card or using PayPal
 *
 * Strategy Design Pattern Important Points
 *
 * We could have used composition to create instance variable for strategies but
 * we should avoid that as we want the specific strategy to be applied for a
 * particular task. Same is followed in Collections.sort() and Arrays.sort()
 * method that take comparator as argument.
 *
 * Strategy Pattern is very similar to State Pattern. One of the difference is
 * that Context contains state as instance variable and there can be multiple
 * tasks whose implementation can be dependent on the state whereas in strategy
 * pattern strategy is passed as argument to the method and context object
 * doesn’t have any variable to store it.
 *
 * Strategy pattern is useful when we have multiple algorithms for specific task
 * and we want our application to be flexible to chose any of the algorithm at
 * runtime for specific task.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class StrategyPolicyPattern {

    private static final Logger log = LoggerFactory.getLogger(StrategyPolicyPattern.class);

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));

        System.out.println("\n\n shopping cart ...");
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("1234", 10);
        Item item2 = new Item("5678", 40);

        cart.addItem(item1);
        cart.addItem(item2);

        //pay by paypal
        cart.pay(new PaypalStrategy("myemail@example.com", "mypwd"));

        //pay by credit card
        cart.pay(new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15"));
    }

}
