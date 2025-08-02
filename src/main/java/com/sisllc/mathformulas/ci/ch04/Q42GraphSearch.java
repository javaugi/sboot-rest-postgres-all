package com.sisllc.mathformulas.ci.ch04;

import java.util.LinkedList;

public class Q42GraphSearch {

    public enum State {
        Unvisited, Visited, Visiting;
    }

    public static void main(String a[]) {
        Q42Graph g = createNewGraph();
        Q42Node[] n = g.getNodes();
        Q42Node start = n[3];
        Q42Node end = n[5];
        System.out.println(search(g, start, end));
    }

    public static Q42Graph createNewGraph() {
        Q42Graph g = new Q42Graph();
        Q42Node[] temp = new Q42Node[6];

        temp[0] = new Q42Node("a", 3);
        temp[1] = new Q42Node("b", 0);
        temp[2] = new Q42Node("c", 0);
        temp[3] = new Q42Node("d", 1);
        temp[4] = new Q42Node("e", 1);
        temp[5] = new Q42Node("f", 0);

        temp[0].addAdjacent(temp[1]);
        temp[0].addAdjacent(temp[2]);
        temp[0].addAdjacent(temp[3]);
        temp[3].addAdjacent(temp[4]);
        temp[4].addAdjacent(temp[5]);
        for (int i = 0; i < 6; i++) {
            g.addNode(temp[i]);
        }
        return g;
    }

    public static boolean search(Q42Graph g, Q42Node start, Q42Node end) {
        LinkedList<Q42Node> q = new LinkedList<Q42Node>();
        for (Q42Node u : g.getNodes()) {
            u.state = State.Unvisited;
        }
        start.state = State.Visiting;
        q.add(start);
        Q42Node u;
        while (!q.isEmpty()) {
            u = q.removeFirst();
            if (u != null) {
                for (Q42Node v : u.getAdjacent()) {
                    if (v.state == State.Unvisited) {
                        if (v == end) {
                            return true;
                        } else {
                            v.state = State.Visiting;
                            q.add(v);
                        }
                    }
                }
                u.state = State.Visited;
            }
        }
        return false;
    }
}
