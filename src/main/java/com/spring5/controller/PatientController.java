/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import com.spring5.entity.Assessment;
import com.spring5.entity.Patient;
import com.spring5.model.AssessmentModel;
import com.spring5.model.PatientModel;
import com.spring5.service.AssessmentService;
import com.spring5.service.PatientService;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
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
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final AssessmentService assessmentService;
    
    @PostConstruct
    public void init() {
    }    

    public PatientController(PatientService patientService, AssessmentService assessmentService) {
        this.patientService = patientService;
        this.assessmentService = assessmentService;
    }

    @GetMapping
    public CollectionModel<PatientModel> getAllPatients() {
        List<PatientModel> patientModels = patientService.findAll().stream()
                .map(patient -> new PatientModel(patient)
                        .add(linkTo(methodOn(PatientController.class).getPatientById(patient.getId())).withSelfRel())
                        .add(linkTo(methodOn(PatientController.class).getPatientAssessments(patient.getId())).withRel("assessments")))
                .collect(Collectors.toList());

        return CollectionModel.of(patientModels)
                .add(linkTo(methodOn(PatientController.class).getAllPatients()).withSelfRel())
                .add(linkTo(methodOn(PatientController.class).createPatient(null)).withRel("create"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientModel> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        PatientModel patientModel = new PatientModel(patient)
                .add(linkTo(methodOn(PatientController.class).getPatientById(id)).withSelfRel())
                .add(linkTo(methodOn(PatientController.class).getAllPatients()).withRel("patients"))
                .add(linkTo(methodOn(PatientController.class).getPatientAssessments(id)).withRel("assessments"))
                .add(linkTo(methodOn(PatientController.class).updatePatient(id, null)).withRel("update"));
        return ResponseEntity.ok(patientModel);
    }

    @PostMapping
    public ResponseEntity<PatientModel> createPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.save(patient);
        PatientModel patientModel = new PatientModel(createdPatient)
                .add(linkTo(methodOn(PatientController.class).getPatientById(createdPatient.getId())).withSelfRel())
                .add(linkTo(methodOn(PatientController.class).getAllPatients()).withRel("patients"))
                .add(linkTo(methodOn(PatientController.class).getPatientAssessments(createdPatient.getId())).withRel("assessments"));
        return ResponseEntity.status(HttpStatus.CREATED).body(patientModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientModel> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        Patient patient = patientService.update(id, updatedPatient);
        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        PatientModel patientModel = new PatientModel(patient)
                .add(linkTo(methodOn(PatientController.class).getPatientById(id)).withSelfRel())
                .add(linkTo(methodOn(PatientController.class).getAllPatients()).withRel("patients"))
                .add(linkTo(methodOn(PatientController.class).getPatientAssessments(id)).withRel("assessments"));
        return ResponseEntity.ok(patientModel);
    }

    @GetMapping("/{patientId}/assessments")
    public CollectionModel<AssessmentModel> getPatientAssessments(@PathVariable Long patientId) {
        Patient patient = patientService.findById(patientId);
        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        List<Assessment> assessments = patientService.findAssessmentsByPatientId(patientId, assessmentService);
        List<AssessmentModel> assessmentModels = assessments.stream()
            .map(assessment -> {
                AssessmentModel model = new AssessmentModel(assessment);
                model.add(linkTo(methodOn(AssessmentController.class).getAssessmentById(assessment.getId())).withSelfRel())
                    .add(linkTo(methodOn(PatientController.class).getPatientById(patientId)).withRel("patient"))
                    .add(linkTo(methodOn(AssessmentController.class).getAssessmentAnswers(assessment.getId())).withRel("answers"));
                return model;
            })
            .collect(Collectors.toList());        
        
        return CollectionModel.of(assessmentModels)
                .add(linkTo(methodOn(PatientController.class).getPatientAssessments(patientId)).withSelfRel())
                .add(linkTo(methodOn(PatientController.class).getPatientById(patientId)).withRel("patient"));
    }
}

