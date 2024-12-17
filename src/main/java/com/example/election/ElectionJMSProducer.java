package com.example.election;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ElectionJMSProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String queueName, String message) {
        kafkaTemplate.send(queueName, message);
        System.out.println("Message sent to queue: " + message);
    }
}