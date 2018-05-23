package com.example.android.stockholmtourguide;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ScreenSlidePagerFragment extends android.support.v4.app.Fragment {
    private int pos;
    private String[] cursor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cursor = getArguments()!=null?getArguments().getStringArray("cursor"):null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.screen_slide_pager_fragment,container,false);
        ImageView mPhotoV;
        TextView mNameV, mIntroductionV, mOpenTimeV, mPhoneV, mEmailV, mWebsiteV, mAddressV,mPriceV;
        RatingBar ratingBar;
        mPhotoV = fragmentView.findViewById(R.id.detail_view_photo);
        mNameV = fragmentView.findViewById(R.id.detail_view_name);
        mIntroductionV = fragmentView.findViewById(R.id.detail_view_introduction);
        mPhoneV = fragmentView.findViewById(R.id.detail_view_phone);
        mEmailV = fragmentView.findViewById(R.id.detail_view_email);
        mWebsiteV = fragmentView.findViewById(R.id.detail_view_website);
        mAddressV = fragmentView.findViewById(R.id.detail_view_address);
        mOpenTimeV = fragmentView.findViewById(R.id.detail_view_open_time);
        mPriceV = fragmentView.findViewById(R.id.detail_view_attraction_price);
        ratingBar = fragmentView.findViewById(R.id.ratingBar);
        //todo:set up views data based on pos
        //test data
        String name = cursor[0];
        int photoId = Integer.parseInt(cursor[1]);
        String introduction = cursor[2];
        String phone = cursor[3];
        String email = cursor[4];
        String website = cursor[5];
        String address = cursor[6];
        String price = cursor[7];
        int rating = Integer.parseInt(cursor[8]);
        String openTime = cursor[9];

        mPhotoV.setImageResource(photoId);
        mNameV.setText(name);
        mIntroductionV.setText(introduction);
        mPhoneV.setText(phone);
        mEmailV.setText(email);
        mWebsiteV.setText(website);
        mAddressV.setText(address);
        mOpenTimeV.setText(openTime);
        mPriceV.setText(price);
        //todo:set rating bar correctly
        ratingBar.setNumStars(rating);

        return fragmentView;
    }
}
