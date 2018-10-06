package com.example.nex_.mobiledev_assignment1.controller;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import com.example.nex_.mobiledev_assignment1.model.DatabaseHelper;
import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableIO;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;
import com.example.nex_.mobiledev_assignment1.model.tracking.Tracking;
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;
import com.example.nex_.mobiledev_assignment1.view.App;

import java.util.UUID;

import static com.example.nex_.mobiledev_assignment1.model.DatabaseHelper.TRACKING_COL_1;
import static com.example.nex_.mobiledev_assignment1.model.DatabaseHelper.TRACKING_COL_2;
import static com.example.nex_.mobiledev_assignment1.model.DatabaseHelper.TRACKING_COL_3;
import static com.example.nex_.mobiledev_assignment1.model.DatabaseHelper.TRACKING_COL_4;
import static com.example.nex_.mobiledev_assignment1.model.DatabaseHelper.TRACKING_COL_5;
import static com.example.nex_.mobiledev_assignment1.model.DatabaseHelper.TRACKING_COL_6;
import static com.example.nex_.mobiledev_assignment1.model.DatabaseHelper.TRACKING_COL_7;

public class Controller {
    private static final Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    public void initTrackables(Context context) {
        System.out.println("initTrackables");
        /*TrackableList.getInstance().addTrackables(0, "Trackable A", "This is the first trackable", "https://google.com", "Category A");
        TrackableList.getInstance().addTrackables(1, "Trackable B", "This is the second trackable", "https://youtube.com", "Category A");
        TrackableList.getInstance().addTrackables(2, "Trackable C", "This is the third trackable", "https://reddit.com", "Category B");
        TrackableList.getInstance().addTrackables(3, "Trackable D", "This is the fourth trackable", "https://waitbutwhy.com", "Category B");
        TrackableList.getInstance().addTrackables(4, "Trackable E", "This is the fifth trackable", "https://store.steampowered.com", "Category C");*/
        TrackableIO io = new TrackableIO();
        io.parseFile(context);
    }

    public void setCurrentTrackable(int currentTrackableID){
        TrackingInfoProcessing.extractCurrentTrackableData(currentTrackableID);
    }


    public void getTrackingData(Context context){
        TrackingInfoProcessing.getData(context);
    }

    //Call update tracking from DataBaseHelper
    public void updateTracking(Context context, String trackingID, int pickedEvent, String title, int currentTrackableID, String stationaryStartTime, String meetTime, String stationaryEndTime, String meetLocation){
        Tracking updatedTracking = new Tracking(trackingID,title, currentTrackableID, stationaryStartTime ,meetTime, stationaryEndTime, meetLocation);

        //Update Tracking ArrayList
        TrackingList.getInstance().getTrackingList().set(pickedEvent, updatedTracking);

        if (DatabaseHelper.getInstance(context).updateTrackingData(trackingID, title, stationaryStartTime, meetTime, stationaryEndTime, meetLocation, currentTrackableID)){
            System.out.println("Update Tracking Succeed");

        }else{
            System.out.println("Update Tracking Failed");
        }



    }

    public void addTracking(Context context, String title, int currentTrackableID, String stationaryStartTime , String meetTime, String stationaryEndTime, String meetLocation){
        TrackingList.getInstance().addTracking(context, title, currentTrackableID, stationaryStartTime ,meetTime, stationaryEndTime, meetLocation);
    }

    //Call delete tracking from DataBaseHelper and reload the Tracking ArrayList
    public void deleteTracking(Context context, String ID){
        if (DatabaseHelper.getInstance(context).deleteTrackingData(ID)){
            App.DatabaseAsyncTask task = new App.DatabaseAsyncTask();
            task.execute(context);
            System.out.println("Delete Tracking Succeed");

        }else{
            System.out.println("Delete Tracking Failed");
        }

    }


}
