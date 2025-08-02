package com.sisllc.mathformulas.ci.ch04;

public class Q42Graph {

    private Q42Node vertices[];
    public int count;

    public Q42Graph() {
        vertices = new Q42Node[6];
        count = 0;
    }

    public void addNode(Q42Node x) {
        if (count < 30) {
            vertices[count] = x;
            count++;
        } else {
            System.out.print("Graph full");
        }
    }

    public Q42Node[] getNodes() {
        return vertices;
    }
}
