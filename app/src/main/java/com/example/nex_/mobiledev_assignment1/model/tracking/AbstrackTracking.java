package com.example.nex_.mobiledev_assignment1.model.tracking;

import android.location.Location;

import java.sql.Time;

public abstract class AbstrackTracking implements InterfaceTracking {
    private int ID;
    private int trackableID;
    private String title;
    private Time startTime;
    private Time endTime;
    private Time meetTime;
    private Location currentLocation;
    private Location meetLocation;

    public void setTrackableID(int trackableID) {
        this.trackableID = trackableID;
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
