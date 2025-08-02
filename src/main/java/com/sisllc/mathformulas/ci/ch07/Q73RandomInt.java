package com.sisllc.mathformulas.ci.ch07;

public class Q73RandomInt {

    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Q73Line line1 = new Q73Line(randomInt(5), randomInt(1));
            Q73Line line2 = new Q73Line(randomInt(5), randomInt(2));
            line1.print();
            System.out.print(", ");
            line2.print();
            if (line1.intersect(line2)) {
                System.out.println("  YES");
            } else {
                System.out.println("  NO");
            }
        }
    }

}
