package com.example.nex_.mobiledev_assignment1.model.tracking;

import java.util.ArrayList;
import java.util.UUID;

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
    public void addTracking(String title, int trackableID, String startTime, String meetTime,  String endTime, String meetLocation){
        String ID = UUID.randomUUID().toString();
        Tracking tracking = new Tracking(ID, title, trackableID, startTime, endTime, meetTime, meetLocation);
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
