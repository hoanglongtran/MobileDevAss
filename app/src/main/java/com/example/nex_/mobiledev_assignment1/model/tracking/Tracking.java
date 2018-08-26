package com.example.nex_.mobiledev_assignment1.model.tracking;

import android.location.Location;

import com.example.nex_.mobiledev_assignment1.model.tracking.AbstrackTracking;

import java.sql.Time;

public class Tracking extends AbstrackTracking {
    private int ID;
    private String title;
    private Time startTime;
    private Time endTime;
    private Time meetTime;
    private Location currentLocation;
    private Location meetLocation;

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Time getMeetTime() {
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

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setMeetTime(Time meetTime) {
        this.meetTime = meetTime;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setMeetLocation(Location meetLocation) {
        this.meetLocation = meetLocation;
    }
}
