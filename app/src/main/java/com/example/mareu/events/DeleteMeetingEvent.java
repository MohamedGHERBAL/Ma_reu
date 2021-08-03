package com.example.mareu.events;

import com.example.mareu.model.Meeting;

/**
 * Created by Mohamed GHERBAL (pour OC) on 20/07/2021
 */
public class DeleteMeetingEvent {
    /**
     * Meeting to delete
     */
    public Meeting meeting;

    /**
     * Constructor.
     * @param meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }
}
