package com.sisllc.mathformulas.ci.ch03;

public abstract class Q37Animal {

    private int order;
    protected String name;

    public Q37Animal(String n) {
        name = n;
    }

    public abstract String name();

    public void setOrder(int ord) {
        order = ord;
    }

    public int getOrder() {
        return order;
    }

    public boolean isOlderThan(Q37Animal a) {
        return this.order < a.getOrder();
    }
}
