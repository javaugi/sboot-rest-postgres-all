/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import org.springframework.context.annotation.Configuration;


@Configuration
public class GraphQlConfiguration {     
}

/*
A very short introduction to GraphQL​
GraphQL is a query language to retrieve data from a server. It is an alternative to REST, SOAP or gRPC.

Let's suppose we want to query the details for a specific book from an online store backend.

With GraphQL you send the following query to the server to get the details for the book with the id "book-1":

query bookDetails {
  bookById(id: "book-1"){
    id
    name
    pageCount
    author {
      firstName
      lastName
    }
  }
}

It basically says:

query a book with a specific id
get me the id, name, pageCount and author from that book
for the author, I want to know the firstName and lastName
The response is normal JSON:

{
  "bookById": {
    "id":"book-1",
    "name":"Harry Potter and the Philosopher's Stone",
    "pageCount":223,
    "author": {
      "firstName":"Joanne",
      "lastName":"Rowling"
    }
  }
}

One very important property of GraphQL is that it is statically typed: the server knows exactly the shape of every object you can query and any client can actually "introspect" the server and ask for the "schema". The schema describes what queries are possible and what fields you can get back. (Note: when we refer to schema here, we always refer to a "GraphQL Schema", which is not related to other schemas like "JSON Schema" or "Database Schema")

The schema for the above query looks like this:

type Query {
  bookById(id: ID): Book
}

type Book {
  id: ID
  name: String
  pageCount: Int
  author: Author
}

type Author {
  id: ID
  firstName: String
  lastName: String
}

GraphQL Java Overview​
GraphQL Java is the Java (server) implementation for GraphQL. There are several repositories in the GraphQL Java Github org. The most important one is the GraphQL Java Engine which is the basis for everything else.

The GraphQL Java Engine is only concerned with executing queries. It doesn't deal with any HTTP or JSON related topics. For these aspects, we will use Spring for GraphQL which takes care of exposing our API via Spring Boot over HTTP.

The main steps of creating a GraphQL Java server are:

Defining a GraphQL Schema. (see above example)
Deciding on how the actual data for a query is fetched.

Our example API: getting book details​
Our example app will be a simple API to get details for a specific book. This is in no way a comprehensive API, but it is enough for this tutorial.

This schema defines one top level field (in the type Query): bookById which returns the details of a specific book.

It also defines the type Book which has the fields: id, name, pageCount and author. author is of type Author, which is defined after Book.

The Domain Specific Language (shown above) used to describe a schema is called the Schema Definition Language

*/