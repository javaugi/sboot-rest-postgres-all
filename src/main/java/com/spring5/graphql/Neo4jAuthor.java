/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.graphql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Neo4jAuthor")
public class Neo4jAuthor {
    @Id
    @GeneratedValue
    Long id;
    
    String name;
    
    @Relationship(type = "AUTHORED")
    List<Neo4jBook> books;    
}
