package com.sisllc.mathformulas.ci.ch03;

public class Q34Main {

    public static void main(String[] args) {
        // Set up code.
        int n = 5;
        Q34Tower[] towers = new Q34Tower[3];
        for (int i = 0; i < 3; i++) {
            towers[i] = new Q34Tower(i);
        }
        for (int i = n - 1; i >= 0; i--) {
            towers[0].add(i);
        }

        // Copy and paste output into a .XML file and open it with internet explorer.
        //towers[0].print();
        towers[0].moveDisks(n, towers[2], towers[1]);
        //towers[2].print();
    }
}
