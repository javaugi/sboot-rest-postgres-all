package com.sisllc.mathformulas.ci.ch16.Question16_5;

public class ThreadMain {

    public static void main(String[] args) {
        FooBad foo = new FooBad();

        MyThread thread1 = new MyThread(foo, "first");
        MyThread thread2 = new MyThread(foo, "second");
        MyThread thread3 = new MyThread(foo, "third");

        thread3.start();
        thread2.start();
        thread1.start();
    }
}
