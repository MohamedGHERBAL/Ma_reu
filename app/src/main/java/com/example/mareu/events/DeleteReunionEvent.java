package com.example.mareu.events;

import com.example.mareu.model.Reunion;

/**
 * Created by Mohamed GHERBAL (pour OC) on 20/07/2021
 */
public class DeleteReunionEvent {
    /**
     * Reunion to delete
     */
    public Reunion reunion;

    /**
     * Constructor.
     * @param reunion
     */
    public DeleteReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}
