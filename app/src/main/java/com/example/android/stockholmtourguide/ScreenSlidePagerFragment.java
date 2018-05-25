package com.example.android.stockholmtourguide;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class ScreenSlidePagerFragment extends android.support.v4.app.Fragment {
    private String[] cursor;
    private byte[] photoBlob;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cursor = getArguments()!=null?getArguments().getStringArray(getString(R.string.string_array_key)):null;
        photoBlob = getArguments()!= null? getArguments().getByteArray(getString(R.string.photo_blob_key)):null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.screen_slide_pager_fragment,container,false);
        FloatingActionButton fab;
        ImageView mPhotoV;
        final TextView mNameV, mIntroductionV, mOpenTimeV, mPhoneV, mWebsiteV, mAddressV,mEmailV;
        LinearLayout openTimeLayout, emailLayout;
        fab = fragmentView.findViewById(R.id.close_btn);
        mPhotoV = fragmentView.findViewById(R.id.detail_view_photo);
        mNameV = fragmentView.findViewById(R.id.detail_view_name);
        mIntroductionV = fragmentView.findViewById(R.id.detail_view_introduction);
        mPhoneV = fragmentView.findViewById(R.id.detail_view_phone);
        mWebsiteV = fragmentView.findViewById(R.id.detail_view_website);
        mAddressV = fragmentView.findViewById(R.id.detail_view_address);
        mOpenTimeV = fragmentView.findViewById(R.id.detail_view_open_time);
        mEmailV = fragmentView.findViewById(R.id.detail_view_email);
        openTimeLayout = fragmentView.findViewById(R.id.open_time_layout);
        emailLayout = fragmentView.findViewById(R.id.email_layout);
        //todo:set up views data based on pos
        String name = cursor[0];
        Bitmap photo = BitmapFactory.decodeByteArray(photoBlob,0,photoBlob.length);
        String introduction = cursor[1];
        String phone = cursor[2];
        String website = cursor[3];
        String address = cursor[4];
        String openTime = cursor[5];
        String email = cursor[6];
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mPhotoV.setImageBitmap(photo);
        mNameV.setText(name);
        mIntroductionV.setText(introduction);
        mPhoneV.setText(phone);
        mPhoneV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.fromParts("tel",mPhoneV.getText().toString(),null));
                startActivity(intent);
            }
        });
        mWebsiteV.setText(website);
        mWebsiteV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(mWebsiteV.getText().toString()));
                startActivity(intent);
            }
        });
        mAddressV.setText(address);
        /*mAddressV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW)
            }
        });*/
        if (TextUtils.isEmpty(openTime)){
            openTimeLayout.setVisibility(View.GONE);
        }else{
            mOpenTimeV.setText(openTime);
        }

        if (TextUtils.isEmpty(email)){
            emailLayout.setVisibility(View.GONE);
        }else{
            mEmailV.setText(email);
            mEmailV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"+mEmailV.getText().toString()));
                    startActivity(intent);
                }
            });
        }
        return fragmentView;
    }
}
