package com.example.nex_.mobiledev_assignment1.controller;

import android.content.Context;

import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;
import com.example.nex_.mobiledev_assignment1.model.tracking.Tracking;
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;

import java.util.UUID;

public class Controller {
    private static final Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    public void initTrackables() {
        System.out.println("initTrackables");
        TrackableList.getInstance().addTrackables(0, "Trackable A", "This is the first trackable", "https://google.com", "Category A");
        TrackableList.getInstance().addTrackables(1, "Trackable B", "This is the second trackable", "https://youtube.com", "Category A");
        TrackableList.getInstance().addTrackables(2, "Trackable C", "This is the third trackable", "https://reddit.com", "Category B");
        TrackableList.getInstance().addTrackables(3, "Trackable D", "This is the fourth trackable", "https://waitbutwhy.com", "Category B");
        TrackableList.getInstance().addTrackables(4, "Trackable E", "This is the fifth trackable", "https://store.steampowered.com", "Category C");
    }

    public void setCurrentTrackable(int currentTrackableID){
        TrackingInfoProcessing.extractCurrentTrackableData(currentTrackableID);
    }

    public void getData(Context context){
        TrackingInfoProcessing.getData(context);
    }

    public void updateTracking(int pickedEvent, String title, int currentTrackableID, String stationaryStartTime, String meetTime, String stationaryEndTime, String meetLocation){
        Tracking updatedTracking = new Tracking(UUID.randomUUID().toString(),title, currentTrackableID, stationaryStartTime ,meetTime, stationaryEndTime, meetLocation);

        TrackingList.getInstance().getTrackingList().set(pickedEvent, updatedTracking);

    }

}
