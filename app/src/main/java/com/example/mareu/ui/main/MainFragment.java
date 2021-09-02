package com.example.mareu.ui.main;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.mareu.DI.DI;
import com.example.mareu.R;

import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import com.example.mareu.service.MeetingApiService;
import com.example.mareu.utils.DateUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

public class MainFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    public MeetingApiService mApiService;
    private List<Meeting> mMeetings;
    private RecyclerView mRecyclerView;
    private MyMeetingRecyclerViewAdapter adapter;

    /**
     * Create and return a new instance
     * @return @{@link MainFragment}
     */
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNewInstanceApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("MainFragment", "onCreateView is called !");
        View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        Context context = view.getContext();
        adapter = new MyMeetingRecyclerViewAdapter(mMeetings);

        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);

        mMeetings = mApiService.getMeetings();
        adapter.setData(mMeetings);
        return view;
    }

    /**
     * Init the List of meetings
     */
    private void initList() {
        Log.i("MainFragment", "initList is called !");
        mApiService = DI.getNewInstanceApiService();
        mMeetings = mApiService.getMeetings();
        mRecyclerView.setAdapter(adapter);
        adapter.setData(mMeetings);
    }

    private void initListForRoom(String room) {
        Log.i("MainFragment", "initListForRoom is called !");
        adapter.setData(mApiService.getMeetingRoomFilter(room));
    }

    private void initListForReset() {
        Log.i("MainFragment", "initListForReset is called !");
        adapter.setData(mApiService.getMeetings());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        Log.i("MainFragment", "ActivityResult is called");

        if (requestCode == 43 && data != null) {
            Log.i("MainFragment", "Existing data !");
            Meeting meeting = data.getParcelableExtra("meeting");

            if (meeting != null) {
                Log.i("MainFragment", "meeting is not null !");
                mMeetings.add(meeting);
                adapter.notifyDataSetChanged();
                initListForReset();
            }

            else {
                Log.i("MainFragment", "Error : meeting is null !");
            }
        }
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.i(String.valueOf(getActivity()), "onDateSet is called !");
        String date = dayOfMonth + "/" + month + "/" + year;
        List<Meeting> filteredMeetingList = new ArrayList<Meeting>();

        for (Meeting meeting : mMeetings) {
            int dayOfMeeting;

            try {
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtils.stringToDate(meeting.getDate()));
                dayOfMeeting = cal.get(Calendar.DAY_OF_MONTH);

                if (dayOfMeeting == dayOfMonth) {
                    Log.i("MainFragment", "Corresponding date found !");
                    filteredMeetingList.add(meeting);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Log.i("MainFragment", "Filter list size: " + filteredMeetingList.size());
        adapter.setData(filteredMeetingList);
    }

    // Filter Room List
    public void filterRoom() {
        List<String> roomList = Room.getRooms();
        String[] roomArray = new String[roomList.size()];
        roomArray = roomList.toArray(roomArray);

        final String[] room = new String[1];
        final AlertDialog.Builder builderRoom = new AlertDialog.Builder(getContext());

        String[] finalRoomList = roomArray;

        builderRoom.setSingleChoiceItems(roomArray, -1, (dialog, which) -> room[0] = finalRoomList[which]);

        builderRoom.setPositiveButton("OK", (dialogInterface, i) -> initListForRoom(room[0]));
        builderRoom.setNegativeButton("Annuler", (dialog, whichButton) -> initListForReset());

        AlertDialog dialogRoom = builderRoom.create();

        dialogRoom.show();
    }

    // Call by the MainActivity.onResetFilterSelected and use for Reset filter list
    public void resetFilter() {
        initListForReset();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button.
     * @param event
     */
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.meeting);
        initList();
    }
}