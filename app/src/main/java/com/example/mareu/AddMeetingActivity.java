package com.example.mareu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

import com.example.mareu.databinding.ActivityAddMeetingBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingGenerator;

import java.util.Calendar;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    // for ViewBinding
    private ActivityAddMeetingBinding binding;
    private static final String TAG = "AddMeetingActivity";

    final int color = DummyMeetingGenerator.generateColor(); // Use to generate random color for meeting
    int hour, minute; // Variables for popTimePicker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Use to display title for AddMeetingActivity
        setTitle("Ajouter une réunion");
        // Calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        listRooms();
        init();

        binding.dateButton.setOnClickListener(v -> showDatePickerDialog());
        binding.create.setOnClickListener(v -> addMeeting());
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;
        binding.dateButton.setText(date);
    }

    // Open POPTimePicker
    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                binding.timeButton.setText(String.format(Locale.getDefault(), "%02dh%02d",hour, minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        // timePickerDialog.setTitle("Définir une heure");
        timePickerDialog.show();
    }

    // Spinner list rooms
    private void listRooms() {
        Spinner dynamicSpinner = (Spinner) binding.roomSpinner;
        String[] items = new String[]
                { "Peach", "Mario", "Luigi",
                        "Donkey", "Bowser", "Wario",
                        "Toad", "Daisy", "Yoshi",
                        "Bowser Jr." };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);
        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void init() {
        binding.titleMeetingLyt.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                binding.create.setEnabled(s.length() > 0);
            }
        });
    }

    void addMeeting() {
        Meeting meeting = new Meeting(
                (int) System.currentTimeMillis(),
                color,
                binding.titleMeetingLyt.getEditText().getText().toString(),
                binding.dateButton.getText().toString(),
                binding.timeButton.getText().toString(),
                binding.roomSpinner.getSelectedItem().toString(),
                binding.usersLyt.getEditText().getText().toString()
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
