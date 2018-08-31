package com.example.nex_.mobiledev_assignment1.model.tracking;

public class Tracking extends AbstrackTracking {
    private int trackingID;
    private int trackableID;
    private String title;
    private String startTime;
    private String endTime;
    private String meetTime;

    Tracking(int trackingID, String title, int trackableID, String startTime, String endTime, String meetTime) {
        this.trackingID = trackingID;
        this.title = title;
        this.trackableID = trackableID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetTime = meetTime;
    }

    public int getTrackingID() {
        return trackingID;
    }

    public String getTitle() {
        return title;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getMeetTime() {
        return meetTime;
    }





}
