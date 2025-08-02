/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import com.spring5.entity.Patient;
import com.spring5.repository.PatientRepository;
import com.spring5.resource.PatientResource;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javaugi
 */
@RestController
@RequestMapping("/api/patients")
public class PatientApiController {

    //*
    @Autowired
    private PatientRepository patientRepository;

    //@Autowired
    //private EntityLinks entityLinks;
    
    private final EntityLinks entityLinks;
    @Autowired
    public PatientApiController(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }    
    

    @GetMapping
    public ResponseEntity<Collection<PatientResource>> getAllPatients() {
        List<PatientResource> patientResources = patientRepository.findAll().stream()
                .map(PatientResource::new)
                .collect(Collectors.toList());

        EntityModel<Collection<PatientResource>> resources = EntityModel.of(patientResources);
        resources.add(entityLinks.linkToCollectionResource(Patient.class).withRel("patients"));

        return ResponseEntity.ok(resources.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResource> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(patient -> {
                    PatientResource resource = new PatientResource(patient);
                    resource.add(entityLinks.linkToItemResource(Patient.class, id).withSelfRel());
                    resource.add(entityLinks.linkToCollectionResource(Patient.class).withRel("all-patients"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PatientResource> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        PatientResource resource = new PatientResource(savedPatient);
        resource.add(entityLinks.linkToItemResource(Patient.class, savedPatient.getId()).withSelfRel());
        resource.add(entityLinks.linkToCollectionResource(Patient.class).withRel("all-patients"));
        
        /*
        EntityModel<PatientResource> model = EntityModel.of(resource);
        model.add(entityLinks.linkToItemResource(Patient.class, savedPatient.getId()).withSelfRel());
        model.add(entityLinks.linkToCollectionResource(Patient.class).withRel("all-patients"));
        // */
        // Get the self link and convert it to a URI
        Link selfLink = resource.getRequiredLink(LinkRelation.of("all-patients"));
        URI locationUri = URI.create(selfLink.getHref());
        return ResponseEntity.created(locationUri).body(resource);
    }
    // */

}
