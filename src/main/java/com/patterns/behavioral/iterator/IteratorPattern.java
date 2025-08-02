/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Iterator pattern is very commonly used design pattern in Java and .Net
 * programming environment. This pattern is used to get a way to access the
 * elements of a collection object in sequential manner without any need to know
 * its underlying representation.
 *
 * Iterator pattern falls under behavioral pattern category.
 *
 * GOF: Provides a way to access the elements of an aggregate object without
 * exposing its underlying represenation.
 *
 * Iterator pattern is used to provide a standard way to traverse through a
 * group of Objects. Iterator pattern is widely used in Java Collection
 * Framework. Iterator interface provides methods for traversing through a
 * collection.
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
Iterator Design Pattern Important Points
Iterator pattern is useful when you want to provide a standard way to iterate
    over a collection and hide the implementation logic from client program.
The logic for iteration is embedded in the collection itself and it helps client
    program to iterate over them easily.

Iterator Design Pattern in JDK
We all know that Collection framework Iterator is the best example of iterator
pattern implementation but do you know that java.util.Scanner class also
Implements Iterator interface. Read this post to learn about Java Scanner Class.
 */
public class IteratorPattern {

    private static final Logger log = LoggerFactory.getLogger(IteratorPattern.class);

    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for (Iterator iter = namesRepository.getIterator(); iter.hasNext();) {
            String name = (String) iter.next();
            System.out.println("Name : " + name);
        }

        ChannelCollection channels = populateChannels();
        ChannelIterator baseIterator = channels.iterator(ChannelTypeEnum.ALL);
        while (baseIterator.hasNext()) {
            Channel c = baseIterator.next();
            System.out.println(c.toString());
        }
        System.out.println("******");
        // Channel Type Iterator
        ChannelIterator englishIterator = channels.iterator(ChannelTypeEnum.ENGLISH);
        while (englishIterator.hasNext()) {
            Channel c = englishIterator.next();
            System.out.println(c.toString());
        }
    }

    private static ChannelCollection populateChannels() {
        ChannelCollection channels = new ChannelCollectionImpl();
        channels.addChannel(new Channel(98.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(99.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(100.5, ChannelTypeEnum.FRENCH));
        channels.addChannel(new Channel(101.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(102.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(103.5, ChannelTypeEnum.FRENCH));
        channels.addChannel(new Channel(104.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(105.5, ChannelTypeEnum.HINDI));
        channels.addChannel(new Channel(106.5, ChannelTypeEnum.FRENCH));
        return channels;
    }
}
