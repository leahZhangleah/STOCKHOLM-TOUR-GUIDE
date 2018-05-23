package com.example.android.stockholmtourguide.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class StockholmContract {
    private StockholmContract(){
    }
    public static class StockholmEntry implements BaseColumns {
        public static final String TABLE_NAME_ATTRACTIONS = "top_attractions";
        public static final String TABLE_NAME_HOTELS = "top_hotels";
        public static final String TABLE_NAME_RESTAURANTS = "top_restaurants";
        public static final String TABLE_COLUMN_ID = BaseColumns._ID;
        public static final String TABLE_COLUMN_NAME = "name";
        public static final String TABLE_COLUMN_INTRODUCTION = "introduction";
        public static final String TABLE_COLUMN_OPEN_TIME = "open_time";
        public static final String TABLE_COLUMN_PHONE = "phone";
        public static final String TABLE_COLUMN_EMAIL = "email";
        public static final String TABLE_COLUMN_WEBSITE = "website";
        public static final String TABLE_COLUMN_ADDRESS = "address";
        public static final String TABLE_COLUMN_PRICE = "price";
        public static final String TABLE_COLUMN_RATING = "rating";
        public static final String TABLE_COLUMN_PHOTO = "photo";

        public static final String CONTENT_AUTHORITY = "com.example.android.stockholmtourguide";
        public static final Uri BASE_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
        public static final String ATTRACTION_PATH = "attractions";
        public static final String RESTAURANT_PATH = "restaurants";
        public static final String HOTEL_PATH = "hotels";
        public static final Uri ATTRACTION_CONTENT_URI = Uri.withAppendedPath(BASE_URI,ATTRACTION_PATH);
        public static final Uri RESTAURANT_CONTENT_URI = Uri.withAppendedPath(BASE_URI,RESTAURANT_PATH);
        public static final Uri HOTEL_CONTENT_URI = Uri.withAppendedPath(BASE_URI,HOTEL_PATH);

        public static final String ATTRACTION_CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/"+ ATTRACTION_CONTENT_URI;
        public static final String ATTRACTION_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/"+ ATTRACTION_CONTENT_URI;
        public static final String RESTAURANT_CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + RESTAURANT_CONTENT_URI;
        public static final String RESTAURANT_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + RESTAURANT_CONTENT_URI;
        public static final String HOTEL_CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + HOTEL_CONTENT_URI;
        public static final String HOTEL_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + HOTEL_CONTENT_URI;
    }
}
