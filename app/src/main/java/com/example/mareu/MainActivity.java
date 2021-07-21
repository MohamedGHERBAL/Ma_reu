package com.example.mareu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mareu.databinding.MainActivityBinding;
import com.example.mareu.ui.main.MainFragment;


public class MainActivity extends AppCompatActivity {

    MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.addReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddReunionActivity();
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, MainFragment.newInstance())
                    .commitNow();
        }
    }

    public void openAddReunionActivity() {
        Intent intent = new Intent(this, AddReunionActivity.class);
        startActivity(intent);
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
            case R.id.date:
                Toast.makeText(this, "La liste est trié par date.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.location:
                Toast.makeText(this, "La liste est trié par lieu.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}