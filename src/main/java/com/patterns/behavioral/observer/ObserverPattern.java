/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GOF: Define a one-to-many dependency between objects so that when one object
 * changes state, all its dependents are notified and updated automatically.
 *
 * Observer pattern is used when there is one-to-many relationship between
 * objects such as if one object is modified, its depenedent objects are to be
 * notified automatically. Observer pattern falls under behavioral pattern
 * category.
 *
 * Implementation Observer pattern uses three actor classes. Subject, Observer
 * and Client. Subject is an object having methods to attach and detach
 * observers to a client object. We have created an abstract class Observer and
 * a concrete class Subject that is extending class Observer.
 *
 * Subject contains a list of observers to notify of any change in it’s state,
 * so it should provide methods using which observers can register and
 * unregister themselves. Subject also contain a method to notify all the
 * observers of any change and either it can send the update while notifying the
 * observer or it can provide another method to get the update.
 *
 * Observer should have a method to set the object to watch and another method
 * that will be used by Subject to notify them of any updates.
 *
 * Java provides inbuilt platform for implementing Observer pattern through
 * java.util.Observable class and java.util.Observer interface. However it’s not
 * widely used because the implementation is really simple and most of the times
 * we don’t want to end up extending a class just for implementing Observer
 * pattern as java doesn’t provide multiple inheritance in classes.
 *
 * Java Message Service (JMS) uses Observer design pattern along with Mediator
 * pattern to allow applications to subscribe and publish data to other
 * applications.
 *
 * Model-View-Controller (MVC) frameworks also use Observer pattern where Model
 * is the Subject and Views are observers that can register to get notified of
 * any change to the model.
 *
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */

/*
 * Observer design pattern is also called as publish-subscribe pattern. Some of it’s implementations are;

    java.util.EventListener in Swing
    javax.servlet.http.HttpSessionBindingListener
    javax.servlet.http.HttpSessionAttributeListener

 */
public class ObserverPattern {

    private static final Logger log = LoggerFactory.getLogger(ObserverPattern.class);

    public static void main(String[] args) {
        //create subject
        MyTopic topic = new MyTopic();

        //create observers
        Observer2 obj1 = new MyTopicSubscriber("Obj1");
        Observer2 obj2 = new MyTopicSubscriber("Obj2");
        Observer2 obj3 = new MyTopicSubscriber("Obj3");

        //register observers to the subject
        topic.register(obj1);
        topic.register(obj2);
        topic.register(obj3);

        //attach observer to subject
        obj1.setSubject(topic);
        obj2.setSubject(topic);
        obj3.setSubject(topic);

        //check if any update is available
        obj1.update();

        //now send message to subject
        topic.postMessage("New Message");

        System.out.println("\n\n\n More observer example ...");
        Subject subject = new Subject();

        Observer obs1 = new HexaObserver(subject);
        Observer obs2 = new OctalObserver(subject);
        Observer obs3 = new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }

}
