/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author javaugi
 */
@Configuration
@ConditionalOnProperty(name = "app.mongodb.enabled", havingValue = "true")
public class MongoConfig {
    //spring.data.mongodb.uri=disabled causes running validation error
}
/*
The error you're seeing (MongoSocketOpenException: Connection refused) indicates that your application is trying to connect to a MongoDB instance, but it can't reach it—likely because MongoDB isn't running, is misconfigured, or the hostname/port is incorrect.

If you're in a situation where MongoDB isn't needed (like in dev/testing), or you want to disable MongoDB startup checks to prevent these errors, here are a few ways to handle that:

✅ Option 1: Lazy Initialization / Conditional MongoDB Connection
Make the MongoDB client connection lazy or conditional based on configuration/environment.

Example (Java with Spring Boot):
In application.properties or application.yml:

properties
Copy
Edit
spring.data.mongodb.uri=disabled
Or have a custom property:

properties
Copy
Edit
app.mongodb.enabled=false
And conditionally load the MongoDB config:

java
Copy
Edit
@Configuration
@ConditionalOnProperty(name = "app.mongodb.enabled", havingValue = "true")
public class MongoConfig {
    // Mongo configuration
}
✅ Option 2: Avoid Connecting If MongoDB Not Needed
If you're using MongoDB via a manual MongoClient, wrap it in a try-catch or environment condition.

java
Copy
Edit
if (isMongoEnabled()) {
    try {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        // use mongoClient
    } catch (MongoSocketOpenException e) {
        log.warn("MongoDB connection failed, skipping...");
    }
}
✅ Option 3: Use Embedded Mongo (For Testing Only)
If the issue is only for local testing/dev, consider using embedded MongoDB:

xml
Copy
Edit
<dependency>
    <groupId>de.flapdoodle.embed</groupId>
    <artifactId>de.flapdoodle.embed.mongo</artifactId>
    <version>4.6.1</version>
    <scope>test</scope>
</dependency>
This spins up an in-memory MongoDB instance during tests so you don't need the real thing running.

✅ Option 4: Ensure MongoDB is Running or Mock It
Make sure MongoDB is actually running (mongod) on the correct port.

Or mock MongoDB repositories/services in test configurations using tools like @MockBean or Mockito
*/