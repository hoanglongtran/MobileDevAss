package com.example.nex_.mobiledev_assignment1.model.tracking;

import android.location.Location;

import com.example.nex_.mobiledev_assignment1.model.tracking.AbstrackTracking;

import java.sql.Time;

public class Tracking extends AbstrackTracking {
    private int ID;
    private String title;
    private String startTime;
    private String endTime;
    private String meetTime;
    private Location currentLocation;
    private Location meetLocation;

    public int getID() {
        return ID;
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

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Location getMeetLocation() {
        return meetLocation;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setMeetLocation(Location meetLocation) {
        this.meetLocation = meetLocation;
    }
}
