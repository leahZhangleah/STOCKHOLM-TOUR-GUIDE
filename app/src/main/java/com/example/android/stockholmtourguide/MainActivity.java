package com.example.android.stockholmtourguide;

import android.content.ContentValues;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.stockholmtourguide.data.StockholmContract.StockholmEntry;

public class MainActivity extends AppCompatActivity {
    private static final Uri attractionUri = StockholmEntry.ATTRACTION_CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertDataInDB();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        SimpleFragmentPagerAdapter pgAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pgAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    //todo:this method will not be necessary once there is a ready-built database
  private void insertDataInDB(){
        ContentValues value = new ContentValues();
        value.put(StockholmEntry.TABLE_COLUMN_NAME,"Stockholm palace");
        value.put(StockholmEntry.TABLE_COLUMN_INTRODUCTION,"bolibonba bolibonba");
        value.put(StockholmEntry.TABLE_COLUMN_PRICE,"free");
        value.put(StockholmEntry.TABLE_COLUMN_OPEN_TIME,"09:00-17:00");
        value.put(StockholmEntry.TABLE_COLUMN_ADDRESS,"södermalm 230");
        value.put(StockholmEntry.TABLE_COLUMN_EMAIL,"efgh@gmail.com");
        value.put(StockholmEntry.TABLE_COLUMN_PHONE,"+46 0725053010");
        value.put(StockholmEntry.TABLE_COLUMN_PHOTO,R.drawable.stockholm_palace);
        value.put(StockholmEntry.TABLE_COLUMN_RATING,5);
        value.put(StockholmEntry.TABLE_COLUMN_WEBSITE,"WWW.stockholmpalace.SE");

        getContentResolver().insert(attractionUri,value);
    }
}
