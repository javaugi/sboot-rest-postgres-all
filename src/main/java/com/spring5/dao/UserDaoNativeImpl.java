/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dao;

import com.spring5.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author javaugi
 */
public class UserDaoNativeImpl extends UserDaoImpl {
    
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Page<User> findAll(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        int max = pageable.toLimit().max();
        
        List<User> users = findAllList(startItem, max);
        System.out.println("user id list=" + findAllIdList(startItem, max));
        return convertListToPage(users, pageable, startItem);
    }

    private List<String> findAllIdList(int offset, int limit) {
        @SuppressWarnings("unchecked")
        Query query = entityManager.createNativeQuery("select u.id from User u");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
    
    private List<User> findAllList(int offset, int limit) {
        @SuppressWarnings("unchecked")
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
    
    private Page<User> convertListToPage(List<User> list, Pageable pageable, int startItem) {
        List<User> pageContent;
        if (list.size() < startItem) {
            pageContent = List.of(); // Return empty list if startItem is out of bounds
        } else {
            int toIndex = Math.min(startItem + pageable.getPageSize(), list.size());
            pageContent = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageContent, pageable, list.size());
    }    
}
