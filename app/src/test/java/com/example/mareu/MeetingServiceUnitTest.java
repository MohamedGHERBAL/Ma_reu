package com.example.mareu;

import static android.graphics.Color.rgb;

import com.example.mareu.DI.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.MeetingApiService;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

    // Use for Test Only
    @Test
    public void addition_isCorrect() {
        assertEquals(3, 1 + 2);
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
    public void addMeeting_WithSuccess () {
        Room room = new room("Peach");
    }

    @Test
    public void filteringRoom_WithSuccess() {
        // void
        List<String> list = new ArrayList<>(Arrays.asList("1@1.1", "2@2.2"));
        Calendar cal = Calendar.getInstance();
        // Meeting test = new Meeting(rgb(100, 150, 200), "Salle A", cal.getTime(), "test", list);
        service.createMeeting(test);

        assertEquals(2, service.getMeetingRoomFilter("Peach").size());
    }
}
