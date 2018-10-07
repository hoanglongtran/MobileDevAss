package com.example.nex_.mobiledev_assignment1.model;

public class Suggestion {
    private int trackableID = 0;
    private String distanceText = "";
    public int distanceValue = 0;
    private String travelTimeText = "";
    private int travelTimeValue = 0;

    public Suggestion(int trackableID, String distanceText, int distanceValue, String travelTimeText, int travelTimeValue) {
        this.trackableID = trackableID;
        this.distanceText = distanceText;
        this.distanceValue = distanceValue;
        this.travelTimeText = travelTimeText;
        this.travelTimeValue = travelTimeValue;
    }

    public int getTrackableID() {
        return trackableID;
    }

    public String getDistanceText() {
        return distanceText;
    }

    public int getDistanceValue() {
        return distanceValue;
    }

    public String getTravelTimeText() {
        return travelTimeText;
    }

    public int getTravelTimeValue() {
        return travelTimeValue;
    }
}
