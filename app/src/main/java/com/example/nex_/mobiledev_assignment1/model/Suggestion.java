package com.example.nex_.mobiledev_assignment1.model;

public class Suggestion {
    int trackableID = 0;
    private String distanceText = "";
    private int distanceValue = 0;
    private String travelTimeText = "";
    private int travelTimeValue = 0;

    public Suggestion(int trackableID, String distanceText, int distanceValue, String travelTimeText, int travelTimeValue) {
        this.trackableID = trackableID;
        this.distanceText = distanceText;
        this.distanceValue = distanceValue;
        this.travelTimeText = travelTimeText;
        this.travelTimeValue = travelTimeValue;
    }
}
