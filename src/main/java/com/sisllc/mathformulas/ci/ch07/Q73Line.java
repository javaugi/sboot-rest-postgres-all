package com.sisllc.mathformulas.ci.ch07;

public class Q73Line {

    static double epsilon = 0.000001;
    public double slope;
    public double yintercept;

    public Q73Line(double s, double y) {
        slope = s;
        yintercept = y;
    }

    public void print() {
        System.out.print("y = " + slope + "x + " + yintercept);
    }

    public boolean intersect(Q73Line line2) {
        return Math.abs(slope - line2.slope) > epsilon
                || Math.abs(yintercept - line2.yintercept) < epsilon;
    }
};
