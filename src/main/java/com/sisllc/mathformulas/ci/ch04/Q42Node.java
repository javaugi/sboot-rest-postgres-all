package com.sisllc.mathformulas.ci.ch04;

class Q42Node {

    private Q42Node adjacent[];
    public int adjacentCount;
    private String vertex;
    public Q42GraphSearch.State state;

    public Q42Node(String vertex, int adjacentLength) {
        this.vertex = vertex;
        adjacentCount = 0;
        adjacent = new Q42Node[adjacentLength];
    }

    public void addAdjacent(Q42Node x) {
        if (adjacentCount < 30) {
            this.adjacent[adjacentCount] = x;
            adjacentCount++;
        } else {
            System.out.print("No more adjacent can be added");
        }
    }

    public Q42Node[] getAdjacent() {
        return adjacent;
    }

    public String getVertex() {
        return vertex;
    }

    @Override
    public String toString() {
        return "Q42Node{" + "adjacentCount=" + adjacentCount + ", vertex=" + vertex + '}';
    }

}
