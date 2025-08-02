/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mongodb;

import org.apache.spark.sql.*;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordCountService {

    //@Autowired
    private SparkSession spark;

    public Map<String, Long> countWords(String filePath) {
        // Code to load the file, split into words, and count occurrences
        //TODO return wordCounts(spark.read().textFile(filePath)); // Using spark session
        return null;
    }    
}
