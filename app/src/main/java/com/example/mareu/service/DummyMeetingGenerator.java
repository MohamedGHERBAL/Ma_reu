package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static android.graphics.Color.rgb;

/**
 * Created by Mohamed GHERBAL (pour OC) on 12/07/2021
 */

public abstract class DummyMeetingGenerator {

    private static int meetingColor;
    
    public static List<Meeting> dummyMeetings = Arrays.asList(
            new Meeting(1,meetingColor(), "Réunion A","20/9/2021", "14h00", "Peach", "maxime@lamzone.com, alex@lamzone.com"),
            new Meeting(2,meetingColor2(), "Réunion B","21/9/2021", "16h00", "Mario", "paul@lamzone.com, viviane@lamzone.com"),
            new Meeting(3,meetingColor2(), "Réunion C","22/9/2021", "19h00", "Luigi", "amandine@lamzone.com, luc@lamzone.com")
    );

    public static int generateColor() {
        meetingColor = rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        return meetingColor;
    }

    public static int meetingColor() {
        meetingColor = rgb(237, 217, 208);
        return meetingColor;
    }

    public static int meetingColor2() {
        meetingColor = rgb(174, 206, 184);
        return meetingColor;
    }

    public static int getMeetingColor() {
        return meetingColor;
    }

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(dummyMeetings);
    }
}
