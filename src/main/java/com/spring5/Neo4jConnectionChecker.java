/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import com.spring5.graphql.Neo4jPerson;
import com.spring5.graphql.PersonRepository;
import java.util.Arrays;
import java.util.List;
import org.neo4j.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.stereotype.Component;

/**
 *
 * @author javaugi
 */
@Component
@EnableNeo4jAuditing
//@EnableNeo4jRepositories(basePackages = {MyApplication.PACKAGES_TO_SCAN})
public class Neo4jConnectionChecker implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(Neo4jConnectionChecker.class);
    
    @Value("${app.neo4j.enabled}")
    private Boolean neo4jDbEnabled;    

    private final Driver driver;
    private final PersonRepository personRepository;
    public Neo4jConnectionChecker(Driver driver, PersonRepository personRepository) {
        this.driver = driver;
        this.personRepository = personRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        log.info("Neo4jConnectionChecker neo4jDbEnabled {}", neo4jDbEnabled);        
        if (neo4jDbEnabled) {
            boolean neo4jRunning = neo4jDbConnectedRunning();
            log.info("Checking Neo4j database connectivity neo4jRunning {}", neo4jRunning);        
            if (neo4jRunning) {
                runNeo4jDemo();
            }
        }
    }

    private boolean neo4jDbConnectedRunning() {        
        try {
            driver.verifyConnectivity();
            log.info("Neo4j database is running and connected.");
            return true;
        } catch (Exception e) {
            log.error("Failed to connect to Neo4j database: " + e.getMessage(), e);
            // Optionally, you can halt the application startup
            // SpringApplication.exit(SpringApplicationContext.getAppContext(), () -> 1);
            // System.exit(1);
        }
        
        return false;
    }

    private void runNeo4jDemo() {
        personRepository.deleteAll();

        Neo4jPerson greg = new Neo4jPerson("Greg");
        Neo4jPerson roy = new Neo4jPerson("Roy");
        Neo4jPerson craig = new Neo4jPerson("Craig");

        List<Neo4jPerson> team = Arrays.asList(greg, roy, craig);

        log.info("Before linking up with Neo4j...");

        team.stream().forEach(person -> log.info("\t" + person.toString()));

        personRepository.save(greg);
        personRepository.save(roy);
        personRepository.save(craig);

        greg = personRepository.findByName(greg.getName());
        greg.worksWith(roy);
        greg.worksWith(craig);
        personRepository.save(greg);

        roy = personRepository.findByName(roy.getName());
        roy.worksWith(craig);
        // We already know that roy works with greg
        personRepository.save(roy);

        // We already know craig works with roy and greg
        log.info("Lookup each person by name...");
        team.stream().forEach(person -> log.info(
                "\t" + personRepository.findByName(person.getName()).toString()));

        List<Neo4jPerson> teammates = personRepository.findByTeammatesName(greg.getName());
        log.info("The following have Greg as a teammate...");
        teammates.stream().forEach(person -> log.info("\t" + person.getName()));
    }
}
