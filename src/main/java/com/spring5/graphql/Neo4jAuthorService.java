/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Neo4jAuthorService {

    private final Neo4jAuthorRepository authorRepository;

    @Autowired
    public Neo4jAuthorService(Neo4jAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Neo4jAuthor> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Neo4jAuthor addAuthor(Neo4jAuthor author) {
        return authorRepository.save(author);
    }

    public Neo4jAuthor updateAuthor(Neo4jAuthor author) {
        Optional<Neo4jAuthor> authorFromDB = authorRepository.findById(author.getId());
        if (authorFromDB.isPresent()) {
            Neo4jAuthor authorFromDBVal = authorFromDB.get();
            authorFromDBVal.setBooks(author.getBooks());
            authorFromDBVal.setName(author.getName());
            authorRepository.save(authorFromDBVal);
        } else {
            return null;
        }
        return author;
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

}
