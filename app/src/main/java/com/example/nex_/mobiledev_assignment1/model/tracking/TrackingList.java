package com.example.nex_.mobiledev_assignment1.model.tracking;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.nex_.mobiledev_assignment1.model.DatabaseHelper;

import java.util.ArrayList;
import java.util.UUID;

import static android.support.constraint.Constraints.TAG;

public class TrackingList {
    private static final TrackingList ourInstance = new TrackingList();

    public static TrackingList getInstance() {
        return ourInstance;
    }

    //This class hold an array of tracking objects
    private ArrayList<Tracking> trackingList;
    private ArrayList<String> trackingTitle;
    private ArrayList<String> trackingMeetTime;

    public ArrayList<Tracking> getTrackingList() {
        return trackingList;
    }

    private TrackingList(){
        trackingList = new ArrayList<>();
        trackingTitle = new ArrayList<>();
        trackingMeetTime = new ArrayList<>();

    }
    public void addTracking(Context context,String title, int trackableID, String startTime, String meetTime, String endTime, String meetLocation){
        String ID = UUID.randomUUID().toString();
        Tracking tracking = new Tracking(ID, title, trackableID, startTime, endTime, meetTime, meetLocation);
        if (DatabaseHelper.getInstance(context).insertTrackingData(ID, title, startTime, meetTime, endTime, meetLocation, trackableID)){
            System.out.println("Add Tracking Succeed");

        }else{
            System.out.println("Add Tracking Failed");
        }
        trackingList.add(tracking);
        trackingTitle.add(title);
        trackingMeetTime.add(meetTime);

    }

    public void deleteTracking(int pickedEvent){
        TrackingList.getInstance().getTrackingList().remove(pickedEvent);

    }

    //private ArrayList<objectname> objectList  = getResults();

    public ArrayList<Tracking> getTracking(Context context) {


        ArrayList<Tracking> resultList = new ArrayList<>();


        Cursor c = DatabaseHelper.getInstance(context).getAllItems(); //function to retrieve all values from a table- written in MyDb.java file
        while (c.moveToNext())
        {
            String ID = c.getString(c.getColumnIndex(DatabaseHelper.TRACKING_COL_1));
            String title = c.getString(c.getColumnIndex(DatabaseHelper.TRACKING_COL_2));
            String startTime = c.getString(c.getColumnIndex(DatabaseHelper.TRACKING_COL_3));
            String meetTime = c.getString(c.getColumnIndex(DatabaseHelper.TRACKING_COL_4));
            String endTime = c.getString(c.getColumnIndex(DatabaseHelper.TRACKING_COL_5));
            String meetLocation = c.getString(c.getColumnIndex(DatabaseHelper.TRACKING_COL_6));
            int trackableID = c.getInt(c.getColumnIndex(DatabaseHelper.TRACKING_COL_7));

            try
            {
                Tracking tracking = new Tracking(ID, title, trackableID, startTime, endTime, meetTime, meetLocation);
                resultList.add(tracking);
            }
            catch (Exception e) {
                Log.e(TAG, "Error " + e.toString());
            }

        }

        c.close();

        return resultList;
    }

    public void setTrackingList(ArrayList<Tracking> trackingList) {
        this.trackingList = trackingList;
    }

    public ArrayList<String> getTrackingTitle() {
        return trackingTitle;
    }

    public ArrayList<String> getTrackingMeetTime() {
        return trackingMeetTime;
    }
}
