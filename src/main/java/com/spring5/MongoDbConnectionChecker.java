/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.spring5.mongodb.Customer;
import com.spring5.mongodb.CustomerRepository;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

/**
 *
 * @author javaugi
 */
@Component
@EnableMongoRepositories(basePackages = {MyApplication.PACKAGES_TO_SCAN})
public class MongoDbConnectionChecker implements CommandLineRunner {    
    private final static Logger log = LoggerFactory.getLogger(MongoDbConnectionChecker.class);
    
    @Value("${app.mongodb.enabled}")
    protected Boolean mongoDbEnabled;
    @Value("${app.mongodb.uri}")
    protected String mongoDbConnUri;
    
    private MongoClient mongoClient;
    
    private final CustomerRepository repository;
    public MongoDbConnectionChecker(CustomerRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (mongoDbEnabled && StringUtils.isNotEmpty(mongoDbConnUri)) {
            boolean mongoDbRunning = mongoDbConnectedRunning();
            log.info("Checking MongoDb database connectivity running {}", mongoDbRunning); 
            mongoDbDemo();
            mongoDbDemo2();
        }
    }
    
    private boolean mongoDbConnectedRunning() {        
        try {
            mongoClient = MongoClients.create(mongoDbConnUri);
            log.info("mongoDbConnectedRunning ...");
            return true;
        } catch (Exception e) {
            log.error("Failed to connect to Mongo database: " + e.getMessage(), e);
            // Optionally, you can halt the application startup
            // SpringApplication.exit(SpringApplicationContext.getAppContext(), () -> 1);
            // System.exit(1);
        }
        
        return false;
    }
    
    private void mongoDbDemo() {
        try {
            repository.deleteAll();

            // save a couple of customers
            repository.save(new Customer("Alice", "Smith"));
            repository.save(new Customer("Bob", "Smith"));

            // fetch all customers
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            for (Customer customer : repository.findAll()) {
                System.out.println(customer);
            }
            System.out.println();

            // fetch an individual customer
            System.out.println("Customer found with findByFirstName('Alice'):");
            System.out.println("--------------------------------");
            System.out.println(repository.findByFirstName("Alice"));

            System.out.println("Customers found with findByLastName('Smith'):");
            System.out.println("--------------------------------");
            for (Customer customer : repository.findByLastName("Smith")) {
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private void mongoDbDemo2() {
        try {
            MongoDatabase database = mongoClient.getDatabase("mydatabase");
            MongoCollection<Document> collection = database.getCollection("mycollection");

            Document doc = new Document("name", "John Doe")
                    .append("age", 30)
                    .append("city", "New York")
                    .append("hobbies", Arrays.asList("reading", "hiking"));

            collection.insertOne(doc);
            System.out.println("Document inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
