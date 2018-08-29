package com.example.nex_.mobiledev_assignment1.controller;

import android.util.Log;

import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;

public class TrackablesController {


    public void initTrackables() {
        System.out.println("initTrackables");
        TrackableList.getInstance().addTrackables(0, "Trackable A", "This is the first trackable", "https://google.com", "Category A");
        TrackableList.getInstance().addTrackables(1, "Trackable B", "This is the second trackable", "https://youtube.com", "Category A");
        TrackableList.getInstance().addTrackables(2, "Trackable C", "This is the third trackable", "https://reddit.com", "Category B");
        TrackableList.getInstance().addTrackables(3, "Trackable D", "This is the fourth trackable", "https://waitbutwhy.com", "Category B");
        TrackableList.getInstance().addTrackables(4, "Trackable E", "This is the fifth trackable", "https://store.steampowered.com", "Category C");
    }


}
