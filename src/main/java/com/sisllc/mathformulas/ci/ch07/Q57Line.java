package com.sisllc.mathformulas.ci.ch07;

public class Q57Line {

    public Point start;
    public Point end;

    public Q57Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "Line from " + start.toString() + " to " + end.toString();
    }
}
