package com.example.android.stockholmtourguide;

import android.app.FragmentManager;
import android.content.Context;
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
    private Context mContext;
    public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fm, Cursor cursor,Context context){
        super(fm);
        mCursor = cursor;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (mCursor.moveToPosition(position)){
            Bundle arguments = new Bundle();
            Log.i("ScreenAdapter", "the current position is: "+ position);
            Log.i("ScreenAdapterMain", "the current position is: "+ MainActivity.currentPosition);
            int idColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_ID);
            int nameColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_NAME);
            int photoColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_PHOTO);
            int introductionColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_INTRODUCTION);
            int phoneColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_PHONE);
            int websiteColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_WEBSITE);
            int addressColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_ADDRESS);
            int openTimeColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_OPEN_TIME);
            int emailColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_EMAIL);

            int id = mCursor.getInt(idColumnIndex);
            String name = mCursor.getString(nameColumnIndex);
            byte[] photoBlob = mCursor.getBlob(photoColumnIndex);
            String introduction = mCursor.getString(introductionColumnIndex);
            String phone = mCursor.getString(phoneColumnIndex);
            String website = mCursor.getString(websiteColumnIndex);
            String address = mCursor.getString(addressColumnIndex);
            String openTime = mCursor.getString(openTimeColumnIndex);
            String email = mCursor.getString(emailColumnIndex);

            String[] value = new String[]{name,introduction,phone,website,address,openTime,email};
            arguments.putStringArray(mContext.getString(R.string.string_array_key),value);
            arguments.putByteArray(mContext.getString(R.string.photo_blob_key),photoBlob);
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
