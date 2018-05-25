package com.example.android.stockholmtourguide;

import android.content.ContentValues;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.stockholmtourguide.data.StockholmContract.StockholmEntry;

public class MainActivity extends AppCompatActivity {
    public static int currentPosition;
    private static final String KEY_CURRENT_POSITION = "com.example.android.stockholmtourguide.key.currentposition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null){
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION,0);
            return;
        }
        Log.i("MainActivity oncreate", "the current position is: "+ currentPosition);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        SimpleFragmentPagerAdapter pgAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(pgAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_POSITION,currentPosition);
        Log.i("MainActivity", "the current position is: "+ currentPosition);

    }
}
