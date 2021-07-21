package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 19/07/2021
 */
public interface ReunionApiService {

    /**
     * Get all my Reunions
     * @return {@link List}
     */
    List<Reunion> getReunions();

    /**
     * Deletes a reunion
     * @param reunion
     */
    void deleteReunion(Reunion reunion);

    /**
     * Create a reunion
     * @param reunion
     */
    void createReunion(Reunion reunion);
}
