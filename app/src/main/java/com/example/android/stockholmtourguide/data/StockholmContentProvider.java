package com.example.android.stockholmtourguide.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.example.android.stockholmtourguide.data.StockholmContract.StockholmEntry;

import com.example.android.stockholmtourguide.R;

public class StockholmContentProvider extends ContentProvider {
    private StockholmDBOpenHelper helper;
    private static final int ATTRACTION = 100;
    private static final int ATTRACTION_ID = 101;
    private static final int RESTAURANT = 200;
    private static final int RESTAURANT_ID = 201;
    private static final int HOTEL = 300;
    private static final int HOTEL_ID = 301;
    private static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final String LOG_TAG = StockholmContentProvider.class.getName();

    static {
        mUriMatcher.addURI(StockholmEntry.CONTENT_AUTHORITY, StockholmEntry.ATTRACTION_PATH,ATTRACTION);
        mUriMatcher.addURI(StockholmEntry.CONTENT_AUTHORITY, StockholmEntry.ATTRACTION_PATH + "#",ATTRACTION_ID);
        mUriMatcher.addURI(StockholmEntry.CONTENT_AUTHORITY, StockholmEntry.RESTAURANT_PATH,RESTAURANT);
        mUriMatcher.addURI(StockholmEntry.CONTENT_AUTHORITY, StockholmEntry.RESTAURANT_PATH+ "#",RESTAURANT_ID);
        mUriMatcher.addURI(StockholmEntry.CONTENT_AUTHORITY, StockholmEntry.HOTEL_PATH,HOTEL);
        mUriMatcher.addURI(StockholmEntry.CONTENT_AUTHORITY, StockholmEntry.HOTEL_PATH+ "#",HOTEL_ID);
    }
    @Override
    public boolean onCreate() {
        helper = new StockholmDBOpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int match = mUriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        Cursor cursor;
        switch (match){
            case ATTRACTION:
                cursor = sqLiteDatabase.query(StockholmEntry.TABLE_NAME_ATTRACTIONS,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case ATTRACTION_ID:
                selection = StockholmEntry.TABLE_COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(StockholmEntry.TABLE_NAME_ATTRACTIONS,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case RESTAURANT:
                cursor = sqLiteDatabase.query(StockholmEntry.TABLE_NAME_RESTAURANTS,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case RESTAURANT_ID:
                selection = StockholmEntry.TABLE_COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(StockholmEntry.TABLE_NAME_RESTAURANTS,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case HOTEL:
                cursor = sqLiteDatabase.query(StockholmEntry.TABLE_NAME_HOTELS,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case HOTEL_ID:
                selection = StockholmEntry.TABLE_COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(StockholmEntry.TABLE_NAME_HOTELS,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException(getContext().getString(R.string.cannot_query_error_msg)+" "+uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = mUriMatcher.match(uri);
        switch (match){
            case ATTRACTION:
                return StockholmEntry.ATTRACTION_CONTENT_LIST_TYPE;
            case ATTRACTION_ID:
                return StockholmEntry.ATTRACTION_CONTENT_ITEM_TYPE;
            case RESTAURANT:
                return StockholmEntry.RESTAURANT_CONTENT_LIST_TYPE;
            case RESTAURANT_ID:
                return StockholmEntry.RESTAURANT_CONTENT_ITEM_TYPE;
            case HOTEL:
                return StockholmEntry.HOTEL_CONTENT_LIST_TYPE;
            case HOTEL_ID:
                return StockholmEntry.HOTEL_CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException(getContext().getString(R.string.cannot_get_type_error_msg)+" "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = mUriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        long rowInsertedId;
        switch (match) {
            case ATTRACTION:
                rowInsertedId = sqLiteDatabase.insert(StockholmEntry.TABLE_NAME_ATTRACTIONS,null,values);
                break;
            case RESTAURANT:
                rowInsertedId = sqLiteDatabase.insert(StockholmEntry.TABLE_NAME_RESTAURANTS,null,values);
                break;
            case HOTEL:
                rowInsertedId = sqLiteDatabase.insert(StockholmEntry.TABLE_NAME_HOTELS,null,values);
                break;
            default:
                throw new IllegalArgumentException(getContext().getString(R.string.cannot_insert_error_msg) + " " + uri);
        }
        if (rowInsertedId == -1){
            Log.i(LOG_TAG,getContext().getString(R.string.insertion_in_table_failed_msg)+" "+uri);
        }
        Uri returnedUri = ContentUris.withAppendedId(uri,rowInsertedId);
        return returnedUri;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = mUriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        int rowDeletedId;
        switch (match){
            case ATTRACTION:
                rowDeletedId = sqLiteDatabase.delete(StockholmEntry.TABLE_NAME_ATTRACTIONS,selection,selectionArgs);
                return rowDeletedId;
            case ATTRACTION_ID:
                selection = StockholmEntry.TABLE_COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowDeletedId = sqLiteDatabase.delete(StockholmEntry.TABLE_NAME_ATTRACTIONS,selection,selectionArgs);
                return rowDeletedId;
            case RESTAURANT:
                rowDeletedId = sqLiteDatabase.delete(StockholmEntry.TABLE_NAME_RESTAURANTS,selection,selectionArgs);
                return rowDeletedId;
            case RESTAURANT_ID:
                selection = StockholmEntry.TABLE_COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowDeletedId = sqLiteDatabase.delete(StockholmEntry.TABLE_NAME_RESTAURANTS,selection,selectionArgs);
                return rowDeletedId;
            case HOTEL:
                rowDeletedId = sqLiteDatabase.delete(StockholmEntry.TABLE_NAME_HOTELS,selection,selectionArgs);
                return rowDeletedId;
            case HOTEL_ID:
                selection = StockholmEntry.TABLE_COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowDeletedId = sqLiteDatabase.delete(StockholmEntry.TABLE_NAME_HOTELS,selection,selectionArgs);
                return rowDeletedId;
            default:
                throw new IllegalArgumentException(getContext().getString(R.string.cannot_delete_error_msg)+" "+uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = mUriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        int rowUpdatedId;
        switch (match){
            //todo:check validity of values
            case ATTRACTION:
                rowUpdatedId = sqLiteDatabase.update(StockholmEntry.TABLE_NAME_ATTRACTIONS,values,selection,selectionArgs);
                return rowUpdatedId;
            case ATTRACTION_ID:
                selection = StockholmEntry.TABLE_COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowUpdatedId = sqLiteDatabase.update(StockholmEntry.TABLE_NAME_ATTRACTIONS,values,selection,selectionArgs);
                return rowUpdatedId;
            case RESTAURANT:
                rowUpdatedId = sqLiteDatabase.update(StockholmEntry.TABLE_NAME_RESTAURANTS,values,selection,selectionArgs);
                return rowUpdatedId;
            case RESTAURANT_ID:
                selection = StockholmEntry.TABLE_COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowUpdatedId = sqLiteDatabase.update(StockholmEntry.TABLE_NAME_RESTAURANTS,values,selection,selectionArgs);
                return rowUpdatedId;
            case HOTEL:
                rowUpdatedId = sqLiteDatabase.update(StockholmEntry.TABLE_NAME_HOTELS,values,selection,selectionArgs);
                return rowUpdatedId;
            case HOTEL_ID:
                selection = StockholmEntry.TABLE_COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowUpdatedId = sqLiteDatabase.update(StockholmEntry.TABLE_NAME_HOTELS,values,selection,selectionArgs);
                return rowUpdatedId;
            default:
                throw new IllegalArgumentException(getContext().getString(R.string.cannot_update_error_msg)+" "+uri);
        }
    }
}
