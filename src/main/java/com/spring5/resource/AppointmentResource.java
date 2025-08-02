/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.resource;

import com.spring5.entity.Appointment;
import com.spring5.entity.Nurse;
import com.spring5.entity.Patient;
import com.spring5.entity.Physician;
import java.time.LocalDateTime;
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
public class AppointmentResource extends RepresentationModel {
    private Long appointmentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String reason;
    private String status; 
    private Patient patient;
    private Nurse nurse;
    private Physician physician;
    
    // Constructor, getters, and setters
    public AppointmentResource(Appointment appointment) {
        this.appointmentId = appointment.getId();
        this.startTime = appointment.getStartTime();
        this.endTime = appointment.getEndTime();
        this.reason = appointment.getReason();
        this.status = appointment.getStatus();
        this.patient = appointment.getPatient();
        this.nurse = appointment.getNurse();
        this.physician = appointment.getPhysician();
    }    
}
