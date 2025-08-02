/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

/**
 *
 * @author javaugi
 */
public class KafkaTopicPartition //implements TopicPartition
{
    @KafkaListener(id = "myConsumer", topicPartitions = 
        @TopicPartition(topic = "yourTopic", partitions = {"0", "1"})
    )
    public void listen(String message) {
        // Process the message
        System.out.println("Received message: " + message);
    }    
}
