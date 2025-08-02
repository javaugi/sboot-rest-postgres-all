/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.graphql;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/* 
/graph/user/graphqls
type Query {
    allUsers: [User]
    userById(id: ID!): User
}

type User {
    id: ID!
    name: String!
    email: String!
}
 */
@Service
public class GraphUserService {

    private static final List<GraphUser> users = Arrays.asList(
            new GraphUser(1L, "Alice", "alice@example.com"),
            new GraphUser(2L, "Bob", "bob@example.com")
    );

    public List<GraphUser> getAllUsers() {
        return users;
    }

    public GraphUser getUserById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }
}
   