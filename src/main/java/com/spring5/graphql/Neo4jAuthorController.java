/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("neo4jauthor")
public class Neo4jAuthorController {
    private final Neo4jAuthorService authorService;

    @Autowired
    public Neo4jAuthorController(Neo4jAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Neo4jAuthor> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @PostMapping
    public Neo4jAuthor addAuthor(@RequestBody Neo4jAuthor author){
        return authorService.addAuthor(author);
    }

    @PutMapping
    public Neo4jAuthor updateAuthor(@RequestBody Neo4jAuthor author)  {
        return authorService.updateAuthor(author);
    }

    @DeleteMapping("/neo4jauthor/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId){
         authorService.deleteAuthor(authorId);
    }    
}
