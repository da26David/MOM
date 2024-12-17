package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.ArrayList;

public class ElectionData {

    private String regionID;
    private String regionName;
    private String regionAddress;
    private String regionPostalCode;
    private String federalState;
    private String timestamp;
    private List<PartyVotes> countingData;

    // Standard-Konstruktor (wichtig für Deserialisierung)
    public ElectionData() {
    }

    // Getter und Setter
    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionAddress() {
        return regionAddress;
    }

    public void setRegionAddress(String regionAddress) {
        this.regionAddress = regionAddress;
    }

    public String getRegionPostalCode() {
        return regionPostalCode;
    }

    public void setRegionPostalCode(String regionPostalCode) {
        this.regionPostalCode = regionPostalCode;
    }

    public String getFederalState() {
        return federalState;
    }

    public void setFederalState(String federalState) {
        this.federalState = federalState;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<PartyVotes> getCountingData() {
        return countingData;
    }

    public void setCountingData(List<PartyVotes> countingData) {
        this.countingData = countingData;
    }

    // Inner Class für PartyVotes
    public static class PartyVotes {

        private String partyID;
        private int amountVotes;
        private List<Vorzugstimme> vorzugsstimmen;

        // Standard-Konstruktor
        public PartyVotes() {
        }

        // Getter und Setter
        public String getPartyID() {
            return partyID;
        }

        public void setPartyID(String partyID) {
            this.partyID = partyID;
        }

        public int getAmountVotes() {
            return amountVotes;
        }

        public void setAmountVotes(int amountVotes) {
            this.amountVotes = amountVotes;
        }

        public List<Vorzugstimme> getVorzugsstimmen() {
            return vorzugsstimmen;
        }

        public void setVorzugsstimmen(List<Vorzugstimme> vorzugsstimmen) {
            this.vorzugsstimmen = vorzugsstimmen;
        }

        // Methode zum Hinzufügen von Vorzugsstimmen
        public void addVorzugsStimme(int listenNummer, String name, int amountVotes) {
            if (this.vorzugsstimmen == null) {
                this.vorzugsstimmen = new ArrayList<>();
            }
            this.vorzugsstimmen.add(new Vorzugstimme(listenNummer, name, amountVotes));
        }
    }

    // Inner Class für Vorzugstimme
    public static class Vorzugstimme {

        private int listenNummer;
        private String name;
        private int amountVotes;

        // Standard-Konstruktor für Jackson
        public Vorzugstimme() {
        }

        // Konstruktor mit Parametern
        @JsonCreator
        public Vorzugstimme(@JsonProperty("listenNummer") int listenNummer,
                            @JsonProperty("name") String name,
                            @JsonProperty("amountVotes") int amountVotes) {
            this.listenNummer = listenNummer;
            this.name = name;
            this.amountVotes = amountVotes;
        }

        // Getter und Setter
        public int getListenNummer() {
            return listenNummer;
        }

        public void setListenNummer(int listenNummer) {
            this.listenNummer = listenNummer;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAmountVotes() {
            return amountVotes;
        }

        public void setAmountVotes(int amountVotes) {
            this.amountVotes = amountVotes;
        }
    }
}