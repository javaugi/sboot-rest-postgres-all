/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * In State pattern a class behavior changes based on its state. This type of
 * design pattern comes under behavior pattern.
 *
 * In State pattern, we create objects which represent various states and a
 * context object whose behavior varies as its state object changes. If we have
 * to change the behavior of an object based on its state, we can have a state
 * variable in the Object. Then use if-else condition block to perform different
 * actions based on the state. State design pattern is used to provide a
 * systematic and loosely coupled way to achieve this through Context and State
 * implementations.
 *
 * State Pattern Context is the class that has a State reference to one of the
 * concrete implementations of the State. Context forwards the request to the
 * state object for processing. Let’s understand this with a simple example.
 *
 * Suppose we want to implement a TV Remote with a simple button to perform
 * action. If the State is ON, it will turn on the TV and if state is OFF, it
 * will turn off the TV.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Step 1
Create an interface.

State Design Pattern Benefits
The benefits of using State pattern to implement polymorphic behavior is clearly
visible. The chances of error are less and it’s very easy to add more states for
additional behavior. Thus making our code more robust, easily maintainable and
flexible. Also State pattern helped in avoiding if-else or switch-case conditional
logic in this scenario.

State Pattern is very similar to Strategy Pattern,
 */
public class StatePattern {

    private static final Logger log = LoggerFactory.getLogger(StatePattern.class);

    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());

        System.out.println("Testing TVState ...");
        TVContext cxt = new TVContext();
        TVState tvStartState = new TVStartState();
        TVState tvStopState = new TVStopState();

        cxt.setState(tvStartState);
        cxt.doAction();

        cxt.setState(tvStopState);
        cxt.doAction();
    }
}
