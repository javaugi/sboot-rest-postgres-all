/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.jpapagination;

import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class EmployeeMain {
    private static final Logger log = LoggerFactory.getLogger(EmployeeMain.class);

    public static void main(String[] args) {
        EmployeeMain main = new EmployeeMain();
        main.run();
    }
    
    private void run() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        log.debug("EmployeeMain starts ...");
        log.debug("Contains employee  {}", context.containsBeanDefinition("employee"));
        System.out.println("Contains employee  " + context.containsBeanDefinition("employee"));
        System.out.println("Contains EmployeeClient  " + context.containsBeanDefinition("employeeClient"));

        EmployeeClient exampleClient = context.getBean(EmployeeClient.class);
        exampleClient.run();
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        emf.close();
        log.debug("EmployeeMain done");
    }
}
