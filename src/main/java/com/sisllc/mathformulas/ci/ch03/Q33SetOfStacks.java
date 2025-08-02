package com.sisllc.mathformulas.ci.ch03;

import java.util.ArrayList;

public class Q33SetOfStacks {

    ArrayList<Q33Stack> stacks = new ArrayList<Q33Stack>();
    public int capacity;

    public Q33SetOfStacks(int capacity) {
        this.capacity = capacity;
    }

    public Q33Stack getLastStack() {
        if (stacks.size() == 0) {
            return null;
        }
        return stacks.get(stacks.size() - 1);
    }

    public void push(int v) {
        Q33Stack last = getLastStack();
        if (last != null && !last.isFull()) { // add to last
            last.push(v);
        } else { // must create new stack
            Q33Stack stack = new Q33Stack(capacity);
            stack.push(v);
            stacks.add(stack);
        }
    }

    public int pop() {
        Q33Stack last = getLastStack();
        int v = last.pop();
        if (last.size == 0) {
            stacks.remove(stacks.size() - 1);
        }
        return v;
    }

    public int popAt(int index) {
        return leftShift(index, true);
    }

    public int leftShift(int index, boolean removeTop) {
        Q33Stack stack = stacks.get(index);
        int removed_item;
        if (removeTop) {
            removed_item = stack.pop();
        } else {
            removed_item = stack.removeBottom();
        }
        if (stack.isEmpty()) {
            stacks.remove(index);
        } else if (stacks.size() > index + 1) {
            int v = leftShift(index + 1, false);
            stack.push(v);
        }
        return removed_item;
    }

    public boolean isEmpty() {
        Q33Stack last = getLastStack();
        return last == null || last.isEmpty();
    }
}
