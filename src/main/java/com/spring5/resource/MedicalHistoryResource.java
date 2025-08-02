/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.resource;

import com.spring5.entity.MedicalHistory;
import com.spring5.entity.Patient;
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
public class MedicalHistoryResource extends RepresentationModel {
    private Long medicalHistoryId;
    private Patient patient;    
    private String history;
    
    // Constructor, getters, and setters
    public MedicalHistoryResource(MedicalHistory medHistory) {
        this.medicalHistoryId = medHistory.getId();
        this.patient = medHistory.getPatient();
        this.history = medHistory.getHistory();
    }    
}
