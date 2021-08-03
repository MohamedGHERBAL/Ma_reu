package com.example.mareu.ui.main;

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

import com.example.mareu.DI.DI;
import com.example.mareu.R;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MainFragment extends Fragment {

    //private MainViewModel mViewModel;
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
        Log.e("MainFragment", "onCreateView msg");
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
/*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }
*/
    /**
     * Init the List of meetings
     */
    private void initList() {
        mApiService = DI.getNewInstanceApiService();
        mMeetings = mApiService.getMeetings();
        mRecyclerView.setAdapter(adapter);
        adapter.setData(mMeetings);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        Log.e("MainFragment", "ActivityResult called");

        if (requestCode == 43 && data != null) {
            Log.e("MainFragment", "Existing data");
            Meeting meeting = data.getParcelableExtra("meeting");

            if (meeting != null) {
                Log.e("MainFragment", "meeting is not null");
                mMeetings.add(meeting);
                adapter.notifyDataSetChanged();
            }

            else{
                Log.e("MainFragment", "Error : meeting is null");
            }
        }
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

/*
    @Override
    public void onDestroy(){
        super.onDestroy();
        //DI.getNewInstanceApiService();
    }
*/

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