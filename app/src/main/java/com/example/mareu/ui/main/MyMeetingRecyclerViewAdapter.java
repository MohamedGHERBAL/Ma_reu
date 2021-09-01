package com.example.mareu.ui.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.MainActivity;
import com.example.mareu.R;
import com.example.mareu.events.DateInfoMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed GHERBAL (pour OC) on 21/07/2021
 */
public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder>  {

    private List<Meeting> mMeetings;
    private List<Meeting> mMeetingsFull;
    // private MeetingApiService meetingApiService;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        Log.e("MyMeetingRecViewAda", "MyMeetingRecyclerViewAdapter is call !");
        mMeetings = items;
        // mMeetingsFull = new ArrayList<>(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.mReunionColor.setColorFilter(meeting.getColor());
        holder.mReunionInfo.setText(meeting.getMeetingInfo());
        holder.mReunionMail.setText(meeting.getUsers());

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MeetingRecyclerViewAda", "mDeleteButton is call !");
                mMeetings.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DateInfoMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings == null ? 0 : mMeetings.size();
    }


    public void setData(List<Meeting> meetings) {
        Log.e("MeetingRecyclerViewAda", "setData is call !");
        this.mMeetings = meetings;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mReunionColor;
        @BindView(R.id.item_list_name)
        public TextView mReunionInfo; // old mReunionName
        @BindView(R.id.item_list_mail)
        public TextView mReunionMail;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
