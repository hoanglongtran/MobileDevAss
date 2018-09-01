package com.example.nex_.mobiledev_assignment1.model.tracking;

public interface InterfaceTracking {
    String trackingID = "";
    int trackableID = 0;
    String title = "";
    String startTime = "";
    String endTime = "";
    String meetTime = "";
    String meetLocation = "";


    String getTrackingID();

    String getTitle();

    String getStartTime();

    String getEndTime();

    String getMeetTime();

    int getTrackableID();

    String getMeetLocation();

    void setTrackableID(int trackableID);
}
