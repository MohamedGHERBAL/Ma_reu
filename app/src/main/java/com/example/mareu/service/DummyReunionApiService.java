package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 19/07/2021
 */
public class DummyReunionApiService implements  ReunionApiService {

    private List<Reunion> reunions = DummyReunionGenerator.generateReunion();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reunion> getReunions() {
        return reunions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

    /**
     * {@inheritDoc}
     * @param reunion
     */
    @Override
    public void createReunion(Reunion reunion) {
        reunions.add(reunion);
    }
}
