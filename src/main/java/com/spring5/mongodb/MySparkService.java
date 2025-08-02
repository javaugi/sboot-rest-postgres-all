/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mongodb;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MySparkService {
    
    //@Autowired
    private SparkSession sparkSession;

    public void processData(String inputPath, String outputPath) {
        // Load data using SparkSession
        Dataset<Row> data = sparkSession.read().format("csv").load(inputPath);

        // Perform transformations (example)
        data.show();

        // Write results
        data.write().format("csv").save(outputPath);
    }
}

