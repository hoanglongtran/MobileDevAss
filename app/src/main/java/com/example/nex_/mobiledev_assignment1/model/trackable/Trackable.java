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

public class Trackable extends AbstractTrackable {
    /*
    General idea: User make a new tracking event, then pick a Trackable, the tracking class will make a new trackable object read the file
    then return the location to the Trackable object (the trackable should have a setCurrentLocation method) and display the current location
    as well as the next location that it will stop into the activity. Date and time except meet time will be input from the system.
    Trackable will also have to read the food_truck_data file and return a trackable list.
     */
    int ID;
    String name;
    String TackableDes;
    java.net.URL URL;
    CategoryList category;
    private DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
    private String searchDate;
    private int searchWindow = 5;
    private int currentLongtitude;
    private int currentLattitude;


    public Trackable(Context context) {
        getData(context);
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public void setSearchWindow(int searchWindow) {
        this.searchWindow = searchWindow;
    }
    @Override
    public double getCurentLongtidue() {
        return currentLongtitude;
    }

    @Override
    public double getCurrentLattitude() {
        return currentLattitude;
    }

    public void getData(Context context)
    {
        TrackingService trackingService = TrackingService.getSingletonInstance(context);
        try
        {
            // 5 mins either side of 05/07/2018 1:05:00 PM
            // PRE: make sure device locale matches provided DateFormat (you can change either device settings or String param)
            Date date = dateFormat.parse(searchDate);
            List<TrackingService.TrackingInfo> matched = trackingService
                    .getTrackingInfoForTimeRange(date, searchWindow, 0);
            trackingService.log(matched);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}
