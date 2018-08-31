package com.example.nex_.mobiledev_assignment1.model.tracking;

import java.util.ArrayList;

public class TrackingList {
    private static final TrackingList ourInstance = new TrackingList();

    public static TrackingList getInstance() {
        return ourInstance;
    }

    private ArrayList<Tracking> trackingList;
    private ArrayList<String> trackingNames;

    public ArrayList<Tracking> getTrackingList() {
        return trackingList;
    }

    private TrackingList(){
        trackingList = new ArrayList<>();
        trackingNames = new ArrayList<>();

    }
    public void addTracking(int ID, String title, String startTime, int trackableID, String endTime, String meetTime){
        Tracking tracking = new Tracking(ID, title, trackableID, startTime, endTime, meetTime);
        trackingList.add(tracking);

    }
}
