package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed GHERBAL (pour OC) on 19/07/2021
 */
public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * {@inheritDoc}
     * @param meeting
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * {@inheritDoc}
     * @param meeting
     */
    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /**
     * {@inheritDoc}
     * @param room
     */
    @Override
    public List<Meeting> getMeetingRoomFilter(String room) {
        List<Meeting> mMeetingFiltered = new ArrayList<>();

        for (Meeting meeting : meetings) {
            if (meeting.getLocation().equals(room)) mMeetingFiltered.add(meeting);
        }
        return mMeetingFiltered;
    }
}
