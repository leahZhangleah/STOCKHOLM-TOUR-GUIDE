package com.example.android.stockholmtourguide;

import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;

import com.example.android.stockholmtourguide.data.StockholmContract.StockholmEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private Cursor mCursor;
    private int cursorPos;
    public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fm, Cursor cursor, int position){
        super(fm);
        mCursor = cursor;
        cursorPos = position;
    }

    @Override
    public Fragment getItem(int position) {
        if (mCursor.moveToPosition(cursorPos)){
            Log.i("ScreenSlideAdapter","the clicked position is: "+ cursorPos);
            Bundle arguments = new Bundle();

            int idColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_ID);
            int nameColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_NAME);
            int photoColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_PHOTO);
            int introductionColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_INTRODUCTION);
            int phoneColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_PHONE);
            int emailColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_EMAIL);
            int websiteColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_WEBSITE);
            int addressColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_ADDRESS);
            int priceColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_PRICE);
            int ratingColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_RATING);
            int openTimeColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_OPEN_TIME);

            int id = mCursor.getInt(idColumnIndex);
            String name = mCursor.getString(nameColumnIndex);
            int photoId = mCursor.getInt(photoColumnIndex);
            String introduction = mCursor.getString(introductionColumnIndex);
            String phone = mCursor.getString(phoneColumnIndex);
            String email = mCursor.getString(emailColumnIndex);
            String website = mCursor.getString(websiteColumnIndex);
            String address = mCursor.getString(addressColumnIndex);
            String price = mCursor.getString(priceColumnIndex);
            int rating = mCursor.getInt(ratingColumnIndex);
            String openTime = mCursor.getString(openTimeColumnIndex);

            String[] value = new String[]{name,String.valueOf(photoId),introduction,phone,email,website,address,price,String.valueOf(rating),openTime};
            arguments.putStringArray("cursor",value);

            ScreenSlidePagerFragment fragment = new ScreenSlidePagerFragment();
            fragment.setArguments(arguments);
            return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        if (mCursor!= null){
            return mCursor.getCount();
        }
        return 0;
    }

    public void swapCursor(Cursor newCursor){
        if (newCursor == mCursor){
            return;
        }
        if (newCursor!=null){
            mCursor=newCursor;
            notifyDataSetChanged();
        }else{
            mCursor = null;
        }
    }
}
