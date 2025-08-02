/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.validator;

import jakarta.validation.ValidationException;
import java.io.IOException;
import java.io.InputStream;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

/*
Best Practices for JSON Validation
    Use Bean Validation for Simple Rules: For basic field-level validation (required fields, length, patterns)
    Implement Custom Validators for Complex Logic: When validation requires access to multiple fields or external services
    Consider JSON Schema for Complex Structures: When you need to validate nested objects or complex relationships
    Validate Early: Validate input as soon as it enters your system (in controllers)
    Provide Clear Error Messages: Help API consumers understand what went wrong
    Consistent Error Responses: Use a standardized format for validation errors
    Combine Approaches: Use both annotation-based validation and programmatic validation when needed

This comprehensive approach ensures your Spring Boot CRUD operations are protected with robust validation at multiple levels.
*/

@Service
public class JsonSchemaValidator {
    
    private final Schema patientSchema;
    
    public JsonSchemaValidator() throws IOException {
        InputStream schemaStream = getClass().getResourceAsStream("/schemas/patient-schema.json");
        JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
        this.patientSchema = SchemaLoader.load(rawSchema);
    }
    
    public void validatePatientJson(String json) throws ValidationException {
        JSONObject patientJson = new JSONObject(json);
        patientSchema.validate(patientJson);
    }
}
