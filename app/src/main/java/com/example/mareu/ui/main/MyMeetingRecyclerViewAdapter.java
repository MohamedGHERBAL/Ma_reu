package com.example.mareu.ui.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed GHERBAL (pour OC) on 21/07/2021
 */
public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder>  {

    private List<Meeting> mMeetings;
    // private List<Meeting> mMeetingsFull;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        Log.i("MyMeetingRecViewAda", "MyMeetingRecyclerViewAdapter is called !");
        mMeetings = items;
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
                Log.i("MeetingRecyclerViewAda", "mDeleteButton is called !");
                mMeetings.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings == null ? 0 : mMeetings.size();
    }

    public void setData(List<Meeting> meetings) {
        Log.i("MeetingRecyclerViewAda", "setData is called !");
        this.mMeetings = meetings;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mReunionColor;
        @BindView(R.id.item_list_name)
        public TextView mReunionInfo;
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
