package com.example.mareu.service;

import com.example.mareu.model.Reunion;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 12/07/2021
 */


public abstract class DummyReunionGenerator {
    
    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion(1, "Réunion A", "14h00", "Peach", "maxime@lamzone.com, alex@lamzone.com"),
            new Reunion(2, "Réunion B", "16h00", "Mario", "paul@lamzone.com, viviane@lamzone.com"),
            new Reunion(3, "Réunion C", "19h00", "Luigi", "amandine@lamzone.com, luc@lamzone.com")
    );
    
    static List<Reunion> generateReunion() {
        return new ArrayList<>(DUMMY_REUNIONS);
    }
}
