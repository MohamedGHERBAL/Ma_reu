package com.example.mareu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.mareu.DI.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeetingActivity extends AppCompatActivity {

    @BindView(R.id.titleMeetingLyt)
    TextInputLayout titleMeetingInput;
    @BindView(R.id.roomLyt)
    TextInputLayout roomInput;
    @BindView(R.id.usersLyt)
    TextInputLayout usersInput;
    @BindView(R.id.create)
    MaterialButton addButton;

    private static final String TAG = "AddMeetingActivity";

    Button timeButton;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);

        // Calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();

        timeButton = findViewById(R.id.timeButton);
    }

    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02dh%02d",hour, minute));
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Définir une heure");
        timePickerDialog.show();
    }

    private void init() {
        titleMeetingInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                addButton.setEnabled(s.length() > 0);
            }
        });
    }

    @OnClick(R.id.create)
    void addMeeting() {
        Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                titleMeetingInput.getEditText().getText().toString(),
                timeButton.getText().toString(),
                roomInput.getEditText().getText().toString(),
                usersInput.getEditText().getText().toString()
        );

        Intent intent = new Intent();
        intent.putExtra("meeting", meeting);
        setResult(43, intent);
        finish();
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivityForResult(activity, intent, 43, null);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
