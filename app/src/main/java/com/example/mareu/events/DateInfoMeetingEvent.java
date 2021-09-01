package com.example.mareu.events;

import com.example.mareu.model.Meeting;

/**
 * Created by Mohamed GHERBAL (pour OC) on 19/08/2021
 */
public class DateInfoMeetingEvent {

    public Meeting meeting;

    public DateInfoMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }
}
