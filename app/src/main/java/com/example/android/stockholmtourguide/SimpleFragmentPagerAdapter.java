package com.example.android.stockholmtourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ceciliaHumlelu on 2018-02-09.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new TopAttractionsFragment();
        } else if (position == 1){
            return new TopAttractionsFragment();
        } else {
            return new TopAttractionsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "TOP ATTRACTIONS";
            case 1: return "RESTAURANTS";
            case 2: return "ACTIVITIES";
            default:return null;
        }
    }
}
