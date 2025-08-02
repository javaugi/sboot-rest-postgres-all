/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.Metamodel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class NativeQueryService {

    @Autowired
    private EntityManagerFactory emf;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    public void checkEntities() {
        try{
            System.out.println("EntityManagerFactory    Managed types: " + emf.getMetamodel().getEntities());
            System.out.println("EntityManager           Managed types: " + entityManager.getMetamodel().getEntities());
            System.out.println("SessionFactory          Managed types: " + sessionFactory.getMetamodel().getEntities());            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
