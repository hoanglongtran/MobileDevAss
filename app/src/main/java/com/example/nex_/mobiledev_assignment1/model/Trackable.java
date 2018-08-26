package com.example.nex_.mobiledev_assignment1.model;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Trackable extends AbstractTrackable {
    // call this method to run simple hard coded test (note you will need to handle the parsing Exception)
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
    String searchDate = "05/07/2018 1:05:00 PM";
    int searchWindow = 5; // +/- 5 mins


    public Trackable() {
        getData();
    }

    private void getData(Context context)
    {
        TrackingService trackingService = TrackingService.getSingletonInstance(context);
        try
        {
            // 5 mins either side of 05/07/2018 1:05:00 PM
            // PRE: make sure tracking_data.txt contains valid matches
            // PRE: make sure device locale matches provided DateFormat (you can change either device settings or String param)
            Date date = dateFormat.parse(searchDate);
            List<TrackingService.TrackingInfo> matched = trackingService
                    .getTrackingInfoForTimeRange(date, searchWindow, 0);
            Log.i(LOG_TAG, String.format("Matched Query: %s, +/-%d mins", searchDate, searchWindow));
            trackingService.log(matched);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}
