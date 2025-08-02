/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import com.spring5.entity.Answer;
import com.spring5.service.AnswerService;
import com.spring5.service.AssessmentService;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author javaugi
 */
@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;
    private final AssessmentService assessmentService;

    public AnswerController(AnswerService answerService, AssessmentService assessmentService) {
        this.answerService = answerService;
        this.assessmentService = assessmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Answer>> getAnswerById(@PathVariable Long id) {
        Answer answer = answerService.findById(id);
        if (answer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found");
        }
        EntityModel<Answer> model = EntityModel.of(answer)
            .add(linkTo(methodOn(AnswerController.class).getAnswerById(id)).withSelfRel())
            .add(linkTo(methodOn(AssessmentController.class)
                .getAssessmentById(answer.getAssessmentId())).withRel("assessment"));

        return ResponseEntity.ok(model);
    }
}
/*
The error occurs because you're trying to assign an EntityModel<Answer> to a variable of type AnswerModel. Let me help you fix this issue and explain the proper way to handle HATEOAS in Spring.

The Problem
You're mixing two different approaches:

EntityModel - Spring HATEOAS's representation model

AnswerModel - Your custom model class

Solution Options
Option 1: Convert to using EntityModel (Recommended)
java
Copy
@GetMapping("/{id}")
public ResponseEntity<EntityModel<Answer>> getAnswerById(@PathVariable Long id) {
    Answer answer = answerService.findById(id);
    if (answer == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found");
    }
    
    EntityModel<Answer> model = EntityModel.of(answer)
        .add(linkTo(methodOn(AnswerController.class).getAnswerById(id)).withSelfRel())
        .add(linkTo(methodOn(AssessmentController.class)
            .getAssessmentById(answer.getAssessmentId())).withRel("assessment"));
    
    return ResponseEntity.ok(model);
}
Option 2: Make AnswerModel extend RepresentationModel
java
Copy
public class AnswerModel extends RepresentationModel<AnswerModel> {
    private final Answer answer;
    
    public AnswerModel(Answer answer) {
        this.answer = answer;
    }
    
    // Getters and other methods
}

@GetMapping("/{id}")
public ResponseEntity<AnswerModel> getAnswerById(@PathVariable Long id) {
    Answer answer = answerService.findById(id);
    if (answer == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found");
    }
    
    AnswerModel model = new AnswerModel(answer);
    model.add(linkTo(methodOn(AnswerController.class).getAnswerById(id)).withSelfRel())
         .add(linkTo(methodOn(AssessmentController.class)
             .getAssessmentById(answer.getAssessmentId())).withRel("assessment"));
    
    return ResponseEntity.ok(model);
}
Option 3: Use a ModelMapper approach
java
Copy
@GetMapping("/{id}")
public ResponseEntity<AnswerModel> getAnswerById(@PathVariable Long id) {
    Answer answer = answerService.findById(id);
    if (answer == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found");
    }
    
    // Convert Answer to AnswerModel using your preferred mapping approach
    AnswerModel answerModel = modelMapper.map(answer, AnswerModel.class);
    
    // Create EntityModel wrapper
    EntityModel<AnswerModel> model = EntityModel.of(answerModel)
        .add(linkTo(methodOn(AnswerController.class).getAnswerById(id)).withSelfRel())
        .add(linkTo(methodOn(AssessmentController.class)
            .getAssessmentById(answer.getAssessmentId())).withRel("assessment"));
    
    return ResponseEntity.ok(model);
}
Key Points
Consistency: Choose one approach (EntityModel or custom model) and stick with it

HATEOAS Requirements: Your model must extend RepresentationModel to support add() method

Type Safety: Ensure return types match between method signature and implementation

Best Practice Recommendation
I recommend Option 2 (extending RepresentationModel) because:

It keeps your domain-specific model (AnswerModel)

It properly supports HATEOAS

It's more maintainable than wrapping everything in EntityModel

If you need to keep AnswerModel as a pure DTO without extending RepresentationModel, then Option 3 would be the better choice.
*/