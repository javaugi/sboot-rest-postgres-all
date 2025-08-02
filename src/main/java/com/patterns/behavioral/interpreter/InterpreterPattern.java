/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interpreter pattern provides a way to evaluate language grammar or
 * expression. This type of pattern comes under behavioral pattern. This pattern
 * involves implementing an expression interface which tells to interpret a
 * particular context. This pattern is used in SQL parsing, symbol processing
 * engine etc.
 *
 * The best example of interpreter design pattern is java compiler that
 * interprets the java source code into byte code that is understandable by JVM.
 * Google Translator is also an example of interpreter pattern where the input
 * can be in any language and we can get the output interpreted in another
 * language.
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Interpreter Pattern Example
To implement interpreter pattern, we need to create Interpreter context engine
    that will do the interpretation work.

Then we need to create different Expression implementations that will consume
    the functionalities provided by the interpreter context.

Finally we need to create the client that will take the input from user and
    decide which Expression to use and then generate output for the user.

Step 1
Create an expression interface.
Step 2
Create concrete classes implementing the above interface.

Important Points about Interpreter pattern
Interpreter pattern can be used when we can create a syntax tree for the grammar we have.
Interpreter design pattern requires a lot of error checking and a lot of
    expressions and code to evaluate them. It gets complicated when the grammar
    becomes more complicated and hence hard to maintain and provide efficiency.
java.util.Pattern and subclasses of java.text.Format are some of the examples of interpreter pattern used in JDK.
 */
public class InterpreterPattern {

    private static final Logger log = LoggerFactory.getLogger(InterpreterPattern.class);

    //Rule: Robert and John are male
    public static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //Rule: Julie is a married women
    public static Expression getMarriedWomanExpression() {
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println("John is male? " + isMale.interpret("John"));
        System.out.println("Julie is a married women? " + isMarriedWoman.interpret("Married Julie"));
        System.out.println("Julie is a married women? " + isMarriedWoman.interpret("Julie Married"));

        String str1 = "28 in Binary";
        String str2 = "28 in Hexadecimal";

        InterpreterPattern ec = new InterpreterPattern(new InterpreterContext());
        System.out.println(str1 + "= " + ec.interpret(str1));
        System.out.println(str2 + "= " + ec.interpret(str2));
    }

    public InterpreterContext ic;

    public InterpreterPattern(InterpreterContext i) {
        this.ic = i;
    }

    public String interpret(String str) {
        Expression1 exp = null;
        //create rules for expressions
        if (str.contains("Hexadecimal")) {
            exp = new IntToHexExpression(Integer.parseInt(str.substring(0, str.indexOf(" "))));
        } else if (str.contains("Binary")) {
            exp = new IntToBinaryExpression(Integer.parseInt(str.substring(0, str.indexOf(" "))));
        } else {
            return str;
        }

        return exp.interpret(ic);
    }
}
