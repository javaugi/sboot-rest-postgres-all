/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.jpapagination;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Component
public class EmployeeClient {

    public static final String[] firstNames = {"Abbey", "Adam", "Alan", "Albert", "Alice", "Amy", "Jax", "John", "Jonathan", "Jeffrey", "Barbara", "Ben", "Bernie",
        "Byron", "Carl", "Carol", "Cathy", "Dan", "David", "Mark", "Mike", "Kevin", "Will"};
    public static final String[] lastNames = {"Smith", "Lee", "Barkley", "Green", "Clinton", "Bloom", "Bryant", "Collins", "Gere", "Dewey"};
    public static final String[] departments = {"Sales", "Marketing", "IT", "HR", "Customer Relations", "Product Management", "Researches"};
    public static final Integer[] salaries = {83578, 55672, 84567, 102321, 102593, 67345, 73895, 93461, 89410, 135921, 176543, 197043, 89054};
    private static final int PAGE_SIZE = 10;

    @Autowired
    private EmployeeRepository repo;

    @PostConstruct
    public void init() {
        createEmployees();
        System.out.println("EmployeeClient creating employees ");
    }

    public void run() {
        List<Employee> employees = createEmployees();
        repo.saveAll(employees);

        System.out.println(" ***** finding all employees --");
        Iterable<Employee> all = repo.findAll();
        all.forEach(System.out::println);

        System.out.println(" -- paginating where dept is Sales --");
        Slice<Employee> slice = null;
        Pageable pageable = PageRequest.of(0, 3, Sort.by("salary"));
        while (true) {
            slice = repo.findByDept("Sales", pageable);
            int number = slice.getNumber();
            int numberOfElements = slice.getNumberOfElements();
            int size = slice.getSize();
            System.out.printf("slice info - page number %s, numberOfElements: %s, size: %s%n",
                    number, numberOfElements, size);
            List<Employee> employeeList = slice.getContent();
            employeeList.forEach(System.out::println);
            if (!slice.hasNext()) {
                break;
            }
            pageable = slice.nextPageable();
        }
    }

    private List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList();
        Employee emp;
        Random rand = new Random();
        int i = 0;
        while (i < 50) {
            i++;
            emp = Employee.create(Arrays.asList(firstNames).get(rand.nextInt(firstNames.length))
                    + Arrays.asList(lastNames).get(rand.nextInt(lastNames.length)),
                    Arrays.asList(departments).get(rand.nextInt(departments.length)),
                    Arrays.asList(salaries).get(rand.nextInt(salaries.length)));
            employees.add(emp);
        }
        return employees;

        /* Arrays.asList(
                Employee.create("Diana", "Sales", 2000),
                Employee.create("Mike", "Sales", 1000),
                Employee.create("Rose", "IT", 4000),
                Employee.create("Sara", "Sales", 3000),
                Employee.create("Andy", "Sales", 3000),
                Employee.create("Charlie", "Sales", 2500),
                Employee.create("Jim", "Sales", 4500),
                Employee.create("Sam", "Sales", 2500),
                Employee.create("Adam", "Sales", 5000),
                Employee.create("Jane", "Sales", 5500),
                Employee.create("Joe", "Sales", 1500)
        );
         */
    }
}
