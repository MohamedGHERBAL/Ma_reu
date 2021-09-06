package com.example.mareu;

import com.example.mareu.DI.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.MeetingApiService;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class MeetingServiceUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetings_WithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> exceptedMeetings = DummyMeetingGenerator.dummyMeetings;
        MatcherAssert.assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(exceptedMeetings.toArray()));
        List<Meeting> actualMeetings = service.getMeetings();

        assertEquals(exceptedMeetings.size(), actualMeetings.size());
        assertEquals(exceptedMeetings.get(1), actualMeetings.get(1));
    }

    @Test
    public void deleteMeetings_WithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);

        assertFalse (service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void createMeetings_WithSuccess () {
        Meeting testMeeting = new Meeting(0, 200, "Test", "01/1/2021", "00h00", "Peach", "test@lamzone.com");
        service.createMeeting(testMeeting);

        assertTrue (service.getMeetings().contains(testMeeting));
    }

    @Test
    public void filterRoom_WithSuccess() {
        Meeting test = new Meeting(0,200,"Test", "01/1/2021", "00h00", "Peach", "test@lamzone.com");
        service.createMeeting(test);

        assertEquals (2, service.getMeetingRoomFilter("Peach").size());
    }
}
