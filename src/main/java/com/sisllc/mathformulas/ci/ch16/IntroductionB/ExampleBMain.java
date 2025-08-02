package com.sisllc.mathformulas.ci.ch16.IntroductionB;

public class ExampleBMain {

    public static void main(String args[]) {
        ThreadExample instance = new ThreadExample();
        instance.start();

        while (instance.count != 5) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        }
    }
}
