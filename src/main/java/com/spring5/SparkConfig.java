/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import java.util.Arrays;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
//TODO
//import com.mongodb.spark.MongoSpark;
//import com.mongodb.spark.rdd.api.java.JavaMongoRDD;

@Configuration
@PropertySource("classpath:application.properties")
//https://github.com/javaugi/spring-spark-example
public class SparkConfig implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(SparkConfig.class);

    @Value("${app.name}")
    protected String appName;
    @Value("${spark.home}")
    protected String sparkHome;
    @Value("${spark.master.uri}")
    protected String sparkMasterUri;
    
    //@Bean
    public SparkSession sparkSession() {
        return new SparkSession.Builder()
                .appName(appName)
                .master(sparkMasterUri) // or "spark://<your_cluster_url>"
                .getOrCreate();
    }

    //@Bean
    public SparkConf sparkConf() {
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(sparkMasterUri);
        return conf;
    }
    
    @Override
    public void run(String... args) throws Exception {
        log.info("SparkConfig with args {}", Arrays.toString(args)); 
        /*
        SparkSession spark = SparkSession.builder()
            .master(sparkMasterUri)
            .appName(appName)
            .config("spark.mongodb.input.uri", "mongodb://192.168.1.2/local.Test")
            .config("spark.mongodb.output.uri", "mongodb://192.168.1.2/local.Test")
            .getOrCreate();

        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
        // */
        
        /* TODO
        JavaMongoRDD<Document> rdd = MongoSpark.load(jsc);

        System.out.println("******************************************");
        System.out.println("The count is : ");
        System.out.println(rdd.count());
        System.out.println(rdd.first().toJson());
        System.out.println("******************************************");

        jsc.close();
        // */
  }
}

/*
To create a Spring Boot application that leverages Apache Spark, you'll need to set up a Spring Boot project, add the necessary dependencies, and then write code to interact with Spark. The application can be configured to use Spark locally or remotely, such as on a Databricks cluster. 
Here's a more detailed breakdown:
1. Setting up the Spring Boot Project:
Project Creation:
Use Spring Initializr to create a new Spring Boot project.
Dependencies:
Add the following dependencies to your pom.xml file:
spring-boot-starter-web (for web application)
spring-boot-starter-data-jpa (if using a database)
spark-core_2.12 (or the appropriate version for your Spark setup).
Consider adding spring-boot-devtools for faster development.
You might also need other dependencies based on your specific needs, like for data access or processing.
Spark Configuration:
Create a class (e.g., SparkConfig) to set up Spark within your Spring Boot application.
Dependency Injection:
Use @Autowired or similar annotations to inject the SparkSession into your Spring Boot components. 
2. Integrating Spark with Your Application:
SparkSession: Obtain a SparkSession instance, which is your entry point for interacting with Spark.
Data Loading: Load data into RDDs or DataFrames using the Spark APIs.
Data Processing: Perform Spark operations to transform or analyze your data.
Data Writing: Write the processed data to a file system, database, or other output source. 
3. Example: Word Count:
A classic example is the Spark Word Count application. You can use the Spring Boot application to: Load text data into an RDD, Split the text into words, Count the occurrences of each word, Organize the results, and Present the word counts in a REST endpoint. 
4. Running the Application:
Local Execution: Ensure Spark is configured locally and run your Spring Boot application. 
Remote Execution (Databricks):
Configure your Spring Boot application to connect to the Databricks cluster. 
Submit the Spark application to the cluster. 
Example Code Snippets:
Java

// SparkConfig.java
@Configuration
public class SparkConfig {

    @Bean
    public SparkSession sparkSession() {
        return new SparkSession.Builder()
                .appName("MySparkApp")
                .master("local[*]") // or "spark://<your_cluster_url>"
                .getOrCreate();
    }
}
Java

// WordCountService.java
@Service
public class WordCountService {

    @Autowired
    private SparkSession spark;

    public Map<String, Long> countWords(String filePath) {
        // Code to load the file, split into words, and count occurrences
        return wordCounts(spark.read().textFile(filePath)); // Using spark session
    }
}
Java

// WordCountController.java
@RestController
public class WordCountController {

    @Autowired
    private WordCountService wordCountService;

    @GetMapping("/wordcount/{filePath}")
    public Map<String, Long> getWordCount(@PathVariable String filePath) {
        return wordCountService.countWords(filePath);
    }
}
Key Considerations:
Spark Version: Choose a compatible Spark version with your Java/Scala/Python environment. 
Dependency Conflicts: Be mindful of potential dependency conflicts between Spring Boot and Spark. 
Remote Execution: If using a remote cluster, configure the SparkSession appropriately. 
Resource Management: Consider the memory and resource requirements of your Spark jobs. 
*/
