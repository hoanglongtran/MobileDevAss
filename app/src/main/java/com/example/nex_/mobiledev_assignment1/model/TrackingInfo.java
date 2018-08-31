package com.example.nex_.mobiledev_assignment1.model;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TrackingInfo {

    private static final String LOG_TAG = TrackingService.class.getName();
    private Date c;
    private SimpleDateFormat df;
    private int currentTrackableID;
    private String currentDateTimeString;
    private static List<TrackingService.TrackingInfo> data;
    // call this method to run simple hard coded test (note you will need to handle the parsing Exception)

    public static void getData(Context context)
    {
        TrackingService trackingService = TrackingService.getSingletonInstance(context);
        Log.i(LOG_TAG, "Parsed File Contents:");

        try
        {
            // 5 mins either side of 05/07/2018 1:05:00 PM
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
            String searchDate = "05/07/2018 1:00:00 PM";
            int searchWindow = 5; // +/- 5 mins
            Date date = dateFormat.parse(searchDate);
            data = trackingService
                    .getTrackingInfoForTimeRange(date, searchWindow, 0);
            Log.i(LOG_TAG, String.format("Matched Query: %s, +/-%d mins", searchDate, searchWindow));

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
    public static void getCurrentTrackableData
    public void getCurrentLocation(int trackableID){
        c = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
        currentDateTimeString = df.format(c);
    }

}
