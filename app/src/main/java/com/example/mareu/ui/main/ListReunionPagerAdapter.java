package com.example.mareu.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Mohamed GHERBAL (pour OC) on 20/07/2021
 */
public class ListReunionPagerAdapter extends FragmentPagerAdapter {

    public ListReunionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance();
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 1;
    }
}
