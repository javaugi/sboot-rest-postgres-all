/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.chainresp;

import java.util.Scanner;

/**
 *
 * Chain of responsibility pattern is used to achieve loose coupling in software
 * design where a request from client is passed to a chain of objects to process
 * them. Then the object in the chain will decide themselves who will be
 * processing the request and whether the request is required to be sent to the
 * next object in the chain or not.
 *
 * As the name suggests, the chain of responsibility pattern creates a chain of
 * receiver objects for a request. This pattern decouples sender and receiver of
 * a request based on type of request. This pattern comes under behavioral
 * patterns.
 *
 * In this pattern, normally each receiver contains reference to another
 * receiver. If one object cannot handle the request then it passes the same to
 * the next receiver and so on
 *
 * Chain of Responsibility Pattern Examples in JDK
 * java.util.logging.Logger#log()
 *
 * javax.servlet.Filter#doFilter()
 *
 *
 * java.lang.Exception
 *
 * Let’s see the example of chain of responsibility pattern in JDK and then we
 * will proceed to implement a real life example of this pattern. We know that
 * we can have multiple catch blocks in a try-catch block code. Here every catch
 * block is kind of a processor to process that particular exception.
 *
 * So when any exception occurs in the try block, its send to the first catch
 * block to process. If the catch block is not able to process it, it forwards
 * the request to next object in chain i.e next catch block. If even the last
 * catch block is not able to process it, the exception is thrown outside of the
 * chain to the calling program.
 *
 *
 * Chain of Responsibility Design Pattern Important Points
 *
 * Client doesn’t know which part of the chain will be processing the request
 * and it will send the request to the first object in the chain. For example,
 * in our program ATMDispenseChain is unaware of who is processing the request
 * to dispense the entered amount.
 *
 * Each object in the chain will have it’s own implementation to process the
 * request, either full or partial or to send it to the next object in the
 * chain.
 *
 * Every object in the chain should have reference to the next object in chain
 * to forward the request to, its achieved by java composition.
 *
 *
 * Creating the chain carefully is very important otherwise there might be a
 * case that the request will never be forwarded to a particular processor or
 * there are no objects in the chain who are able to handle the request. In my
 * implementation, I have added the check for the user entered amount to make
 * sure it gets processed fully by all the processors but we might not check it
 * and throw exception if the request reaches the last object and there are no
 * further objects in the chain to forward the request to. This is a design
 * decision.
 *
 * Chain of Responsibility design pattern is good to achieve lose coupling but
 * it comes with the trade-off of having a lot of implementation classes and
 * maintenance problems if most of the code is common in all the
 * implementations.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Chain of Responsibility Design Pattern Important Points
Client doesn’t know which part of the chain will be processing the request and it
    will send the request to the first object in the chain. For example, in our
    program ATMDispenseChain is unaware of who is processing the request to dispense the entered amount.
Each object in the chain will have it’s own implementation to process the request,
    either full or partial or to send it to the next object in the chain.
Every object in the chain should have reference to the next object in chain to
    forward the request to, its achieved by java composition.
Creating the chain carefully is very important otherwise there might be a case
    that the request will never be forwarded to a particular processor or there
    are no objects in the chain who are able to handle the request. In my implementation,
    I have added the check for the user entered amount to make sure it gets processed
    fully by all the processors but we might not check it and throw exception if the
    request reaches the last object and there are no further objects in the chain to
    forward the request to. This is a design decision.
Chain of Responsibility design pattern is good to achieve lose coupling but it comes
    with the trade-off of having a lot of implementation classes and maintenance
    problems if most of the code is common in all the implementations.

Chain of Responsibility Pattern Examples in JDK
java.util.logging.Logger#log()
javax.servlet.Filter#doFilter()

 */
public class ChainOfResponsibilityPattern {

    private static AbstractLogger getChainOfLoggers() {

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    private DispenseChain c1;

    public ChainOfResponsibilityPattern() {
        // initialize the chain
        this.c1 = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();

        // set the chain of responsibility
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        System.out.println("\n\n INFO LOGGING ...");
        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

        System.out.println("\n\n DEBUG LOGGING ...");
        loggerChain.logMessage(AbstractLogger.DEBUG, "This is an debug level information.");
        System.out.println("\n\n ERROR LOGGING ...");
        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");

        System.out.println("\n\n ATM Currency Dispenser ...");
        ChainOfResponsibilityPattern atmDispenser = new ChainOfResponsibilityPattern();
        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10s.");
                return;
            }
            // process the request
            atmDispenser.c1.dispense(new Currency(amount));
        }
    }
}
