package com.example.android.stockholmtourguide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by ceciliaHumlelu on 2018-02-09.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new CityFragment();
        } else if (position == 1){
            return TabFragment.newInstance(mContext.getString(R.string.fragment_two));
        } else if (position == 2){
            return TabFragment.newInstance(mContext.getString(R.string.fragment_three));
        }else{
            return TabFragment.newInstance(mContext.getString(R.string.fragment_four));
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int[] imageResId = new int[]{R.drawable.ic_location_city_black_24dp,R.drawable.ic_photo_camera_black_24dp,
        R.drawable.ic_hotel_black_24dp,R.drawable.ic_restaurant_black_24dp};
        String[] tabTitleResId = new String[]{mContext.getString(R.string.fragment_one),
                mContext.getString(R.string.fragment_two), mContext.getString(R.string.fragment_three),
                mContext.getString(R.string.fragment_four)};
        Drawable image = mContext.getResources().getDrawable(imageResId[position]);
        image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" "+tabTitleResId[position]);
        ImageSpan imageSpan = new ImageSpan(image,ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan,0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
