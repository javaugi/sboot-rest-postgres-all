/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.validator;

import com.spring5.entity.Patient;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author javaugi
 */
public class ValidPatientValidator implements ConstraintValidator<ValidAppointment, Patient> {
    
    @Override
    public boolean isValid(Patient patient, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(patient.getUserEmail()) && StringUtils.isBlank(patient.getPhoneNumber())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Must provide either email or phone number or both")
                   .addPropertyNode("userEmail")
                   .addPropertyNode("phoneNumber")
                   .addConstraintViolation();
            return false;
        }
        
        return true;
    }
}
