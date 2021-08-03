package com.example.mareu.service;

import com.example.mareu.model.Meeting;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 12/07/2021
 */


public abstract class DummyMeetingGenerator {
    
    public static List<Meeting> dummyMeetings = Arrays.asList(
            new Meeting(1, "Réunion A - ", "14h00 - ", "Peach", "maxime@lamzone.com, alex@lamzone.com"),
            new Meeting(2, "Réunion B - ", "16h00 - ", "Mario", "paul@lamzone.com, viviane@lamzone.com"),
            new Meeting(3, "Réunion C - ", "19h00 - ", "Luigi", "amandine@lamzone.com, luc@lamzone.com")
    );
    
    static List<Meeting> generateMeetings() {
        return new ArrayList<>(dummyMeetings);
    }
}
