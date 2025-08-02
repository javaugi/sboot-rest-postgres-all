/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mongodb;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class CustomerRepositoryTests {
    
    
    @Autowired
    Environment env;
    
    @Value("${app.mongodb.enabled}")
    protected boolean mongoDbEnabled;

    @Container
    @ServiceConnection
    static MongoDBContainer container = null;

    @Autowired
    CustomerRepository repository;

    Customer dave, oliver, carter;

    @BeforeEach
    public void setUp() {
        if (mongoDbEnabled) {
            container = new MongoDBContainer("mongo:7.0.2");
            
            repository.deleteAll();

            dave = repository.save(new Customer("Dave", "Matthews"));
            oliver = repository.save(new Customer("Oliver August", "Matthews"));
            carter = repository.save(new Customer("Carter", "Beauford"));
        }
    }

    @Test
    public void setsIdOnSave() {
        String id = null;
        if (mongoDbEnabled) {
            Customer dave = repository.save(new Customer("Dave", "Matthews"));
            id= dave.id;
            assertThat(id).isNotNull();
        } else {
            assertThat(id).isNull();
        }
    }

    //@Test
    public void findsByLastName() {

        List<Customer> result = repository.findByLastName("Beauford");

        //assertThat(result).extracting("firstName").contains("Carter");
    }

    //@Test
    public void findsByExample() {

        Customer probe = new Customer(null, "Matthews");

        List<Customer> result = repository.findAll(Example.of(probe));

        //assertThat(result).hasSize(2).extracting("firstName").contains("Dave", "Oliver August");
    }
}
