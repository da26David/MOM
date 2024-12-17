package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CentralSystemConsumer {

    private final List<ElectionData> collectedData = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Listener für station-001
    @KafkaListener(topics = "station-001", groupId = "myGroup")
    public void processStation001(String message) {
        processPollingStationData(message, "station-001");
    }

    // Listener für station-002
    @KafkaListener(topics = "station-002", groupId = "myGroup")
    public void processStation002(String message) {
        processPollingStationData(message, "station-002");
    }

    // Listener für station-003
    @KafkaListener(topics = "station-003", groupId = "myGroup")
    public void processStation003(String message) {
        processPollingStationData(message, "station-003");
    }

    // Zentrale Methode zur Verarbeitung der Daten
    private void processPollingStationData(String message, String stationId) {
        try {
            // Nachricht in ElectionData umwandeln
            ElectionData data = objectMapper.readValue(message, ElectionData.class);
            collectedData.add(data);

            System.out.println("Data received from " + stationId + ": " + data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gibt alle gesammelten Wahldaten kombiniert zurück.
     */
    public List<ElectionData> getCollectedData() {
        return collectedData;
    }

    /**
     * Gibt die zusammengefassten Daten als Map mit aggregierten Stimmen zurück.
     */
    public Map<String, Integer> getAggregatedVotes() {
        Map<String, Integer> aggregatedVotes = new HashMap<>();

        for (ElectionData data : collectedData) {
            for (ElectionData.PartyVotes partyVotes : data.getCountingData()) {
                aggregatedVotes.merge(partyVotes.getPartyID(), partyVotes.getAmountVotes(), Integer::sum);
            }
        }

        return aggregatedVotes;
    }

    /**
     * Gibt die zusammengefassten Daten als String mit aggregierten Stimmen zurück.
     */
    public String getCombinedDataAsString() {
        Map<String, Integer> aggregatedVotes = getAggregatedVotes();

        // Aggregierte Ergebnisse in lesbares Format umwandeln
        StringBuilder result = new StringBuilder("Combined Election Data:\n");
        aggregatedVotes.forEach((party, votes) ->
                result.append("Party: ").append(party).append(", Votes: ").append(votes).append("\n")
        );

        return result.toString();
    }

    /**
     * Löscht alle gesammelten Wahldaten.
     */
    public void clearData() {
        collectedData.clear();
    }
}
