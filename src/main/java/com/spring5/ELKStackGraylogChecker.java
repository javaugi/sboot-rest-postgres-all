/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javaugi
 */
@RestController
public class ELKStackGraylogChecker {

    private static final Logger logger = LoggerFactory.getLogger(ELKStackGraylogChecker.class);

    @GetMapping("/elkgraylog")
    public String elkGraylog() {
        logger.info("Hello endpoint was accessed");
        return "Hello, World!";
    }
}

/*
The ELK stack (Elasticsearch - is a search and analytics engine that is used to store and index log data., 
    Logstash - is a data processing pipeline that is used to collect, parse, and transform log data before sending it to Elasticsearch., 
    and Kibana - is a visualization tool (dashboard) that is used to explore and analyze log data stored in Elasticsearch  ) 
and Graylog are popular open-source solutions for centralized logging. They allow you to collect, store, search, and analyze logs
    from multiple sources in a single place. This can be very useful for troubleshooting
    
This configuration sends logs to a Graylog instance running at graylog-server on port 12201. 
To use this code, you will need to have a Graylog server running. You can download Graylog from the Graylog website. Once you have Graylog running,
    you can start the Spring Boot application. The application will then send its logs to Graylog.
*/

/*
<dependencies>
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>     
     </dependency>
     <dependency>
        <groupId>net.logstash.logback</groupId>
        <artifactId>logstash-logback-encoder</artifactId>
        <version>7.4</version>
    </dependency>
    <dependency>
        <groupId>org.graylog2</groupId>
        <artifactId>gelfj</artifactId>
        <version>1.1.16</version>
    </dependency>
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-test</artifactId>
         <scope>test</scope>
     </dependency>
 </dependencies>


 // application.properties
 logging.config=classpath:logback-spring.xml

<!-- logback-spring.xml -->
 <?xml version="1.0" encoding="UTF-8"?>
 <configuration>
     <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
         <encoder>
             <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
         </encoder>
     </appender>
 
     <appender name="GELF" class="biz.paluch.logging.gelf.logback.GelfUDPAppender">
        <host>graylog-server</host>
        <port>12201</port>
        <encoder class="biz.paluch.logging.gelf.logback.GelfLogbackEncoder">
            <includeFullMdc>true</includeFullMdc>
            <includeStackTrace>true</includeStackTrace>
            <patternLayout>
                <pattern>%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%t] %c{1}:%L - %m%n</pattern>
            </patternLayout>
        </encoder>
    </appender>
 
     <root level="INFO">
         <appender-ref ref="STDOUT" />
         <appender-ref ref = "GELF"/>
     </root>
 </configuration>
*/