/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

/*
The @QueryMapping annotation binds this method to a query, a field under the Query type. The query field is then 
    determined from the method name, bookById. It could also be declared on the annotation. Spring for GraphQL uses 
    RuntimeWiring.Builder to register the handler method as a graphql.schema.DataFetcher for the query field bookById.

In GraphQL Java, DataFetchingEnvironment provides access to a map of field-specific argument values. Use the @Argument 
    annotation to have an argument bound to a target object and injected into the handler method. By default, the method 
    parameter name is used to look up the argument. The argument name can be specified in the annotation.

The @SchemaMapping annotation maps a handler method to a field in the GraphQL schema and declares it to be the DataFetcher 
    for that field. The field name defaults to the method name, and the type name defaults to the simple class name of the 
    source/parent object injected into the method. In this example, the field defaults to author and the type defaults to Book. 
    The type and field can be specified in the annotation.
*/
@Controller
public class BookController {
    
    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }
}

/* 

Boot the application​
Start your Spring application.

Navigate to http://localhost:8080/graphiql or your custom URL.

Run the query​
Type in the query and hit the play button at the top of the window.

example query - put the cursor in front the word query and then click the submit arrow

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


*/