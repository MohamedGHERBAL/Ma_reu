package com.example.mareu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.TextView;

import java.util.Calendar;

public class AddReunionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "AddReunionActivity";

    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reunion);

        // Calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        dateText = findViewById(R.id.TextViewDate);
        findViewById(R.id.TextDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    public void showDatePickerDialog(){
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
        String date = "Jour/Mois/Année: " + dayOfMonth + "/" + month + "/" + year;
        dateText.setText(date);
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
