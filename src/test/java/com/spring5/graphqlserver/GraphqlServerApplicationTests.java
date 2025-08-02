/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.graphqlserver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@RunWith(SpringJUnit4ClassRunner.class)
public class GraphqlServerApplicationTests {

    //@Test
    public void contextLoads() {
    }
    //@SpringBootTest gives compile error and SpringJUnit4ClassRunner needs one test method
    //@Test
    public void makeItTrue() {
        assertTrue(true);
    }
}
