package com.sisllc.mathformulas.ci.ch03;

public class Q37Main {

    public static void main(String[] args) {
        Q37AnimalQueue animals = new Q37AnimalQueue();
        animals.enqueue(new Q37Cat("Callie"));
        animals.enqueue(new Q37Cat("Kiki"));
        animals.enqueue(new Q37Dog("Fido"));
        animals.enqueue(new Q37Dog("Dora"));
        animals.enqueue(new Q37Cat("Kari"));
        animals.enqueue(new Q37Dog("Dexter"));
        animals.enqueue(new Q37Dog("Dobo"));
        animals.enqueue(new Q37Cat("Copa"));

        System.out.println(animals.dequeueAny().name());
        System.out.println(animals.dequeueAny().name());
        System.out.println(animals.dequeueAny().name());

        animals.enqueue(new Q37Dog("Dapa"));
        animals.enqueue(new Q37Cat("Kilo"));

        while (animals.size() != 0) {
            System.out.println(animals.dequeueAny().name());
        }
    }

}
