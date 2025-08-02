/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.misc.jdinjection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://www.journaldev.com/2394/java-dependency-injection-design-pattern-example-tutorial
 *
 * Java Dependency Injection – Service Components: Messagingservice
 *
 * Java Dependency Injection – Service Consumer: Consumer
 *
 * Java Dependency Injection – Injectors Classes:
 *
 * As you can see that our application classes are responsible only for using
 * the service. Service classes are created in injectors. Also if we have to
 * further extend our application to allow facebook messaging, we will have to
 * write Service classes and injector classes only.
 *
 * So dependency injection implementation solved the problem with hard-coded
 * dependency and helped us in making our application flexible and easy to
 * extend. Now let’s see how easily we can test our application class by mocking
 * the injector and service classes.
 *
 * One of the best example of setter dependency injection is Struts2 Servlet API
 * Aware interfaces.
 *
 * So dependency injection implementation solved the problem with hard-coded
 * dependency and helped us in making our application flexible and easy to
 * extend. Now let’s see how easily we can test our application class by mocking
 * the injector and service classes.
 *
 * Whether to use Constructor based dependency injection or setter based is a
 * design decision and depends on your requirements. For example, if my
 * application can’t work at all without the service class then I would prefer
 * constructor based DI or else I would go for setter method based DI to use it
 * only when it’s really needed.
 *
 * Dependency Injection in Java is a way to achieve Inversion of control (IoC)
 * in our application by moving objects binding from compile time to runtime. We
 * can achieve IoC through Factory Pattern, Template Method Design Pattern,
 * Strategy Pattern and Service Locator pattern too.
 *
 * Spring Dependency Injection, Google Guice and Java EE CDI frameworks
 * facilitate the process of dependency injection through use of Java Reflection
 * API and java annotations. All we need is to annotate the field, constructor
 * or setter method and configure them in configuration xml files or classes.
 *
 * Benefits of Java Dependency Injection
 *
 * Some of the benefits of using Dependency Injection in Java are:
 *
 * Separation of Concerns Boilerplate Code reduction in application classes
 * because all work to initialize dependencies is handled by the injector
 * component Configurable components makes application easily extendable Unit
 * testing is easy with mock objects
 *
 * Disadvantages of Java Dependency Injection
 *
 * Java Dependency injection has some disadvantages too:
 *
 * If overused, it can lead to maintenance issues because effect of changes are
 * known at runtime. Dependency injection in java hides the service class
 * dependencies that can lead to runtime errors that would have been caught at
 * compile time.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class DependencyInjectionPattern {

    private static final Logger log = LoggerFactory.getLogger(DependencyInjectionPattern.class);

    public static void main(String[] args) {
        String msg = "Hi Pankaj";
        String email = "pankaj@abc.com";
        String phone = "4088888888";

        /*
        Java Dependency Injection – Service Components: MessagingService            - to sendMessage
        Java Dependency Injection – Injectors Classes:  MessagingServiceInjector    - to get Consumer
        Java Dependency Injection – Service Consumer: Consumer                      - to process message
         */
        //Send email
        MessageServiceInjector injector = new EmailServiceInjector();
        Consumer app = injector.getConsumer();
        app.processMessages(msg, email);

        //Send SMS
        injector = new SMSServiceInjector();
        app = injector.getConsumer();
        app.processMessages(msg, phone);
    }
}
