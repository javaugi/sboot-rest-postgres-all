/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import com.spring5.entity.Answer;
import com.spring5.entity.Assessment;
import com.spring5.model.AnswerModel;
import com.spring5.model.AssessmentModel;
import com.spring5.service.AnswerService;
import com.spring5.service.AssessmentService;
import com.spring5.service.PatientService;
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
@RestController
@RequestMapping("/assessments")
public class AssessmentController {
    private final AssessmentService assessmentService;
    private final PatientService patientService;
    private final AnswerService answerService;

    public AssessmentController(AssessmentService assessmentService, PatientService patientService, AnswerService answerService) {
        this.assessmentService = assessmentService;
        this.patientService = patientService;
        this.answerService = answerService;
    }

    @GetMapping
    public CollectionModel<AssessmentModel> getAllAssessments() {
        List<AssessmentModel> assessmentModels = assessmentService.findAll().stream()
                .map(assessment -> {
                    AssessmentModel model = new AssessmentModel(assessment);
                    model.add(linkTo(methodOn(AssessmentController.class).getAssessmentById(assessment.getId())).withSelfRel())
                        .add(linkTo(methodOn(PatientController.class).getPatientById(assessment.getPatientId())).withRel("patient"))
                        .add(linkTo(methodOn(AssessmentController.class).getAssessmentAnswers(assessment.getId())).withRel("answers"));
                    return model;                
                }).collect(Collectors.toList());

        return CollectionModel.of(assessmentModels)
                .add(linkTo(methodOn(AssessmentController.class).getAllAssessments()).withSelfRel())
                .add(linkTo(methodOn(AssessmentController.class).createAssessment(null)).withRel("create"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Assessment>> getAssessmentById(@PathVariable Long id) {
        Assessment assessment = assessmentService.findById(id);
        if (assessment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found");
        }
        //AssessmentModel assessmentModel = new AssessmentModel(assessment)
        EntityModel<Assessment> assessmentModel = EntityModel.of(assessment)            
                .add(linkTo(methodOn(AssessmentController.class).getAssessmentById(id)).withSelfRel())
                .add(linkTo(methodOn(AssessmentController.class).getAllAssessments()).withRel("assessments"))
                .add(linkTo(methodOn(PatientController.class).getPatientById(assessment.getPatientId())).withRel("patient"))
                .add(linkTo(methodOn(AssessmentController.class).getAssessmentAnswers(id)).withRel("answers"))
                .add(linkTo(methodOn(AssessmentController.class).updateAssessment(id, null)).withRel("update"));
        return ResponseEntity.ok(assessmentModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Assessment>> createAssessment(@RequestBody Assessment assessment) {
        Assessment createdAssessment = assessmentService.save(assessment);
        //AssessmentModel assessmentModel = new AssessmentModel(createdAssessment)
        EntityModel<Assessment> assessmentModel = EntityModel.of(createdAssessment)            
                .add(linkTo(methodOn(AssessmentController.class).getAssessmentById(createdAssessment.getId())).withSelfRel())
                .add(linkTo(methodOn(AssessmentController.class).getAllAssessments()).withRel("assessments"))
                .add(linkTo(methodOn(PatientController.class).getPatientById(createdAssessment.getPatientId())).withRel("patient"))
                .add(linkTo(methodOn(AssessmentController.class).getAssessmentAnswers(createdAssessment.getId())).withRel("answers"));
        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Assessment>> updateAssessment(@PathVariable Long id, @RequestBody Assessment updatedAssessment) {
        Assessment assessment = assessmentService.update(id, updatedAssessment);
        if (assessment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found");
        }
        
        //AssessmentModel assessmentModel = new AssessmentModel(assessment)
        EntityModel<Assessment> assessmentModel = EntityModel.of(assessment)                
                .add(linkTo(methodOn(AssessmentController.class).getAssessmentById(id)).withSelfRel())
                .add(linkTo(methodOn(AssessmentController.class).getAllAssessments()).withRel("assessments"))
                .add(linkTo(methodOn(PatientController.class).getPatientById(assessment.getPatientId())).withRel("patient"))
                .add(linkTo(methodOn(AssessmentController.class).getAssessmentAnswers(id)).withRel("answers"));
        return ResponseEntity.ok(assessmentModel);
    }

    @GetMapping("/{assessmentId}/answers")
    public CollectionModel<AnswerModel> getAssessmentAnswers(@PathVariable Long assessmentId) {
        Assessment assessment = assessmentService.findById(assessmentId);
        if (assessment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found");
        }
        List<Answer> answers = assessmentService.findAnswersByAssessmentId(assessmentId, answerService);
        List<AnswerModel> answerModels = answers.stream()
                .map(answer ->{
                    AnswerModel model = new AnswerModel(answer);
                    model.add(linkTo(methodOn(AnswerController.class).getAnswerById(answer.getId())).withSelfRel())
                        .add(linkTo(methodOn(AssessmentController.class).getAssessmentById(assessmentId)).withRel("assessment"));
                    return model;                    
                }).collect(Collectors.toList());

        return CollectionModel.of(answerModels)
                .add(linkTo(methodOn(AssessmentController.class).getAssessmentAnswers(assessmentId)).withSelfRel())
                .add(linkTo(methodOn(AssessmentController.class).getAssessmentById(assessmentId)).withRel("assessment"))
                .add(linkTo(methodOn(AssessmentController.class).createAnswer(assessmentId, null)).withRel("create"));
    }

    @PostMapping("/{assessmentId}/answers")
    public ResponseEntity<EntityModel<Answer>> createAnswer(@PathVariable Long assessmentId, @RequestBody Answer answer) {
        Assessment assessment = assessmentService.findById(assessmentId);
        if (assessment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found");
        }
        Answer createdAnswer = answerService.save(new Answer(null, assessmentId, answer.getQuestionId(), answer.getAnswerText()));
        //AnswerModel answerModel = new AnswerModel(createdAnswer)
        EntityModel<Answer> answerModel = EntityModel.of(createdAnswer)
                .add(linkTo(methodOn(AnswerController.class).getAnswerById(createdAnswer.getId())).withSelfRel())
                .add(linkTo(methodOn(AssessmentController.class).getAssessmentById(assessmentId)).withRel("assessment"));
        return ResponseEntity.status(HttpStatus.CREATED).body(answerModel);
    }    
}
