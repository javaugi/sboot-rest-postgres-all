package com.sisllc.mathformulas.ci.ch03;

public class Q33Stack {

    private int capacity;
    public Q33Node top;
    public Q33Node bottom;
    public int size = 0;

    public Q33Stack(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return capacity == size;
    }

    public void join(Q33Node above, Q33Node below) {
        if (below != null) {
            below.above = above;
        }
        if (above != null) {
            above.below = below;
        }
    }

    public boolean push(int v) {
        if (size >= capacity) {
            return false;
        }
        size++;
        Q33Node n = new Q33Node(v);
        if (size == 1) {
            bottom = n;
        }
        join(n, top);
        top = n;
        return true;
    }

    public int pop() {
        Q33Node t = top;
        top = top.below;
        size--;
        return t.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int removeBottom() {
        Q33Node b = bottom;
        bottom = bottom.above;
        if (bottom != null) {
            bottom.below = null;
        }
        size--;
        return b.value;
    }
}
