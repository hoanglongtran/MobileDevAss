package com.example.nex_.mobiledev_assignment1.model.tracking;

public class Tracking extends AbstrackTracking {
    private String trackingID;
    private int trackableID;
    private String title;
    private String startTime;
    private String endTime;
    private String meetTime;
    private String meetLocation;

    public Tracking(String trackingID, String title, int trackableID, String startTime, String endTime, String meetTime, String meetLocation) {
        this.trackingID = trackingID;
        this.title = title;
        this.trackableID = trackableID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetTime = meetTime;
        this.meetLocation = meetLocation;
    }

    public String getTrackingID() {
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

    public int getTrackableID() {
        return trackableID;
    }

    public String getMeetLocation() {
        return meetLocation;
    }

    @Override
    public void setTrackableID(int trackableID) {
        this.trackableID = trackableID;
    }
}
