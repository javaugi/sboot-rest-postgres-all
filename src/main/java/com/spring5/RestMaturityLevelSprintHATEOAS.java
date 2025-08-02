/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

/**
 *
 * @author javaugi
 */
public class RestMaturityLevelSprintHATEOAS {
    
}
/*
Key Features

RESTful API with HATEOAS:
    Resources include links to related resources
    Follows REST principles
    Self-descriptive messages

Web Interface with Spring MVC:
    Traditional server-side rendering
    Thymeleaf templates for HTML generation
    Simple CRUD operations through web forms

Data Model:
    JPA entities with proper relationships
    Repository pattern for data access

Separation of Concerns:
    API endpoints separate from web interface
    Clear distinction between data model and resource representation

This implementation provides a solid foundation that can be extended with additional features like:

    Authentication and authorization
    Validation
    Advanced search capabilities
    Pagination
    Caching
    API documentation with Swagger
*/


/*
Example: REST Maturity Levels & API Design Critique
Let’s break down the Richardson Maturity Model (RMM) with a concrete example and then critique a real-world API design.

1. REST Maturity Levels Explained
Level 0: The Swamp of POX (Plain Old XML/HTTP)
Example: A SOAP-like API using HTTP as a tunnel.

http
Copy
POST /orderService HTTP/1.1
Body: <createOrder><productId>123</productId></createOrder>
Problems:

Single endpoint (/orderService).

No HTTP semantics (always POST).

Level 1: Resources
Example: Separate URIs for resources but still only POST.

http
Copy
POST /orders HTTP/1.1
Body: { "productId": 123 }
Improvement:

Resources identified (/orders).

Problems:

Still ignores HTTP methods (GET, PUT, DELETE).

Level 2: HTTP Verbs & Status Codes
Example: Proper use of HTTP methods and status codes.

http
Copy
POST /orders HTTP/1.1      → 201 Created + Location: /orders/100
GET /orders/100 HTTP/1.1   → 200 OK + Order details
DELETE /orders/100 HTTP/1.1 → 204 No Content
Improvements:

Uses GET, POST, DELETE.

Correct status codes (201, 404, 204).

Problems:

No hypermedia (clients hardcode URIs like /orders/100).

Level 3: Hypermedia Controls (HATEOAS)
Example: Responses include links for discoverability.

http
Copy
GET /orders/100 HTTP/1.1
Response:
{
  "id": 100,
  "status": "pending",
  "_links": {
    "self": { "href": "/orders/100" },
    "cancel": { "href": "/orders/100", "method": "DELETE" },
    "payment": { "href": "/payments?order=100" }
  }
}
Improvements:

Clients follow links instead of hardcoding paths.

API evolves without breaking clients (new links can be added).

2. Critiquing a Real-World API Design
Example API: A Payment Service
Design:

http
Copy
POST /api/v1/createPayment HTTP/1.1  
Body: { "orderId": 100, "amount": 50 }  
Response: { "success": true, "transactionId": "txn_123" }  
Critique Using REST Maturity Levels
Level 0-1 Issues:

Poor URI naming (/createPayment is RPC-style, not resource-oriented).

Misuse of HTTP (POST for idempotent payments—should be PUT).

Level 2 Issues:

No standard status codes (e.g., 200 OK for failures instead of 400 Bad Request).

No resource identifier (e.g., /payments/txn_123 for future lookups).

Level 3 Issues:

No hypermedia (e.g., no link to refund or check payment status).

Improved Design (Level 3 REST)
http
Copy
PUT /payments/order-100 HTTP/1.1  
Body: { "amount": 50, "currency": "USD" }  

Response (201 Created):  
{
  "id": "txn_123",
  "status": "completed",
  "_links": {
    "self": { "href": "/payments/txn_123" },
    "refund": { "href": "/payments/txn_123/refunds", "method": "POST" }
  }
}
Why It’s Better:

URI: Resource-oriented (/payments/{id}).

HTTP Method: PUT for idempotency (prevents duplicate payments).

Hypermedia: Clients discover refunds via links, no hardcoding.

3. Common API Design Pitfalls
Ignoring Idempotency:

Payments should use PUT (not POST) to avoid duplicates.

Overfetching/Underfetching:

Bad: GET /orders returns 100 fields when only 5 are needed.

Fix: Use GraphQL or ?fields=id,status.

Versioning Mistakes:

Bad: Breaking changes in /v1/orders.

Fix: Use hypermedia or Accept-Header versioning.

Poor Error Handling:

Bad: { "error": "Failed" } with 200 OK.

Fix: RFC 7807 (Problem Details for HTTP APIs):

json
Copy
{
  "type": "/errors/out-of-stock",
  "title": "Insufficient Inventory",
  "detail": "Product ID 123 is out of stock"
}
Key Takeaways for Interviews
Ask About Maturity:

"Did you design for Level 2 (HTTP verbs) or Level 3 (HATEOAS)?"

Critique Tradeoffs:

"Level 3 adds complexity—justified if clients need discoverability."

Discuss Real Examples:

"At [Company X], we upgraded from Level 1 to Level 2 by fixing GET /getOrders to GET /orders."

Example Interview Question:
"How would you design a refund endpoint for the Payment API?"
Strong Answer:
"I’d use POST /payments/{id}/refunds with HATEOAS links for discoverability. Idempotency would be ensured via a Idempotency-Key header."

Would you like a side-by-side comparison of good vs. bad API designs for another scenario (e.g., user management)?
*/

/*
Explanation of Level 3 REST API (HATEOAS) and Spring HATEOAS Usage:

This example demonstrates a Level 3 REST API because the responses include hypermedia links, allowing the client to 
discover and navigate the API without having hardcoded URLs. Spring HATEOAS simplifies the process of adding these links.

RepresentationModel and EntityModel:

PatientModel extends RepresentationModel. This is used for the root level representation of a patient, where we might 
want to include links to related resources (like assessments) directly within the patient object's representation.
AssessmentModel, QuestionModel, and AnswerModel extend EntityModel. This is a more specific type of RepresentationModel 
that wraps a single entity and allows adding links related to that specific entity.

WebMvcLinkBuilder:

linkTo(methodOn(ControllerClass.class).methodName(methodParameters)) is a core utility from Spring HATEOAS. It allows
you to create links to your Spring MVC controller methods in a type-safe way. You don't need to hardcode URLs as strings.
.withSelfRel(): Creates a link with the rel attribute set to self, pointing to the current resource.
.withRel("relationName"): Creates a link with a custom rel attribute, describing the relationship between the current 
resource and the linked resource (e.g., "assessments", "patient", "answers", "create", "update", "questions").

Controller Methods:

Each controller method returns a ResponseEntity containing either an EntityModel for a single resource or a CollectionModel for a collection of resources.
The map operations in the controller methods are where the HATEOAS links are added to the models.
Example API Interactions:

1. Get all patients:

HTTP

GET /patients
Response (Example):

JSON

{
  "_embedded": {
    "patientModelList": [
      {
        "id": 1,
        "name": "John Doe",
        "dob": "1980-05-15",
        "_links": {
          "self": {
            "href": "http://localhost:8080/patients/1"
          },
          "assessments": {
            "href": "http://localhost:8080/patients/1/assessments"
          }
        }
      },
      {
        "id": 2,
        "name": "Jane Smith",
        "dob": "1992-10-20",
        "_links": {
          "self": {
            "href": "http://localhost:8080/patients/2"
          },
          "assessments": {
            "href": "http://localhost:8080/patients/2/assessments"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/patients"
    },
    "create": {
      "href": "http://localhost:8080/patients"
    }
  }
}
The response for the collection of patients includes links to each individual patient (self) and a link to create a new patient (create).
Each individual patient also has links to its own details (self) and to retrieve their assessments (assessments).
2. Get a specific patient:

HTTP

GET /patients/1
Response (Example):

JSON

{
  "id": 1,
  "name": "John Doe",
  "dob": "1980-05-15",
  "_links": {
    "self": {
      "href": "http://localhost:8080/patients/1"
    },
    "patients": {
      "href": "http://localhost:8080/patients"
    },
    "assessments": {
      "href": "http://localhost:8080/patients/1/assessments"
    },
    "update": {
      "href": "http://localhost:8080/patients/1"
    }
  }
}
The response for a specific patient includes links to itself (self), the collection of all patients (patients), the patient's 
assessments (assessments), and an action to update the patient (update).

3. Get assessments for a specific patient:

HTTP

GET /patients/1/assessments
Response (Example):

JSON

{
  "_embedded": {
    "assessmentModelList": [
      {
        "content": {
          "id": 1,
          "patientId": 1,
          "date": "2025-04-08",
          "status": "Completed"
        },
        "_links": {
          "self": {
            "href": "http://localhost:8080/assessments/1"
          },
          "patient": {
            "href": "http://localhost:8080/patients/1"
          },
          "answers": {
            "href": "http://localhost:8080/assessments/1/answers"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/patients/1/assessments"
    },
    "patient": {
      "href": "http://localhost:8080/patients/1"
    }
  }
}
The response for a patient's assessments includes links to each individual assessment (self), the associated 
patient (patient), and the answers for that assessment (answers).

Key Principles of Level 3 REST (HATEOAS) Demonstrated:

Discoverability: Clients can discover available actions and related resources by following the links provided 
in the responses. They don't need prior knowledge of all the API endpoints.
Self-Descriptive Messages: Each response contains information about the resource itself and how to interact
with it further through the links.
Application State Transitions: The links guide the client through the possible state transitions of the application.
For example, after retrieving a patient, the client can see a link to update the patient.
This detailed example illustrates how to build a Level 3 REST API for a healthcare assessment system using 
Spring HATEOAS. By including hypermedia links, you create a more flexible, evolvable, and discoverable API. 
Remember to include the necessary Spring HATEOAS dependency in your pom.xml or build.gradle file:
*/