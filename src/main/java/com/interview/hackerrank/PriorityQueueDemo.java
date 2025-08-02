/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author javaugi
 */
public class PriorityQueueDemo {
     private final static Scanner scan = new Scanner(System.in);
     private final static Priorities priorities = new Priorities();
     
     private static final String[] events = {"ENTER John 3.75 50","ENTER Mark 3.8 24","ENTER Shafaet 3.7 35","SERVED","SERVED",
            "ENTER Samiha 3.85 36","SERVED","ENTER Ashley 3.9 42","ENTER Maria 3.6 46","ENTER Anik 3.95 49","ENTER Dan 3.95 50","SERVED"};
     /*
     results Dan Ashley Shafaet Maria
     */
     private static final String[] events2 = {};
    
     public static void main(String[] args) {
         System.out.println("demo ...");
         demo();
         
         List<String> eventList = Arrays.asList(events);
         System.out.println("\n demo1 eventList=" + eventList);
         demo1(eventList) ;
     }
     
     private static void demo() {
        // Define a custom comparator to compare strings based on their length
        Comparator<String> stringLengthComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };

        // Create a PriorityQueue with initial capacity of 5 and the custom comparator
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(5, stringLengthComparator);

        // Add elements to the PriorityQueue
        priorityQueue.add("Apple");
        priorityQueue.add("Banana");
        priorityQueue.add("Kiwi");
        priorityQueue.add("Orange");
        priorityQueue.add("Grape");
        priorityQueue.add("Strawberry");

        // Print the elements in priority order (shortest string first)
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }             
     }
     
     private static void demo1(List<String> events) {
        /* int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- > 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        // */
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }         
     }
    
     private static void runScanner() {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- > 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }         
     }
}
/*
Explanation:
Student Class:
    Stores student information (ID, name, CGPA)
    Provides getter methods for accessing these properties

StudentComparator:

Implements the priority rules:
    Higher CGPA students come first
    For same CGPA, alphabetical name order
    For same CGPA and name, lower ID comes first

Priorities Class:
    Processes events using a priority queue
    ENTER events create new Student objects and add them to the queue
    SERVED events remove the highest priority student
    Returns remaining students in priority order

Solution Class:
    Handles input/output
    Reads events and passes them to the Priorities class
    Prints the names of remaining students or "EMPTY" if none left

Key Features:
    PriorityQueue is used to efficiently maintain students in priority order
    Custom Comparator ensures proper ordering based on the problem requirements
    Event Processing handles both ENTER and SERVED operations
    Output matches the expected format with students printed in priority order

This solution efficiently handles the priority queue operations while maintaining all the specified ordering rules. The time complexity is O(N log N) for N events due to the priority queue operations.
*/

/*
In computer science, a priority queue is an abstract data type which is like a regular queue, but where additionally each element 
    has a "priority" associated with it. In a priority queue, an element with high priority is served before an element with low priority. 

In this problem we will test your knowledge on Java Priority Queue.

There are a number of students in a school who wait to be served. Two types of events, ENTER and SERVED, can take place which are described below.

ENTER: A student with some priority enters the queue to be served.
SERVED: The student with the highest priority is served (removed) from the queue.
A unique id is assigned to each student entering the queue. The queue serves the students based on the following criteria (priority criteria):

The student having the highest Cumulative Grade Point Average (CGPA) is served first.
Any students having the same CGPA will be served by name in ascending case-sensitive alphabetical order.
Any students having the same CGPA and name will be served in ascending order of the id.
Create the following two classes:

The Student class should implement:
The constructor Student(int id, String name, double cgpa).
The method int getID() to return the id of the student.
The method String getName() to return the name of the student.
The method double getCGPA() to return the CGPA of the student.
The Priorities class should implement the method List<Student> getStudents(List<String> events) to process all the given events and return all 
    the students yet to be served in the priority order.
Input Format

The first line contains an integer, , describing the total number of events. Each of the  subsequent lines will be of the following two forms:

ENTER name CGPA id: The student to be inserted into the priority queue.
SERVED: The highest priority student in the queue was served.
The locked stub code in the editor reads the input and tests the correctness of the Student and Priorities classes implementation.

Constraints

Output Format

The locked stub code prints the names of the students yet to be served in the priority order. If there are no such student, then the code prints EMPTY.

Sample Input 0

12
ENTER John 3.75 50
ENTER Mark 3.8 24
ENTER Shafaet 3.7 35
SERVED
SERVED
ENTER Samiha 3.85 36
SERVED
ENTER Ashley 3.9 42
ENTER Maria 3.6 46
ENTER Anik 3.95 49
ENTER Dan 3.95 50
SERVED
Sample Output 0

Dan
Ashley
Shafaet
Maria
Explanation 0

In this case, the number of events is 12. Let the name of the queue be Q.

John is added to Q. So, it contains (John, 3.75, 50).
Mark is added to Q. So, it contains (John, 3.75, 50) and (Mark, 3.8, 24).
Shafaet is added to Q. So, it contains (John, 3.75, 50), (Mark, 3.8, 24), and (Shafaet, 3.7, 35).
Mark is served as he has the highest CGPA. So, Q contains (John, 3.75, 50) and (Shafaet, 3.7, 35).
John is served next as he has the highest CGPA. So, Q contains (Shafaet, 3.7, 35).
Samiha is added to Q. So, it contains (Shafaet, 3.7, 35) and (Samiha, 3.85, 36).
Samiha is served as she has the highest CGPA. So, Q contains (Shafaet, 3.7, 35).
Now, four more students are added to Q. So, it contains (Shafaet, 3.7, 35), (Ashley, 3.9, 42), (Maria, 3.6, 46), (Anik, 3.95, 49), and (Dan, 3.95, 50).
Anik is served because though both Anil and Dan have the highest CGPA but Anik comes first when sorted in alphabetic order. So, Q contains (Dan, 3.95, 50), (Ashley, 3.9, 42), (Shafaet, 3.7, 35), and (Maria, 3.6, 46).
As all events are completed, the name of each of the remaining students is printed on a new line.
*/
