package com.example.nex_.mobiledev_assignment1.model.tracking;

import android.location.Location;

import java.sql.Time;

public abstract class AbstrackTracking implements InterfaceTracking {
    private int ID;
    private String title;
    private Time startTime;
    private Time endTime;
    private Time meetTime;
    private Location currentLocation;
    private Location meetLocation;
}
