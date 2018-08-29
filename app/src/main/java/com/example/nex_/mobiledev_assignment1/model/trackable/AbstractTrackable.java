package com.example.nex_.mobiledev_assignment1.model.trackable;

import android.content.Context;

import com.example.nex_.mobiledev_assignment1.model.CategoryList;
import com.example.nex_.mobiledev_assignment1.model.TrackingService;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public abstract class AbstractTrackable implements InterfaceTrackable {
    int ID;
    String name;
    String TackableDes;
    URL URL;
    CategoryList category;
    private DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
    private String searchDate;
    private int searchWindow = 5;
    private int currentLongtitude;
    private int currentLattitude;

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
