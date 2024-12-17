package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Wahllokal3 {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ElectionDataGenerator dataGenerator = new ElectionDataGenerator();

    private final String regionID = "station-003"; // ID für die Message Queue

    @Scheduled(fixedRate = 10000) // Alle 10 Sekunden
    public void sendElectionData() {
        try {
            // Erstelle ElectionData mithilfe des Generators
            ElectionData electionData = dataGenerator.generateData(regionID);


            String message = objectMapper.writeValueAsString(electionData);

            // Sende die Nachricht an die Kafka-Topic
            kafkaTemplate.send(regionID, message);
            System.out.println("3 -> Data sent to queue: " + message);
            Log logger = new Log("Lokal-003.txt");
            logger.appendString("3 -> Data sent to queue: " + message);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
