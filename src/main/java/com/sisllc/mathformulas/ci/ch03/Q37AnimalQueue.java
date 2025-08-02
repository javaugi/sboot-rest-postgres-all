package com.sisllc.mathformulas.ci.ch03;

import java.util.LinkedList;

public class Q37AnimalQueue {

    LinkedList<Q37Dog> dogs = new LinkedList<Q37Dog>();
    LinkedList<Q37Cat> cats = new LinkedList<Q37Cat>();
    private int order = 0;

    public void enqueue(Q37Animal a) {
        a.setOrder(order);
        order++;
        if (a instanceof Q37Dog) {
            dogs.addLast((Q37Dog) a);
        } else if (a instanceof Q37Cat) {
            cats.addLast((Q37Cat) a);
        }
    }

    public Q37Animal dequeueAny() {
        if (dogs.size() == 0) {
            return dequeueCats();
        } else if (cats.size() == 0) {
            return dequeueDogs();
        }
        Q37Dog dog = dogs.peek();
        Q37Cat cat = cats.peek();
        if (dog.isOlderThan(cat)) {
            return dogs.poll();
        } else {
            return cats.poll();
        }
    }

    public Q37Animal peek() {
        if (dogs.size() == 0) {
            return cats.peek();
        } else if (cats.size() == 0) {
            return dogs.peek();
        }
        Q37Dog dog = dogs.peek();
        Q37Cat cat = cats.peek();
        if (dog.isOlderThan(cat)) {
            return dog;
        } else {
            return cat;
        }
    }

    public int size() {
        return dogs.size() + cats.size();
    }

    public Q37Dog dequeueDogs() {
        return dogs.poll();
    }

    public Q37Dog peekDogs() {
        return dogs.peek();
    }

    public Q37Cat dequeueCats() {
        return cats.poll();
    }

    public Q37Cat peekCats() {
        return cats.peek();
    }
}
