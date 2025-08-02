/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import com.spring5.entity.Appointment;
import com.spring5.repository.AppointmentRepository;
import com.spring5.repository.NurseRepository;
import com.spring5.repository.PatientRepository;
import com.spring5.repository.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author javaugi
 */
@Controller
@RequestMapping("/web")
public class WebMvcController {
@Autowired
    private PatientRepository patientRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private PhysicianRepository physicianRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @GetMapping("/patients")
    public String showPatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patients";
    }
    
    @GetMapping("/nurses")
    public String showNurses(Model model) {
        model.addAttribute("nurses", nurseRepository.findAll());
        return "nurses";
    }
    
    @GetMapping("/physicians")
    public String showPhysicians(Model model) {
        model.addAttribute("physicians", physicianRepository.findAll());
        return "physicians";
    }
    
    @GetMapping("/appointments")
    public String showAppointments(Model model) {
        model.addAttribute("appointments", appointmentRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("nurses", nurseRepository.findAll());
        model.addAttribute("physicians", physicianRepository.findAll());
        return "appointments";
    }
    
    @PostMapping("/appointments")
    public String createAppointment(@ModelAttribute Appointment appointment) {
        appointmentRepository.save(appointment);
        return "redirect:/web/appointments";
    }    
}


/*
/*
Key Features

RESTful API with HATEOAS:
    Resources include links to related resources
    Follows REST principles
    Self-descriptive messages

Web Interface with Spring MVC:
    Traditional server-side rendering
    Thymeleaf templates for HTML generation
    Simple CRUD operations through web forms

Data Model:
    JPA entities with proper relationships
    Repository pattern for data access

Separation of Concerns:
    API endpoints separate from web interface
    Clear distinction between data model and resource representation

This implementation provides a solid foundation that can be extended with additional features like:

    Authentication and authorization
    Validation
    Advanced search capabilities
    Pagination
    Caching
    API documentation with Swagger
*/
