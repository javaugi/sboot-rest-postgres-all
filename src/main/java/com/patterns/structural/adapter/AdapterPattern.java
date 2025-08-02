/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adapter pattern works as a bridge between two incompatible interfaces. This
 * type of design pattern comes under structural pattern as this pattern
 * combines the capability of two independent interfaces.
 *
 * This pattern involves a single class which is responsible to join
 * functionalities of independent or incompatible interfaces. A real life
 * example could be a case of card reader which acts as an adapter between
 * memory card and a laptop. You plugin the memory card into card reader and
 * card reader into the laptop so that memory card can be read via laptop.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Step 1
Create interfaces for Media Player and Advanced Media Player.

Step 2
Create concrete classes implementing the AdvancedMediaPlayer interface.

Step 3
Create adapter class implementing the MediaPlayer interface.

Step 4
Create concrete class implementing the MediaPlayer interface.

Step 5
Use the AudioPlayer to play different types of audio formats.



Two Way Adapter Pattern
While implementing Adapter pattern, there are two approaches – class adapter and
    object adapter – however both these approaches produce same result.


Class Adapter – This form uses java inheritance and extends the source
    interface, in our case Socket class.
Object Adapter – This form uses Java Composition and adapter contains
    the source object.

Adapter Design Pattern Example in JDK
Some of the adapter design pattern example I could easily find in JDK classes are;

    java.util.Arrays#asList()
    java.io.InputStreamReader(InputStream) (returns a Reader)
    java.io.OutputStreamWriter(OutputStream) (returns a Writer)

 */
public class AdapterPattern {

    private static final Logger log = LoggerFactory.getLogger(AdapterPattern.class);

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");

        System.out.println("\n\n\n moe testing ");
        testClassAdapter();
        testObjectAdapter();
    }

    private static void testObjectAdapter() {
        SocketAdapter sockAdapter = new SocketObjectAdapterImpl();
        Volt v3 = getVolt(sockAdapter, 3);
        Volt v12 = getVolt(sockAdapter, 12);
        Volt v120 = getVolt(sockAdapter, 120);
        System.out.println("v3 volts using Object Adapter=" + v3.getVolts());
        System.out.println("v12 volts using Object Adapter=" + v12.getVolts());
        System.out.println("v120 volts using Object Adapter=" + v120.getVolts());
    }

    private static void testClassAdapter() {
        SocketAdapter sockAdapter = new SocketClassAdapterImpl();
        Volt v3 = getVolt(sockAdapter, 3);
        Volt v12 = getVolt(sockAdapter, 12);
        Volt v120 = getVolt(sockAdapter, 120);
        System.out.println("v3 volts using Class Adapter=" + v3.getVolts());
        System.out.println("v12 volts using Class Adapter=" + v12.getVolts());
        System.out.println("v120 volts using Class Adapter=" + v120.getVolts());
    }

    private static Volt getVolt(SocketAdapter sockAdapter, int i) {
        switch (i) {
            case 3:
                return sockAdapter.get3Volt();
            case 12:
                return sockAdapter.get12Volt();
            case 120:
                return sockAdapter.get120Volt();
            default:
                return sockAdapter.get120Volt();
        }
    }
}
