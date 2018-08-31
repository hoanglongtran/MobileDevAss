package com.example.nex_.mobiledev_assignment1.model.tracking;

import java.util.ArrayList;

public class TrackingList {
    private static final TrackingList ourInstance = new TrackingList();

    public static TrackingList getInstance() {
        return ourInstance;
    }

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
    public void addTracking(int ID, String title, int trackableID, String startTime, String meetTime,  String endTime){
        Tracking tracking = new Tracking(ID, title, trackableID, startTime, endTime, meetTime);
        trackingList.add(tracking);
        trackingTitle.add(title);
        trackingMeetTime.add(meetTime);

    }

    public ArrayList<String> getTrackingTitle() {
        return trackingTitle;
    }

    public ArrayList<String> getTrackingMeetTime() {
        return trackingMeetTime;
    }
}
