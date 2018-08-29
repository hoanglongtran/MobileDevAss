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





}
