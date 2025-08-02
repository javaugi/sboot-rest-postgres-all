/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GOF: Allows loose coupling by encapsulating the way disparate sets of objects
 * interact and communicate with each other. Allows for the actions of each
 * object set to vary independently of one another.
 *
 * Mediator pattern is used to reduce communication complexity between multiple
 * objects or classes. This pattern provides a mediator class which normally
 * handles all the communications between different classes and supports easy
 * maintenance of the code by loose coupling. Mediator pattern falls under
 * behavioral pattern category.
 *
 * so it deals with the behaviors of objects. Mediator design pattern is used to
 * provide a centralized communication medium between different objects in a
 * system.
 *
 * Mediator design pattern is very helpful in an enterprise application where
 * multiple objects are interacting with each other. If the objects interact
 * with each other directly, the system components are tightly-coupled with each
 * other that makes higher maintainability cost and not hard to extend. Mediator
 * pattern focuses on provide a mediator between objects for communication and
 * help in implementing lose-coupling between objects.
 *
 * Air traffic controller is a great example of mediator pattern where the
 * airport control room works as a mediator for communication between different
 * flights. Mediator works as a router between objects and it can have it’s own
 * logic to provide way of communication.
 *
 * The system objects that communicate each other are called Colleagues. Usually
 * we have an interface or abstract class that provides the contract for
 * communication and then we have concrete implementation of mediators.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */

/*
Mediator Pattern Example in JDK
    java.util.Timer class scheduleXXX() methods
    Java Concurrency Executor execute() method.
    java.lang.reflect.Method invoke() method.

Mediator Design Pattern Important Points
Mediator pattern is useful when the communication logic between objects is complex,
    we can have a central point of communication that takes care of communication logic.
Java Message Service (JMS) uses Mediator pattern along with Observer pattern to allow
    applications to subscribe and publish data to other applications.
We should not use mediator pattern just to achieve lose-coupling because if the number
    of mediators will grow, then it will become hard to maintain them.
 */
public class MediatorPattern {

    private static final Logger log = LoggerFactory.getLogger(MediatorPattern.class);

    public static void main(String[] args) {
        ChatRoomUser robert = new ChatRoomUser("Robert");
        ChatRoomUser john = new ChatRoomUser("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");

        System.out.println("\n\n\n ...");
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Pankaj");
        User user2 = new UserImpl(mediator, "Lisa");
        User user3 = new UserImpl(mediator, "Saurabh");
        User user4 = new UserImpl(mediator, "David");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hi All");
    }
}
