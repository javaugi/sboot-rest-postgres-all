/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.command;

/**
 * Command pattern is a data driven design pattern and falls under behavioral
 * pattern category. A request is wrapped under an object as command and passed
 * to invoker object. Invoker object looks for the appropriate object which can
 * handle this command and passes the command to the corresponding object which
 * executes the command.
 *
 *
 * Command Pattern
 *
 * “An object that contains a symbol, name or key that represents a list of
 * commands, actions or keystrokes”. This is the definition of a macro, one that
 * should be familiar to any computer user. From this idea the Command design
 * pattern was given birth. The Macro represents, at some extent, a command that
 * is built from the reunion of a set of other commands, in a given order. Just
 * as a macro, the Command design pattern encapsulates commands (method calls)
 * in objects allowing us to issue requests without knowing the requested
 * operation or the requesting object. Command design pattern provides the
 * options to queue commands, undo/redo actions and other manipulations.
 *
 * Intent
 *
 * - encapsulate a request in an object
 *
 * - allows the parameterization of clients with different requests
 *
 * - allows saving the requests in a queue
 *
 *
 * The classes participating in the pattern are:
 *
 * - Command - declares an interface for executing an operation;
 *
 * - ConcreteCommand - extends the Command interface, implementing the Execute
 * method by invoking the corresponding operations on Receiver. It defines a
 * link between the Receiver and the action.
 *
 * - Client - creates a ConcreteCommand object and sets its receiver;
 *
 * - Invoker - asks the command to carry out the request;
 *
 * - Receiver - knows how to perform the operations;
 *
 * The Client asks for a command to be executed. The Invoker takes the command,
 * encapsulates it and places it in a queue, in case there is something else to
 * do first, and the ConcreteCommand that is in charge of the requested command,
 * sending its result to the Receiver.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class CommandPattern {

    //this represent the client who ConcreteCommand object and sets its receiver;
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStockOrder buyStockOrder = new BuyStockOrder(abcStock);
        SellStockOrder sellStockOrder = new SellStockOrder(abcStock);

        BrokerInvoker broker = new BrokerInvoker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}
