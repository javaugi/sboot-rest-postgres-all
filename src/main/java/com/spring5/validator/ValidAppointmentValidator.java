/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.validator;

import com.spring5.entity.Appointment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * @author javaugi
 */
public class ValidAppointmentValidator implements ConstraintValidator<ValidAppointment, Appointment> {

    @Override
    public boolean isValid(Appointment appointment, ConstraintValidatorContext context) {
        if (appointment.getStartTime() == null || appointment.getEndTime() == null) {
            return true; // Let @NotNull handle these
        }

        if (appointment.getEndTime().isBefore(appointment.getStartTime())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End time must be after start time")
                    .addPropertyNode("endTime")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
