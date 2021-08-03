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
import androidx.viewpager.widget.ViewPager;

import com.example.mareu.ui.main.ListMeetingPagerAdapter;
import com.example.mareu.ui.main.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    // UI Components

    //@BindView(R.id.tabs)
    //TabLayout mTabLayout;
    //@BindView(R.id.toolbar)
    //Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListMeetingPagerAdapter mPagerAdapter;
    //MainActivityBinding binding;

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
/*
        binding = MainActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
*/
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        //setSupportActionBar(mToolbar);
        mPagerAdapter = new ListMeetingPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        //mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

/*
        binding.addReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddReunionActivity();
            }
        });
*/
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, MainFragment.newInstance(), "MainFragment")
                    .commitNow();
        }
    }

/*
    public void openAddReunionActivity() {
        Intent intent = new Intent(this, AddReunionActivity.class);
        startActivity(intent);
    }
*/

    // Enable menu on actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @OnClick(R.id.add_meeting)
    void addMeeting() {
        //AddMeetingActivity.navigate(this);
        Intent intent = new Intent(this, AddMeetingActivity.class);
        startActivityForResult(intent, 43);
    }

    // ItemMenu on actionbar do something onClick...
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.date:

                Toast.makeText(this, "La liste est trié par date.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.room:

                Toast.makeText(this, "La liste est trié par lieu.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}