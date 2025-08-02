/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.resource;

import com.spring5.entity.Appointment;
import com.spring5.entity.Nurse;
import com.spring5.entity.Physician;
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
public class NurseResource extends RepresentationModel {
    private Long nurseId;
    private String name;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String specialization;
    private Physician supervisingPhysician;
    private List<Appointment> appointments;
    
    // Constructor, getters, and setters
    public NurseResource(Nurse nurse) {
        this.nurseId = nurse.getId();
        this.name = nurse.getName();
        this.firstName = nurse.getFirstName();
        this.lastName = nurse.getLastName();
        this.licenseNumber = nurse.getLicenseNumber();
        this.specialization = nurse.getSpecialization();
        this.supervisingPhysician = nurse.getSupervisingPhysician();
        this.appointments = nurse.getAppointments();
    }    
}
