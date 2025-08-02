/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.interview;

import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class LambdaExpression {

    private static final Logger log = LoggerFactory.getLogger(LambdaExpression.class);

    interface NumericTest {

        boolean computeTest(int n);
    }

    interface MyGreeting {

        String processName(String str);
    }

    interface MyString {

        String myStringFunction(String str);
    }

    public static String reverseStr(MyString reverse, String str) {
        return reverse.myStringFunction(str);
    }

    interface MyGeneric<T> {

        T compute(T t);
    }

    interface Factorial<T> {

        T compute(T t);
    }

    public static void main(String args[]) {
        NumericTest isEven = (n) -> (n % 2) == 0;
        NumericTest isNegative = (n) -> (n < 0);
        // Output: false
        System.out.println(isEven.computeTest(5));
        // Output: true
        System.out.println(isNegative.computeTest(-5));

        MyGreeting morningGreeting = (str) -> "Good Morning " + str + "!";
        MyGreeting eveningGreeting = (str) -> "Good Evening " + str + "!";
        // Output: Good Morning Luis!
        System.out.println(morningGreeting.processName("Luis"));
        // Output: Good Evening Jessica!
        System.out.println(eveningGreeting.processName("Jessica"));

        MyString reverseStr = (str) -> {
            String result = "";

            for (int i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }

            return result;
        };
        // Output: omeD adbmaL
        System.out.println(reverseStr.myStringFunction("Lambda Demo"));

        MyString reverseStr2 = (str) -> {
            return new StringBuilder(str).reverse().toString();
        };
        // Output: omeD adbmaL
        System.out.println(reverseStr2.myStringFunction("Lambda Demo"));

        MyGeneric<String> reverse = (str) -> {
            String result = "";

            for (int i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }

            return result;
        };
        // Output: omeD adbmaL
        System.out.println(reverse.compute("Lambda Demo"));

        MyGeneric<String> reverse2 = (str) -> {
            StringBuilder sb = new StringBuilder();

            for (int i = str.length() - 1; i >= 0; i--) {
                sb.append(str.charAt(i));
            }

            return sb.toString();
        };
        // Output: omeD adbmaL
        System.out.println(reverse2.compute("Lambda Demo"));


        // Integer version of MyGeneric
        MyGeneric<Integer> factorial = (Integer n) -> {
            int result = 1;

            for (int i = 1; i <= n; i++) {
                result = i * result;
                System.out.println("factorial calc n=" + i + "-result=" + result);
            }

            return result;
        };
        // Output: 120
        System.out.println(factorial.compute(5));
        
        
        //Factorial
        // Integer version of MyGeneric
        Factorial<Integer> factorial2 = (Integer n) -> {
            int result = 1;

            for (int i = 1; i <= n; i++) {
                result = i * result;
                System.out.println("factorial calc n=" + i + "-result=" + result);
            }

            return result;
        };
        // Output: 720
        System.out.println(factorial2.compute(6));

        
        //Factorial
        // Integer version of MyGeneric
        Factorial<BigInteger> factorial3 = (BigInteger n) -> {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(i);
                //result = i * result;
                System.out.println("factorial calc n=" + i + "-result=" + result);
            }

            return result;
        };
        // Output: 5040
        System.out.println("factorial3  for 7=" + factorial3.compute(BigInteger.valueOf(7)));
    }
}
