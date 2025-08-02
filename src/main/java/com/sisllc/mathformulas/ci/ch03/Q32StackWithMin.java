package com.sisllc.mathformulas.ci.ch03;

import java.util.Stack;

public class Q32StackWithMin extends Stack<Q32NodeWithMin> {

    public void push(int value) {
        int newMin = Math.min(value, min());
        super.push(new Q32NodeWithMin(value, newMin));
    }

    public int min() {
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return peek().min;
        }
    }
}
