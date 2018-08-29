package com.example.nex_.mobiledev_assignment1.model.trackable;

import android.content.Context;

import com.example.nex_.mobiledev_assignment1.model.CategoryList;
import com.example.nex_.mobiledev_assignment1.model.TrackingService;
import com.example.nex_.mobiledev_assignment1.model.trackable.AbstractTrackable;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Trackable {

    /*General idea: User make a new tracking event, then pick a Trackable, the tracking class will make a new trackable object read the file
    then return the location to the Trackable object (the trackable should have a setCurrentLocation method) and display the current location
    as well as the next location that it will stop into the activity. Date and time except meet time will be input from the system.
    Trackable will also have to read the food_truck_data file and return a trackable list.*/

    private int ID;
    private String name;
    private String trackableDes;
    private String URL;
    private String category;
    private double currentLongtitude;
    private double currentLatitude;
    private double nextStationaryLongtitude;
    private double nextStationaryLatitude;

    public Trackable(int ID, String name, String trackableDes, String URL, String category) {
        this.ID = ID;
        this.name = name;
        this.trackableDes = trackableDes;
        this.URL = URL;
        this.category = category;
    }

    public int getID() {
        return ID;
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

    public double getCurrentLongtitude() {
        return currentLongtitude;
    }

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public double getNextStationaryLongtitude() {
        return nextStationaryLongtitude;
    }

    public double getNextStationaryLatitude() {
        return nextStationaryLatitude;
    }
}
