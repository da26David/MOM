package com.example.demo;

import com.example.demo.ElectionData;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Election Data Objekt erstellen und Daten generieren
 */
public class ElectionDataGenerator {
    /*

     */
    public ElectionData generateData(String regionID) {
        ElectionData data = new ElectionData();
        data.setRegionID(regionID);
        data.setRegionName("Linz Bahnhof");
        data.setRegionAddress("Bahnhofsstrasse 27/9");
        data.setRegionPostalCode("4020");
        data.setFederalState("Austria");
        data.setTimestamp(LocalDateTime.now().toString());

        ElectionData.PartyVotes p1 = new ElectionData.PartyVotes();
        p1.setPartyID("ÖVP");
        p1.setAmountVotes(generateRandomVotes(350, 500));
        p1.addVorzugsStimme(1,"Karl Nehammer",350);

        ElectionData.PartyVotes p2 = new ElectionData.PartyVotes();
        p2.setPartyID("FPÖ");
        p2.setAmountVotes(generateRandomVotes(200, 400));
        p2.addVorzugsStimme(1,"Herbert Kickl",101);
        p2.addVorzugsStimme(2, "Viktor Orban", 99);

        ElectionData.PartyVotes p3 = new ElectionData.PartyVotes();
        p3.setPartyID("SPÖ");
        p3.setAmountVotes(generateRandomVotes(150, 300));
        p3.addVorzugsStimme(1,"Andreas Babbler",100);

        ElectionData.PartyVotes p4 = new ElectionData.PartyVotes();
        p4.setPartyID("Grüne");
        p4.setAmountVotes(generateRandomVotes(100, 200));
        p4.addVorzugsStimme(1,"Werner Kogler",100);

        ElectionData.PartyVotes p5 = new ElectionData.PartyVotes();
        p5.setPartyID("Neos");
        p5.setAmountVotes(generateRandomVotes(50, 75));
        p5.addVorzugsStimme(1,"Beate Meinl-Reisinger",50);

        ElectionData.PartyVotes p6 = new ElectionData.PartyVotes();
        p6.setPartyID("Bier");
        p6.setAmountVotes(generateRandomVotes(25, 50));
        p6.addVorzugsStimme(1,"Marco Pogo",25);



        data.setCountingData(Arrays.asList(p1, p2, p3, p4, p5, p6));
        return data;
    }

    private int generateRandomVotes(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}

