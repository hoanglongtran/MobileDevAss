package com.example.nex_.mobiledev_assignment1.model.trackable;

import java.util.ArrayList;

public class Trackable {

    /*General idea: User make a new tracking event, then pick a Trackable, the tracking class will make a new trackable object read the file
    then return the location to the Trackable object (the trackable should have a setCurrentLocation method) and display the current location
    as well as the next location that it will stop into the activity. Date and time except meet time will be input from the system.
    Trackable will also have to read the food_truck_data file and return a trackable list.*/

    private int trackabelID;
    private String name;
    private String trackableDes;
    private String URL;
    private String category;
    private double currentLong;
    private double currentLat;
    private ArrayList<Double> meetLong;
    private ArrayList<Double> meetLat;
    private String meetTime;


    Trackable(int trackabelID, String name, String trackableDes, String URL, String category) {
        this.trackabelID = trackabelID;
        this.name = name;
        this.trackableDes = trackableDes;
        this.URL = URL;
        this.category = category;
    }

    public int getTrackabelID() {
        return trackabelID;
    }

    public String getName() {
        return name;
    }

    public String getTackableDes() {
        return trackableDes;
    }

    public String getURL() {
        return URL;
    }

    public String getCategory() {
        return category;
    }

    public double getCurrentLong() {
        return currentLong;
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLong(double currentLong) {
        this.currentLong = currentLong;
    }

    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    public ArrayList<Double> getMeetLong() {
        return meetLong;
    }

    public void setMeetLong(ArrayList<Double> meetLong) {
        this.meetLong = meetLong;
    }

    public ArrayList<Double> getMeetLat() {
        return meetLat;
    }

    public void setMeetLat(ArrayList<Double> meetLat) {
        this.meetLat = meetLat;
    }

    public String getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }
}
