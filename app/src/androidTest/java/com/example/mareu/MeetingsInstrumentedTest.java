package com.example.mareu;

import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.utils.DeleteViewAction;
import com.example.mareu.utils.NumberPicker;
import com.example.mareu.utils.RecyclerViewItemCountAssertion;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static java.util.EnumSet.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingsInstrumentedTest {

    private MainActivity mainActivity;
    private MeetingApiService meetingApiService;

    private static final int ITEMS_COUNT = 3;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mainActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("com.example.mareu", appContext.getPackageName());
    }

    @Test
    public void meetingsList_shouldNotBeEmpty() {
        onView(withId(R.id.list_meetings))
                .check(matches(hasMinimumChildCount(3)));
    }

    @Test
    public void meetingsList_deleteAction_shouldRemoveMeeting() {
        // Check the list_meetings view and ITEM_COUNT
        onView(withId(R.id.list_meetings))
                .check(withItemCount(ITEMS_COUNT));
        // Click trash logo for the last meeting on the list
        onView(withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new DeleteViewAction()));
        // Check if ITEMS_COUNT is decreased by 1
        onView(withId(R.id.list_meetings))
                .check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void checkIf_AddMeeting_Work() {
        // Open AddMeetingActivity
        onView(withId(R.id.add_meeting))
            .perform(click());

        // Define "Test" text to titleMeeting
        onView(withId(R.id.titleMeeting))
                .perform(typeText("Test"));

        // Open DatePickerDialog and default date
        // Then click on "OK" button
        onView(withId(R.id.dateButton))
                .perform(click());
        onView(withText("OK"))
                .perform(click());

        // Open TimePickerDialog and set hour to 1 and minute to 1
        // Then click on "OK" button
        onView(withId(R.id.timeButton))
                .perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(NumberPicker.setTime(1,1));
        onView(withText("OK")).perform(click());

        // Define "Salle T" text to room
        onView(withId(R.id.roomSpinner))
                .perform(click());
        onView(withText("Donkey")).perform(click());
                //.perform(swipeUp());

        // Define two users by mail to users
        onView(withId(R.id.users))
                .perform(typeText("test@test.com, test2@lamzone.com"));
                closeSoftKeyboard();

        // Then click on "Ajouter"
        onView(withId(R.id.create))
                .perform(click());

        // And check if meeting is increased by 1
        onView(withId(R.id.list_meetings))
                .check(withItemCount(ITEMS_COUNT + 1));
    }

    @Test
    public void checkIf_RoomFilter_Work() {
        // Room Filter
        onView(withId(R.id.main_content))
                .check(matches(hasMinimumChildCount(3)));
        onView(withId(R.id.menu_item1))
                .perform(click());
        onView(withText(endsWith("Trier par salle")))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText("Peach")).perform(click());
        onView(withText("OK")).perform(click());

        // Check if ITEMS_COUNT is decreased by 1
        onView(withId(R.id.list_meetings))
                .check(withItemCount(ITEMS_COUNT - 2));
    }

    @Test
    public void checkIf_DateFilter_Work() {
        // Date Filter
        onView(withId(R.id.main_content))
                .check(matches(hasMinimumChildCount(3)));
        onView(withId(R.id.menu_item1))
                .perform(click());
        onView(withText(endsWith("Trier par date")))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2021, 8, 17));
        onView(withText("OK")).perform(click());

        // Check if ITEMS_COUNT is decreased by 2
        onView(withId(R.id.list_meetings))
                .check(withItemCount(ITEMS_COUNT - 2));
    }

}
