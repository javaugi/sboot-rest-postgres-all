/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.resource;

import com.spring5.entity.Appointment;
import com.spring5.entity.Patient;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

/**
 *
 * @author javaugi
 */
/*
Representation models
The ResourceSupport/Resource/Resources/PagedResources group of classes never really felt appropriately named. After all,
    these types do not actually manifest resources but rather representation models that can be enriched with hypermedia
    information and affordances. Here’s how new names map to the old ones:

    ResourceSupport is now RepresentationModel
    Resource is now EntityModel
    Resources is now CollectionModel
    PagedResources is now PagedModel
*/
@Getter
@Setter
@ToString
public class PatientResource extends RepresentationModel {
    private Long patientId;
    private String name;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private List<Appointment> appointments;  
    
    // Constructor, getters, and setters
    public PatientResource(Patient patient) {
        this.patientId = patient.getId();
        this.name = patient.getName();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.dateOfBirth = patient.getDateOfBirth();
        this.gender = patient.getGender();
        this.address = patient.getAddress();
        this.phoneNumber = patient.getPhoneNumber();
        this.appointments = patient.getAppointments();
    }    
}
