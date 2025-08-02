/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import com.spring5.entity.Patient;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PatientValidationTest {
    
    //@Autowired
    //private MockMvc mockMvc;
    
    //@Autowired
    //private ValidPatientValidator validator;
    @Mock
    private Validator validator;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
        
    //@Test
    public void whenFirstNameIsBlank_thenValidationFails() {
        Patient patient = new Patient();
        patient.setUserEmail("");
        patient.setPhoneNumber("");
        
        Errors errors = this.validator.validateObject(patient);
        assertFalse(errors.hasErrors());
        assertTrue(errors != null && errors.getFieldError() != null);
        assertEquals("Must provide either email or phone number or both", errors.getFieldError().getDefaultMessage());
        
        /* original code
        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);
        assertFalse(violations.isEmpty());
        assertEquals("First name is required", violations.iterator().next().getMessage());    
        // */
    }
    
}
