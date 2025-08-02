package com.sisllc.mathformulas.ci.ch03;

public class Q33Main {

    public static void main(String[] args) {
        int capacity_per_substack = 5;
        Q33SetOfStacks set = new Q33SetOfStacks(capacity_per_substack);
        for (int i = 0; i < 34; i++) {
            set.push(i);
        }
        for (int i = 0; i < 34; i++) {
            System.out.println("Popped " + set.pop());
        }
    }
}
