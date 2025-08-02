/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import com.spring5.entity.Question;
import com.spring5.model.QuestionModel;
import com.spring5.service.QuestionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author javaugi
 */
/*
How do I fix this collection error @GetMapping
    public CollectionModel<QuestionModel> getAllQuestions() {
        List<EntityModel<Question>> questionModels = questionService.findAll().stream()
                .map(question -> new QuestionModel(question)
                        .add(linkTo(methodOn(QuestionController.class).getQuestionById(question.getId())).withSelfRel()))
                .collect(Collectors.toList());

        //return CollectionModel.of(questionModels)
        return CollectionModel.of(EntityModel.of(questionModels))
                .add(linkTo(methodOn(QuestionController.class).getAllQuestions()).withSelfRel())
                .add(linkTo(methodOn(QuestionController.class).createQuestion(null)).withRel("create"));
    }
The error occurs because you're mixing incompatible types in your CollectionModel. Let me provide a clean solution with proper type handling:

The Problem
You're trying to combine:

List<EntityModel<Question>> (from your stream)

QuestionModel (your custom model class)

Wrapping with EntityModel.of() incorrectly

Corrected Solution
Option 1: If using QuestionModel extends RepresentationModel
java
Copy
@GetMapping
public CollectionModel<QuestionModel> getAllQuestions() {
    List<QuestionModel> questionModels = questionService.findAll().stream()
            .map(question -> {
                QuestionModel model = new QuestionModel(question);
                model.add(linkTo(methodOn(QuestionController.class)
                        .getQuestionById(question.getId())).withSelfRel());
                return model;
            })
            .collect(Collectors.toList());

    return CollectionModel.of(questionModels)
            .add(linkTo(methodOn(QuestionController.class).getAllQuestions()).withSelfRel())
            .add(linkTo(methodOn(QuestionController.class).createQuestion(null)).withRel("create"));
}
Option 2: If using EntityModel<Question>
java
Copy
@GetMapping
public CollectionModel<EntityModel<Question>> getAllQuestions() {
    List<EntityModel<Question>> questionModels = questionService.findAll().stream()
            .map(question -> EntityModel.of(question)
                    .add(linkTo(methodOn(QuestionController.class)
                            .getQuestionById(question.getId())).withSelfRel()))
            .collect(Collectors.toList());

    return CollectionModel.of(questionModels)
            .add(linkTo(methodOn(QuestionController.class).getAllQuestions()).withSelfRel())
            .add(linkTo(methodOn(QuestionController.class).createQuestion(null)).withRel("create"));
}
Option 3: Hybrid Approach (Recommended)
java
Copy
@GetMapping
public CollectionModel<QuestionModel> getAllQuestions() {
    List<QuestionModel> questionModels = questionService.findAll().stream()
            .map(question -> {
                QuestionModel model = new QuestionModel(question);
                model.add(linkTo(methodOn(QuestionController.class)
                        .getQuestionById(question.getId())).withSelfRel());
                return model;
            })
            .collect(Collectors.toList());

    return CollectionModel.of(questionModels,
            linkTo(methodOn(QuestionController.class).getAllQuestions()).withSelfRel(),
            linkTo(methodOn(QuestionController.class).createQuestion(null)).withRel("create"));
}
Key Fixes:
Consistent Type Usage: Stick with either QuestionModel or EntityModel<Question> throughout

Proper CollectionModel Construction: Don't wrap the list in EntityModel.of()

Cleaner Link Addition: Use the CollectionModel.of() overload that accepts varargs links

Best Practices:
Choose One Model Approach:

If QuestionModel contains business logic, extend RepresentationModel

If it's a simple DTO, use EntityModel wrapper

Link Creation:

Keep link creation close to the model construction

Use static imports for cleaner code:

java
Copy
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
Error Prevention:

Ensure your QuestionModel either:

Extends RepresentationModel<QuestionModel>, or

Is wrapped in EntityModel consistently

Example of proper QuestionModel class if choosing Option 1 or 3:

java
Copy
public class QuestionModel extends RepresentationModel<QuestionModel> {
    private final Question question;
    
    public QuestionModel(Question question) {
        this.question = question;
    }
    
    // Getters and other methods
}
*/
@RestController
@RequestMapping("/questions")
public class QuestionController {
private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public CollectionModel<QuestionModel> getAllQuestions() {
         List<QuestionModel> questionModels = questionService.findAll().stream()
            .map(question -> {
                QuestionModel model = new QuestionModel(question);
                model.add(linkTo(methodOn(QuestionController.class)
                        .getQuestionById(question.getId())).withSelfRel());
                return model;
            })
            .collect(Collectors.toList());

        return CollectionModel.of(questionModels)
                .add(linkTo(methodOn(QuestionController.class).getAllQuestions()).withSelfRel())
                .add(linkTo(methodOn(QuestionController.class).createQuestion(null)).withRel("create"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Question>> getQuestionById(@PathVariable Long id) {
        Question question = questionService.findById(id);
        if (question == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found");
        }
        //QuestionModel questionModel = new QuestionModel(question)
        EntityModel<Question> questionModel = EntityModel.of(question)
                .add(linkTo(methodOn(QuestionController.class).getQuestionById(id)).withSelfRel())
                .add(linkTo(methodOn(QuestionController.class).getAllQuestions()).withRel("questions"))
                .add(linkTo(methodOn(QuestionController.class).updateQuestion(id, null)).withRel("update"));
        return ResponseEntity.ok(questionModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Question>> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.save(question);
        //QuestionModel questionModel = new QuestionModel(createdQuestion)
        EntityModel<Question> questionModel = EntityModel.of(question)
                .add(linkTo(methodOn(QuestionController.class).getQuestionById(createdQuestion.getId())).withSelfRel())
                .add(linkTo(methodOn(QuestionController.class).getAllQuestions()).withRel("questions"));
        return ResponseEntity.status(HttpStatus.CREATED).body(questionModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Question>> updateQuestion(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        Question question = questionService.findById(id);
        if (question == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found");
        }
        Question updated = questionService.save(new Question(id, updatedQuestion.getText()));
        //QuestionModel questionModel = new QuestionModel(updated)
        EntityModel<Question> questionModel = EntityModel.of(updated)
                .add(linkTo(methodOn(QuestionController.class).getQuestionById(id)).withSelfRel())
                .add(linkTo(methodOn(QuestionController.class).getAllQuestions()).withRel("questions"));
        return ResponseEntity.ok(questionModel);
    }    
}
