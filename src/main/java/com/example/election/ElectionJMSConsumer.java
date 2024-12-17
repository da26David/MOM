package com.example.election;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ElectionJMSConsumer {

    @KafkaListener(topics = "election-queue", groupId = "election-group")
    public void consume(String message) {
        System.out.println("Received message from queue: " + message);
    }
}