package com.example.mareu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mareu.databinding.MainActivityBinding;
import com.example.mareu.ui.main.MainFragment;



public class MainActivity extends AppCompatActivity {

    // for ViewBinding
    MainActivityBinding binding;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment mainFragment = getSupportFragmentManager().findFragmentByTag("MainFragment");

        if (mainFragment != null) {
            mainFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Perform click on FloatingActionButton to load addMeeting()
        binding.addMeeting.setOnClickListener(v -> addMeeting());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, MainFragment.newInstance(), "MainFragment")
                    .commitNow();
        }
    }

    // Use to open AddMeetingActivity
    void addMeeting() {
        Intent intent = new Intent(this, AddMeetingActivity.class);
        startActivityForResult(intent, 43);
    }

    // Enable menu on actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    // ItemMenu on actionbar do something onClick...
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.datefilter:
                onDateFilterSelected();
                Toast.makeText(this, "La liste est triée par date.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.roomfilter:
                onRoomFilterSelected();
                Toast.makeText(this, "Séléctionner une salle", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.resetfilter:
                onResetFilterSelected();
                Toast.makeText(this, "La liste à été restaurée", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Use for DateFilter
    public void onDateFilterSelected() {
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("MainFragment");
        if(mainFragment != null) {
            mainFragment.showDatePickerDialog();
        }
    }

    // Use for RoomFilter
    public void onRoomFilterSelected() {
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("MainFragment");
        if (mainFragment != null) {
            mainFragment.filterRoom();
        }
    }

    // Use for reset filter list
    public void onResetFilterSelected() {
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("MainFragment");
        if (mainFragment != null) {
            mainFragment.resetFilter();
        }
    }
}
