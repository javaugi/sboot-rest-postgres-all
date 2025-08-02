/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.resource;

import com.spring5.entity.Appointment;
import com.spring5.entity.Nurse;
import com.spring5.entity.Physician;
import java.util.ArrayList;
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
public class PhysicianResource extends RepresentationModel {
    private Long physicianId;
    private String name;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String specialization;
    private List<Nurse> supervisedNurses = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();
    
    // Constructor, getters, and setters
    public PhysicianResource(Physician physician) {
        this.physicianId = physician.getId();
        this.name = physician.getName();
        this.firstName = physician.getFirstName();
        this.lastName = physician.getLastName();
        this.licenseNumber = physician.getLicenseNumber();
        this.specialization = physician.getSpecialization();
        this.supervisedNurses = physician.getSupervisedNurses();
        this.appointments = physician.getAppointments();
    }    
}
