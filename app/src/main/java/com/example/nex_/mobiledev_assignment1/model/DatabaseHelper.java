package com.example.nex_.mobiledev_assignment1.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper ourInstance;

    private static final String DATABASE_NAME = "Assignemnt2.db";
    private static final String TRACKABLE_TABLE = "trackable";
    private static final String TRACKABLE_COL_1 = "ID";
    private static final String TRACKABLE_COL_2 = "NAME";
    private static final String TRACKABLE_COL_3 = "DES";
    private static final String TRACKABLE_COL_4 = "URL";
    private static final String TRACKABLE_COL_5 = "CATEGORY";

    public static final String TRACKING_TABLE = "tracking";
    public static final String TRACKING_COL_1 = "ID";
    public static final String TRACKING_COL_2 = "TITLE";
    public static final String TRACKING_COL_3 = "STARTTIME";
    public static final String TRACKING_COL_4 = "MEETTIME";
    public static final String TRACKING_COL_5 = "ENDTIME";
    public static final String TRACKING_COL_6 = "MEETLOCATION";
    public static final String TRACKING_COL_7 = "TRACKABLEID";

    public static synchronized DatabaseHelper getInstance(Context context){
        if (ourInstance == null){
            ourInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return ourInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TRACKABLE_TABLE + " (ID INTEGER PRIMARY KEY, NAME TEXT, DES TEXT, URL TEXT, CATEGORY TEXT) ");
        db.execSQL("create table " + TRACKING_TABLE + " (ID TEXT PRIMARY KEY, TITLE TEXT, STARTTIME TEXT, MEETTIME TEXT, ENDTIME TEXT, MEETLOCATION TEXT, TRACKABLEID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRACKABLE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TRACKING_TABLE);
        onCreate(db);
    }



    public  boolean insertTrackaleData(int ID, String name, String description, String URL, String category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRACKABLE_COL_1, ID);
        contentValues.put(TRACKABLE_COL_2, name);
        contentValues.put(TRACKABLE_COL_3, description);
        contentValues.put(TRACKABLE_COL_4, URL);
        contentValues.put(TRACKABLE_COL_5, category);

        double result = db.insert(TRACKABLE_TABLE, null, contentValues);
        if (result == -1){
            return false;
        }else {
            return  true;
        }
    }

    public  boolean insertTrackingData(String ID, String title, String startTime, String meetTime, String endTime, String meetLocation, int trackableID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(TRACKING_COL_1, ID);
        contentValues2.put(TRACKING_COL_2, title);
        contentValues2.put(TRACKING_COL_3, startTime);
        contentValues2.put(TRACKING_COL_4, meetTime);
        contentValues2.put(TRACKING_COL_5, endTime);
        contentValues2.put(TRACKING_COL_6, meetLocation);
        contentValues2.put(TRACKING_COL_7, trackableID);
        double result = db.insert(TRACKING_TABLE, null, contentValues2);
        return !(result == -1);
    }

    public  boolean updateTrackingData(String ID, String title, String startTime, String meetTime, String endTime, String meetLocation, int trackableID){
        SQLiteDatabase db = this.getWritableDatabase();
        String strFilter = "ID= '" + ID + "'";
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(TRACKING_COL_1, ID);
        contentValues3.put(TRACKING_COL_2, title);
        contentValues3.put(TRACKING_COL_3, startTime);
        contentValues3.put(TRACKING_COL_4, meetTime);
        contentValues3.put(TRACKING_COL_5, endTime);
        contentValues3.put(TRACKING_COL_6, meetLocation);
        contentValues3.put(TRACKING_COL_7, trackableID);
        double result = db.update(TRACKING_TABLE,  contentValues3, strFilter, null);
        return !(result == -1);
    }

    public boolean deleteTrackingData(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        double result = db.delete(TRACKING_TABLE, TRACKABLE_COL_1 + "= '" + ID + "'", null);
        return !(result == -1);
    }

    public Cursor getAllItems(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TRACKING_TABLE,
                null,
                null,
                null,
                null,
                null,
                TRACKING_COL_2 + " ASC"
        );
    }
}
