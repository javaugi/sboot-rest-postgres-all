/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

/**
 * Best Practices for JSON Validation
 * 
 * Use Bean Validation for Simple Rules: For basic field-level validation (required fields, length, patterns)
 * Implement Custom Validators for Complex Logic: When validation requires access to multiple fields or external services
 * Consider JSON Schema for Complex Structures: When you need to validate nested objects or complex relationships
 * Validate Early: Validate input as soon as it enters your system (in controllers)
 * Provide Clear Error Messages: Help API consumers understand what went wrong
 * Consistent Error Responses: Use a standardized format for validation errors
 * Combine Approaches: Use both annotation-based validation and programmatic validation when needed
 * This comprehensive approach ensures your Spring Boot CRUD operations are protected with robust validation at multiple levels.
 * 
 * @author javaugi
 */
public class JsonValidationJWTApacheAvro {
    // 1. JSON validation
    // 2. JWT usage
    //3. Apache Avro vs JWT
    
}
/*
Avro in Apache Avro is not an acronym. It's a name, possibly inspired by the British aircraft manufacturer Avro (A.V. Roe and Company). 
The name was chosen for the open-source data serialization system developed by the Apache Software Foundation. Some speculate that 
the "A" in AVRO might refer to Apache, while the "O" might stand for Object, according to a Stack Overflow discussion. Additionally, 
the "V" could potentially stand for version, notes the same discussion
*/
/*
Validating JSON File Content in Spring Boot CRUD Operations
    Validating JSON input is crucial for maintaining data integrity in your Spring Boot CRUD application. Here's a comprehensive guide to implementing JSON validation:

1. Add Validation Dependencies: First, ensure you have the necessary dependencies in your pom.xml:
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
</dependency>

2. Basic Bean Validation Annotate Your Entity Class
@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
}

3. Enable Validation in Controller - PatientValidatorController
4. Custom Validators Create a Custom Validation Annotation
    Create a Custom Validation Annotation
        public @interface AllowedValues {
            String message() default "Value not allowed";
            Class<?>[] groups() default {};
            Class<? extends Payload>[] payload() default {};
            String[] value();
        }
    Implement the Validator
        public class AllowedValuesValidator implements ConstraintValidator<AllowedValues, String> {

            private String[] allowedValues;

            @Override
            public void initialize(AllowedValues constraintAnnotation) {
                this.allowedValues = constraintAnnotation.value();
            }

            @Override
            public boolean isValid(String value, ConstraintValidatorContext context) {
                if (value == null) {
                    return true; // Let @NotNull handle null checks
                }
                return Arrays.asList(allowedValues).contains(value);
            }
        }
    Use the Custom Validator
        public class Appointment implements java.io.Serializable {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            Long id;

            private LocalDateTime appointmentDateTime;
            private String reason;

            @AllowedValues(value = {"SCHEDULED", "COMPLETED", "CANCELLED"}, 
                          message = "Status must be SCHEDULED, COMPLETED, or CANCELLED")
            private String status; // Scheduled, Completed, Cancelled
        }
5. Cross Field Validation 
    Class-Level Validation Annotation : ValidAppointment 
    Class-Level Validator Implementation: ValidAppointmentValidator
    Apply to Entity
        @Entity
        @ValidAppointment
        public class Appointment {
            // ...
        }

6. JSON Schema Validation: For complex validation, consider using JSON Schema:
    Add Dependency
        <dependency>
            <groupId>com.github.everit-org.json-schema</groupId>
            <artifactId>org.everit.json.schema</artifactId>
            <version>1.14.0</version>
        </dependency>
    Create JSON Schema
        src/main/resources/schemas/patient-schema.json:    
            {
              "$schema": "http://json-schema.org/draft-07/schema#",
              "type": "object",
              "required": ["firstName", "lastName", "dateOfBirth"],
              "properties": {
                "firstName": {
                  "type": "string",
                  "minLength": 2,
                  "maxLength": 50
                },
                "lastName": {
                  "type": "string",
                  "minLength": 2,
                  "maxLength": 50
                },
                "dateOfBirth": {
                  "type": "string",
                  "format": "date"
                },
                "phoneNumber": {
                  "type": "string",
                  "pattern": "^\\+?[0-9]{10,15}$"
                }
              }
            }
    Implement Schema Validatio: JsonSchemaValidator 
    Use in Controller
        public class PatientJsonApiController {

            @Autowired
            private JsonSchemaValidator jsonValidator;

            @PostMapping("/validate")
            public ResponseEntity<?> validatePatientJson(@RequestBody String patientJson) {
                try {
                    jsonValidator.validatePatientJson(patientJson);
                    return ResponseEntity.ok().body("JSON is valid");
                } catch (ValidationException e) {
                    return ResponseEntity.badRequest().body(e.getLocalizedMessage());
                }
            }  
        }
7. Global Exception Handling
8. Testing Validation Unit Test Example
*/

/*
A JWT, or JSON Web Token, is a open standard used to securely transmit information, including user authentication data, between two parties. It's like a digital identity card that allows a client (like a web app) to prove their identity to a server. 
Here's a more detailed explanation:
1. What it is: 
JWTs are strings of text encoded in JSON format.
They contain information, or "claims," about the user or entity they represent.
These claims can include things like user ID, roles, permissions, and other relevant data.
2. How it works: 
When a user logs in, a server issues a JWT and sends it to the client.
The client stores this JWT and includes it in subsequent requests to the server.
The server can then verify the JWT's authenticity and extract the user's claims.
3. Why it's used:
Stateless authentication:
JWTs allow the server to maintain a stateless authentication system, meaning it doesn't need to store session information on its end. 
Secure communication:
JWTs can be signed to ensure their integrity and authenticity, and can be encrypted to prevent unauthorized access to the data they contain. 
Cross-domain authentication:
JWTs can be used across different domains, making them ideal for microservice architectures. 
4. Structure: 
A JWT is typically composed of three parts:
Header: Contains information about the token type (JWT) and the signature algorithm used.
Payload: Contains the claims (information about the user or entity).
Signature: A digital signature that verifies the integrity and authenticity of the token.
5. Benefits:
Improved security:
JWTs can be signed and encrypted to prevent tampering and unauthorized access. 
Statelessness:
The server doesn't need to maintain session state, making it easier to scale and deploy applications. 
Scalability:
JWTs are compact and easy to transport, making them well-suited for microservice architectures. 
In essence, JWTs provide a secure and efficient way for clients to authenticate with servers and securely share information. 
*/

/*
Apache Avro and JWT (JSON Web Token) serve different purposes. Avro is a data serialization framework used for efficient data exchange and storage, particularly in big data environments. JWT is a standard for securely transmitting information between two parties, often used for authentication and authorization. While both use JSON, Avro focuses on data representation and serialization, while JWT focuses on security and information exchange between applications. 
Apache Avro:
Purpose: Data serialization and exchange, particularly for big data applications. 
Functionality: Avro provides a schema-based, binary format for encoding and decoding data. It's designed to be compact, fast, and efficient for large datasets. 
Use Cases: Big data processing (Hadoop, Spark), data streaming, and data exchange. 
Key Features: Schema evolution, type safety, language neutrality, and efficient storage and transmission. 
JWT (JSON Web Token):
Purpose:
Securely transmitting information, often for authentication and authorization. 
Functionality:
JWTs are compact, self-contained JSON objects that are digitally signed. They contain claims, which are data about the subject (user, service). 
Use Cases:
User authentication, authorization, and secure communication between applications. 
Key Features:
Compactness, URL-safe, and secure communication with digital signatures. 
Key Differences:
Feature
Apache Avro
JWT
Purpose
Data serialization and exchange
Secure information transmission (authentication, auth)
Data Format
Schema-based, binary
JSON
Security
Schema evolution, type safety
Digital signatures, secure claims
Use Cases
Big data, data streaming, data storage
User authentication, authorization, secure comms
In essence, Avro is a tool for handling large datasets efficiently, while JWT is a tool for secure and reliable communication between applications. They address different needs and are often used in different contexts. 
*/
