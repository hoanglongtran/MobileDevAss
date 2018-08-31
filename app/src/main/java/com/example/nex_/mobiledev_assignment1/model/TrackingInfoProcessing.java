package com.example.nex_.mobiledev_assignment1.model;

import android.content.Context;
import android.util.Log;

import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TrackingInfoProcessing {

    private static final String LOG_TAG = TrackingService.class.getName();
    private static int currentTrackableID;
    private static List<TrackingService.TrackingInfo> data;
    private static ArrayList<String> dataString = new ArrayList<>();
    private static ArrayList<String> currentTrackableData;
    private static String currentData;


    public static void getData(Context context)
    {
        TrackingService trackingService = TrackingService.getSingletonInstance(context);
        Log.i(LOG_TAG, "Parsed File Contents:");

        try
        {
            //Get and store all tracking info
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
            String searchDate = "05/07/2018 1:00:00 PM";
            int searchWindow = 60; // +/- 5 mins
            Date date = dateFormat.parse(searchDate);
            data = trackingService
                    .getTrackingInfoForTimeRange(date, searchWindow, 0);
            Log.i(LOG_TAG, String.format("Matched Query: %s, +/-%d mins", searchDate, searchWindow));
            trackingService.log(data);
            //Convert all tracking info to string
            convertDataToString();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

    }

    private static void convertDataToString(){
        for (int i = 0; i < data.size(); i ++){
            dataString.add(data.get(i).toString());
        }
    }


    //Extract the current Trackable info
    public static void extractCurrentTrackableData(int currentTrackableID){
        TrackingInfoProcessing.currentTrackableID = currentTrackableID;
        for (int i = 0; i< dataString.size(); i++){
            if (dataString.get(i).contains("trackableId="+currentTrackableID)){
                currentTrackableData.add(dataString.get(i));
            }
        }
    }

    //Search throught the extracted info and get all the meet location and time
    public static void getMeetLocation(){
        for (int i = 0; i< currentTrackableData.size(); i++) {
            if (Integer.parseInt(currentTrackableData.get(i).replaceAll(".*stopTime=(\\d).*", "")) > 0) {

                TrackableList.getInstance().getTrackablesList().get(currentTrackableID)
                        .getMeetLong().add(Double.parseDouble(currentTrackableData.get(i).replaceAll(".*long=(\\d).*", "")));

                TrackableList.getInstance().getTrackablesList().get(currentTrackableID)
                        .getMeetLat().add(Double.parseDouble(currentTrackableData.get(i).replaceAll(".*lat=(\\d).*", "")));

            }
        }
    }

    //Compare system time with tracking data then extract the location of matching time
    public static void getCurrentLocation(){
        for (int i = 0; i < currentTrackableData.size(); i++){
            String time = getTime();
            if (currentTrackableData.get(i).contains(time)){
                currentData = currentTrackableData.get(i);
            }
            TrackableList.getInstance().getTrackablesList().get(currentTrackableID)
                    .setCurrentLong(Double.parseDouble(currentData.replaceAll(".*long=(\\d).*", "")));
            TrackableList.getInstance().getTrackablesList().get(currentTrackableID)
                    .setCurrentLat(Double.parseDouble(currentData.replaceAll(".*lat=(\\d).*", "")));
        }


    }

    private static String getTime(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        return df.format(c);
    }

}
