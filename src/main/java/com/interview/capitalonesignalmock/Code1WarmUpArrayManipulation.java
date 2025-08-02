/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author javaugi
 */
public class Code1WarmUpArrayManipulation {

    /*
        Problem 1: Warm-Up (Easy) – Array Manipulation
        Task: Given an array of integers, return the second-largest distinct value. If none, return -1.

        Example:
        Input: [5, 5, 4, 2, 3] → Output: 4
        Input: [1, 1, 1] → Output: -1    
    
    
    Key Points:
        Single-pass O(N) time, O(1) space.
        Handles duplicates and edge cases (all identical values).
    // */
    public static void main(String[] args) {
        Code1WarmUpArrayManipulation main = new Code1WarmUpArrayManipulation();
        //*
        main.testArrayManipulation();
        main.testObjManipulation();
        main.stringListManipulation();
        main.listManipulation();
        main.stringListIndex();
        // */
        main.testObjListManipulation();
        main.testStringListManipulation();
    }
    
    private void testStringListManipulation() {
        List<String> myList = new ArrayList(List.of("Smith", "Mannone", "Thurn", "Smith","Peterson"));
        
        List<String> filtered = myList.stream()
            .filter(name -> !"Smith".equals(name))
            .collect(Collectors.toList());
        System.out.println("1. filtered list " + filtered);
        
        myList = List.of("Smith", "Mannone", "Thurn", "Smith","Peterson");
        filtered = myList.stream()
            .filter(name -> !"Smith".equals(name))
            .collect(Collectors.toList());
        System.out.println("2. filtered list " + filtered);

        
        myList = Arrays.asList("Smith", "Mannone", "Thurn", "Smith","Peterson");
        filtered = myList.stream()
            .filter(name -> !"Smith".equals(name))
            .collect(Collectors.toList());
        System.out.println("3. filtered list " + filtered);
    }
    
    private void testObjListManipulation() {
        List<Person> myList = new ArrayList(List.of(
                new Person("Smith", "John", 40),
                new Person("Mannone", "Shannon", 40),
                new Person("Thurn", "Anita", 45),
                new Person("Smith", "Kevin", 43),
                new Person("Peterson", "Gary", 60)
        ));
        
        List<Person> filtered = myList.stream()
            .filter(person -> !"Smith".equals(person.getLastName()))
            .collect(Collectors.toList());
        System.out.println("1. filtered list " + filtered);
        
        myList = List.of(
                new Person("Smith", "John", 40),
                new Person("Mannone", "Shannon", 40),
                new Person("Thurn", "Anita", 45),
                new Person("Smith", "Kevin", 43),
                new Person("Peterson", "Gary", 60)
        );
        filtered = myList.stream()
            .filter(person -> !"Smith".equals(person.getLastName()))
            .collect(Collectors.toList());
        System.out.println("2. filtered list " + filtered);

        
        myList = Arrays.asList(
                new Person("Smith", "John", 40),
                new Person("Mannone", "Shannon", 40),
                new Person("Thurn", "Anita", 45),
                new Person("Smith", "Kevin", 43),
                new Person("Peterson", "Gary", 60)
        );
        filtered = myList.stream()
            .filter(person -> !"Smith".equals(person.getLastName()))
            .collect(Collectors.toList());
        System.out.println("3. filtered list " + filtered);
    }
    
    private void stringListIndex() {
        // Define an ArrayList and a string
        ArrayList<Integer> myList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        String myString = "Hello";

        // Slicing: subList for ArrayList and substring for Strings
        ArrayList<Integer> sliceList = new ArrayList<>(myList.subList(2, 4)); // [3, 4]
        System.out.println("1 stringListIndex sliceList=" + sliceList);
        String sliceString = myString.substring(1, 3); // "el"
        System.out.println("2 stringListIndex sliceString=" + sliceString);

        // Concatenation: addAll for lists and + operator for strings
        ArrayList<Integer> concatenateList = new ArrayList<>(myList);
        concatenateList.addAll(Arrays.asList(6, 7, 8)); // [1, 2, 3, 4, 5, 6, 7, 8]
        System.out.println("3 stringListIndex concatenateList=" + concatenateList);
        String concatenateString = myString + ", world!"; // "Hello, world!"
        System.out.println("4 stringListIndex concatenateString=" + concatenateString);
        
        // Finding the index of an element in a list or a string
        // indexOf returns the first occurrence index of the element
        // returns -1 if the list or the string doesn't contain the element
        int indexList = myList.indexOf(3); // 2 - Index of element '3'
        System.out.println("5 stringListIndex indexList=" + indexList);
        int indexString = myString.indexOf('e'); // 1 - Index of character 'e'
        System.out.println("6 stringListIndex indexString=" + indexString);

        // Sorting items in ArrayList in non-increasing order
        ArrayList<Integer> sortedList = new ArrayList<>(myList);
        sortedList.sort(Collections.reverseOrder()); // [5, 4, 3, 2, 1]        
        System.out.println("7 stringListIndex sortedList=" + sortedList);
    }
    
    private void listManipulation() {
        // Creating an ArrayList
        ArrayList<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "cherry"));
        System.out.println("1 listManipulation fruits=" + fruits);

        // Add a new element at the end
        fruits.add("date"); // ['apple', 'banana', 'cherry', 'date']
        System.out.println("2 listManipulation fruits=" + fruits);

        // Inserting an element at a specific position
        fruits.add(1, "bilberry"); // ['apple', 'bilberry', 'banana', 'cherry', 'date']
        System.out.println("3 listManipulation fruits=" + fruits);
        Collections.sort(fruits);
        System.out.println("3-2 listManipulation sorted fruits=" + fruits);
        fruits.sort(Collections.reverseOrder());
        System.out.println("3-3 listManipulation sorted reverse fruits=" + fruits);
        List<String> filtered = fruits.stream().filter(name -> !"apple".equals(name)).collect(Collectors.toList());
        System.out.println("3-4 listManipulation filtered fruits=" + filtered);

        // Removing a particular element
        fruits.remove("banana"); // ['apple', 'bilberry', 'cherry', 'date']
        System.out.println("4 listManipulation fruits=" + fruits);

        // Accessing elements using indexing
        String firstFruit = fruits.get(0); // apple
        String lastFruit = fruits.get(fruits.size() - 1); // date

        // Converting static array to ArrayList and vice versa
        String[] fruitArray = {"kiwi", "lemon", "mango"};
        System.out.println("5 listManipulation fruits=" + Arrays.toString(fruitArray));
        ArrayList<String> fruitList = new ArrayList<>(Arrays.asList(fruitArray));
        System.out.println("6 listManipulation fruits=" + fruitList);
        String[] newFruitArray = fruitList.toArray(new String[0]);        
        System.out.println("7 listManipulation fruits=" + Arrays.toString(newFruitArray));
    }
    
    private void stringListManipulation() {
        // Defining an ArrayList and a String
        // <Integer> specifies the type of Objects the ArrayList will hold
        ArrayList<Integer> myList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        String myString = "hello";

        // Now let's try to change the first element of both these features
        // set method changes the element at the specified index (0 in this case)
        myList.set(0, 100);
        // There is no method set for Strings
        // The following method does not change the string in place,
        // but returns a new string where 'h' is replaced with 'H'
        myString.replace('h', 'H');
        // So it is possible to obtain a new string like this:
        String newString = myString.replace('h', 'H');
        
        System.out.println(myList); // prints [100, 2, 3, 4]
        System.out.println(myString); // prints hello
        System.out.println(newString); // prints Hello        
    }
    
    private void testObjManipulation() {
        List<Person> people = createPersons();
        System.out.println("1 testObjManipulation original : \n" + people);
        Collections.sort(people);
        System.out.println("2 testObjManipulation sorted : \n" + people);
        people.sort(Collections.reverseOrder());
        System.out.println("3 testObjManipulation reverse sort : \n" + people);
        Collections.sort(people, Comparator.comparing(Person::getLastName));
        System.out.println("4 testObjManipulation srt by lastName: \n" + people);
        Collections.sort(people, Comparator.comparingInt(Person::getAge));
        System.out.println("5 testObjManipulation sorted age : \n" + people);
        Collections.sort(people, Comparator.comparingInt(Person::getAge).reversed());
        System.out.println("5-2 testObjManipulation reversed sorted age : \n" + people);
        
        List<Person> filteredPeople = people.stream()
            .filter(person -> !"Smith".equals(person.getLastName()))
            .collect(Collectors.toList());
        System.out.println("6 testObjManipulation filtered people: \n" + filteredPeople);
    }
    
    private List<Person> createPersons() {                
        return new ArrayList(List.of(
                new Person("Smith", "John", 40),
                new Person("Mannone", "Shannon", 40),
                new Person("Thurn", "Anita", 45),
                new Person("Smith", "Kevin", 43),
                new Person("Peterson", "Gary", 60)
        ));
    }
    
    private void testArrayManipulation() {
        int[] intArr = {1, 2, 3, 4, 5};
        System.out.println("1-1 the original array: " + Arrays.toString(intArr));
        int result = secondLargest(intArr);
        System.out.println("1-2 The secondLargest result is: " + result);

        int[] intArr2 = {5, 5, 4, 2, 3};
        System.out.println("2-1 the original array: " + Arrays.toString(intArr2));
        List<Integer> intList = Arrays.stream(intArr2)
                .boxed() 
                .sorted()
                .collect(Collectors.toList());
        System.out.println("2-2 the converted list: " + intList);
        Collections.sort(intList);
        System.out.println("2-3 the sorted list: " + intList);
        intList.sort(Collections.reverseOrder());
        System.out.println("2-4 the sorted in reverse order list: " + intList);

        intArr2 = intList.stream().mapToInt(Integer::intValue) // Convert Integer to int
                .toArray();
        System.out.println("2-5 the sorted array: " + Arrays.toString(intArr2));
        result = secondLargest(intArr2);
        System.out.println("2-6 The secondLargest result is: " + result);

        int[] intArr3 = {1, 1, 1};
        result = secondLargest(intArr3);
        System.out.println("3 The secondLargest result is: " + result + " from input: " + Arrays.toString(intArr3));
    }
    
    public int secondLargest(int[] nums) {
        if (nums.length < 1) {
            //throw new IllegalArgumentException("Array cannot be empty");
        }
        if (nums.length > 0 && nums.length < 2) {
            throw new IllegalArgumentException("Array must have two elements to find the second largest");
        }
        
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) {
                second = num;
            }
        }
        return second != Integer.MIN_VALUE ? second : -1;
    }

    @Entity
    @Table(name = "Person")
    public class Person implements Comparable<Person> {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private long id;

        @Column
        private String firstName;

        @Column
        private String lastName;

        @Column
        private int age;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
        
        @Override
        public int compareTo(Person other) {
            // Sort by name (alphabetically)
            return this.lastName.compareTo(other.lastName);

            // To sort by age (ascending):
            // return Integer.compare(this.age, other.age);
        }        
        
        public Person() {
        }
        
        public Person(String lastName, String firstName, int age) {
            this();
            this.lastName = lastName;
            this.firstName = firstName;
            this.age = age;
        }
        
        public Person builder(String lastName, String firstName, int age) {
            return  new Person(lastName, firstName, age);            
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\nPerson{");
            sb.append("id=").append(id);
            sb.append(", firstName=").append(firstName);
            sb.append(", lastName=").append(lastName);
            sb.append(", age=").append(age);
            sb.append('}');
            return sb.toString();
        }
        
    }
}
