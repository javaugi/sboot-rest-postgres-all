/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author javaugi
 */
@Component
public class MyApplicationProfileChecker implements CommandLineRunner {
    private final static Logger log = LoggerFactory.getLogger(MyApplicationProfileChecker.class);
    
    private static final String MONGO_PROFILE = "mongo";
    private static final String FILE_PROFILE = "file";
    
    @Override
    public void run(String... args) throws Exception {
        log.info("MyApplicationProfileChecker CommandLineRunner ..."); 
    }    

    public void setupProfiles(ApplicationEnvironmentPreparedEvent event) {
        List<String> activeProfiles = Arrays.asList(event.getEnvironment().getActiveProfiles());
        boolean errorOccurred = false;

        if (activeProfiles.isEmpty()) {
            errorOccurred = true;
        } else if (!containsOperationModeProfile(activeProfiles)) {
            errorOccurred = true;
        }
        log.info("MyApplicationProfileChecker activeProfiles {} running {}", activeProfiles, errorOccurred); 

        if (errorOccurred) {
            try {
                Thread.sleep(1000);
                System.exit(1);
            } catch (InterruptedException ex) {                
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public boolean containsOperationModeProfile(List<String> activeProfiles) {
        return activeProfiles.contains(MONGO_PROFILE)
                || activeProfiles.contains(FILE_PROFILE);
    }
}
