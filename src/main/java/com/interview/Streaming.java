/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.interview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
 * What is Stream? Stream represents a sequence of objects from a source, which
 * supports aggregate operations. Following are the characteristics of a Stream
 * 1 Sequence of elements − A stream provides a set of elements of specific type
 * in a sequential manner. A stream gets/computes elements on demand. It never
 * stores the elements.
 * 2. Source − Stream takes Collections, Arrays, or I/O resources as input source.
 * 3. Aggregate operations − Stream supports aggregate
 * operations like filter, map, limit, reduce, find, match, and so on.
 * 4. Pipelining − Most of the stream operations return stream itself so that their
 * result can be pipelined. These operations are called intermediate operations
 * and their function is to take input, process them, and return output to the
 * target. collect() method is a terminal operation which is normally present at
 * the end of the pipelining operation to mark the end of the stream.
 * 5. Automatic iterations − Stream operations do the iterations internally over the source
 * elements provided, in contrast to Collections where explicit iteration is
 * required.
 */
public class Streaming {

    private static final Logger log = LoggerFactory.getLogger(Streaming.class);

    public static void main(String[] args) {

        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        log.info("Printing {}", myList);

        log.info("EX1 ", myList);
        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        log.info("EX2 ");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        log.info("Ex3 ");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
        log.info("Parallel ");
        parallelStream();

        log.info("Sorted \n ");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        log.info("persons \n ");
        persons();

        log.info("parallelStreams \n ");
        parallelStreams();
    }

    public static void parallelStream() {
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                s, Thread.currentThread().getName()));
    }

    static class Person {

        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static List<Person> persons
            = Arrays.asList(
                    new Person("Max", 18),
                    new Person("Peter", 23),
                    new Person("Pamela", 23),
                    new Person("David", 12));

    private static void persons() {
        List<Person> filtered
                = persons
                        .stream()
                        .filter(p -> p.name.startsWith("P"))
                        .collect(Collectors.toList());

        System.out.println(filtered);    // [Peter, Pamela]

        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
        // age 18: [Max]
        // age 23: [Peter, Pamela]
        // age 12: [David]

        Collector<Person, StringJoiner, String> personNameCollector
                = Collector.of(
                        () -> new StringJoiner(" | "), // supplier
                        (j, p) -> j.add(p.name.toUpperCase()), // accumulator
                        (j1, j2) -> j1.merge(j2), // combiner
                        StringJoiner::toString);                // finisher

        String names = persons
                .stream()
                .collect(personNameCollector);

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID
    }

    private static void parallelStreams() {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());    // 3

        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                s, Thread.currentThread().getName()));

        persons
                .parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s [%s]\n",
                                    sum, p, Thread.currentThread().getName());
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s [%s]\n",
                                    sum1, sum2, Thread.currentThread().getName());
                            return sum1 + sum2;
                        });
    }
}
