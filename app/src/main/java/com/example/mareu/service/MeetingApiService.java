package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 19/07/2021
 */
public interface MeetingApiService {

    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);
}
