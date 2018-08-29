package com.example.nex_.mobiledev_assignment1.model.tracking;

public class TrackingList {
    private static final TrackingList ourInstance = new TrackingList();

    public static TrackingList getInstance() {
        return ourInstance;
    }
}
