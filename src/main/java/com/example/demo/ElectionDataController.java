package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/electionData")
public class ElectionDataController {

    @Autowired
    private CentralSystemConsumer consumer;

    /**
     * Gibt die gesammelten Wahldaten als JSON zurück.
     */
    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ElectionData> getElectionDataJson() {
        return consumer.getCollectedData();
    }

    /**
     * Gibt die gesammelten Wahldaten als XML zurück.
     */
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<ElectionData> getElectionDataXml() {
        return consumer.getCollectedData();
    }

    /**
     * Gibt die aggregierten Stimmen als JSON zurück.
     */
    @GetMapping(value = "/aggregated/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> getAggregatedVotesJson() {
        return consumer.getAggregatedVotes();
    }

    /**
     * Gibt die aggregierten Stimmen als XML zurück.
     */
    @GetMapping(value = "/aggregated/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Map<String, Integer> getAggregatedVotesXml() {
        return consumer.getAggregatedVotes();
    }

    /**
     * Löscht alle gesammelten Wahldaten.
     */
    @DeleteMapping
    public void clearData() {
        consumer.clearData();
    }
}
