/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.memento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * Memento pattern is used to restore state of an object to a previous state.
 * Memento pattern falls under behavioral pattern category.
 *
 * Implementation Memento pattern uses three actor classes. Memento contains
 * state of an object to be restored. Originator creates and stores states in
 * Memento objects and Caretaker object is responsible to restore object state
 * from Memento. We have created classes Memento, Originator and CareTaker.
 *
 * Memento design pattern is implemented with two objects – Originator and
 * Caretaker.
 *
 * Originator is the object whose state needs to be saved and restored and it
 * uses an inner class to save the state of Object. The inner class is called
 * Memento and it’s private, so that it can’t be accessed from other objects.
 *
 * Caretaker is the helper class that is responsible for storing and restoring
 * the Originator’s state through Memento object. Since Memento is private to
 * Originator, Caretaker can’t access it and it’s stored as an Object within the
 * caretaker.
 *
 * One of the best real life example is the text editors where we can save it’s
 * data anytime and use undo to restore it to previous saved state.
 *
 * Memento pattern is simple and easy to implement, one of the thing needs to
 * take care is that Memento class should be accessible only to the Originator
 * object. Also in client application, we should use caretaker object for saving
 * and restoring the originator state.
 *
 * Also if Originator object has properties that are not immutable, we should
 * use deep copy or cloning to avoid data integrity issue like I have used in
 * above example. We can use Serialization to achieve memento pattern
 * implementation that is more generic rather than Memento pattern where every
 * object needs to have it’s own Memento class implementation.
 *
 * One of the drawback is that if Originator object is very huge then Memento
 * object size will also be huge and use a lot of memory.
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class MementoPattern {

    private static final Logger log = LoggerFactory.getLogger(MementoPattern.class);

    public static void main(String[] args) {

        FileWriterMementoCaretaker caretaker = new FileWriterMementoCaretaker();

        FileWriterUtilMementoOriginator fileWriter = new FileWriterUtilMementoOriginator("data.txt");
        fileWriter.write("First Set of Data\n");
        System.out.println(fileWriter + "\n\n");

        // lets save the file
        caretaker.save(fileWriter);
        //now write something else
        fileWriter.write("Second Set of Data\n");

        //checking file contents
        System.out.println(fileWriter + "\n\n");

        //lets undo to last save
        caretaker.undo(fileWriter);

        //checking file content again
        System.out.println(fileWriter + "\n\n");

        System.out.println("\n\n\n more example ...");
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());

        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }

}
