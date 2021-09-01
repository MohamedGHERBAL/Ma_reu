package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.Date;
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
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Filter meeting list for room
     * @param room
     */
    List<Meeting> getMeetingRoomFilter(String room);
}
