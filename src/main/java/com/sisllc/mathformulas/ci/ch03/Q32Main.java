package com.sisllc.mathformulas.ci.ch03;

import com.sisllc.mathformulas.ci.lib.AssortedMethods;

public class Q32Main {

    public static void main(String[] args) {
        Q32StackWithMin stack = new Q32StackWithMin();
        Q32StackWithMin2 stack2 = new Q32StackWithMin2();
        for (int i = 0; i < 15; i++) {
            int value = AssortedMethods.randomIntInRange(0, 100);
            stack.push(value);
            stack2.push(value);
            System.out.print(value + ", ");
        }
        System.out.println('\n');
        for (int i = 0; i < 15; i++) {
            System.out.println("Popped " + stack.pop().value + ", " + stack2.pop());
            System.out.println("New min is " + stack.min() + ", " + stack2.min());
        }
    }

}
