package com.example.android.stockholmtourguide.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.stockholmtourguide.data.StockholmContract.StockholmEntry;

public class StockholmDBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "stockholm_tour_guide.db";
    private static final int TABLE_VERSION = 1;

    public StockholmDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ATTRACTIONS_SQL_TABLE = "CREATE TABLE " + StockholmEntry.TABLE_NAME_ATTRACTIONS + "("
                + StockholmEntry.TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + StockholmEntry.TABLE_COLUMN_PHOTO + " INTEGER NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_NAME + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_INTRODUCTION + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_PHONE + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_EMAIL + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_ADDRESS + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_OPEN_TIME + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_PRICE + " DECIMAL(2) NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_RATING + " INTEGER NOT NULL DEFAULT 0,"
                + StockholmEntry.TABLE_COLUMN_WEBSITE + " TEXT NOT NULL"
                + ");";
        String CREATE_HOTELS_SQL_TABLE = "CREATE TABLE "+StockholmEntry.TABLE_NAME_HOTELS + "("
                + StockholmEntry.TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + StockholmEntry.TABLE_COLUMN_PHOTO + " INTEGER NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_NAME + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_INTRODUCTION + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_PHONE + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_EMAIL + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_ADDRESS + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_OPEN_TIME + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_PRICE + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_RATING + " INTEGER NOT NULL DEFAULT 0,"
                + StockholmEntry.TABLE_COLUMN_WEBSITE + " TEXT NOT NULL"
                + ");";

        String CREATE_RESTAURANTS_SQL_TABLE = "CREATE TABLE "+StockholmEntry.TABLE_NAME_RESTAURANTS + "("
                + StockholmEntry.TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + StockholmEntry.TABLE_COLUMN_PHOTO + " INTEGER NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_NAME + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_INTRODUCTION + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_PHONE + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_EMAIL + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_ADDRESS + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_OPEN_TIME + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_PRICE + " TEXT NOT NULL,"
                + StockholmEntry.TABLE_COLUMN_RATING + " INTEGER NOT NULL DEFAULT 0,"
                + StockholmEntry.TABLE_COLUMN_WEBSITE + " TEXT NOT NULL"
                + ");";

        db.execSQL(CREATE_ATTRACTIONS_SQL_TABLE);
        db.execSQL(CREATE_HOTELS_SQL_TABLE);
        db.execSQL(CREATE_RESTAURANTS_SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StockholmEntry.TABLE_NAME_RESTAURANTS);
        db.execSQL("DROP TABLE IF EXISTS " + StockholmEntry.TABLE_NAME_ATTRACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + StockholmEntry.TABLE_NAME_HOTELS);

        // Create new tables
        onCreate(db);
    }


}
