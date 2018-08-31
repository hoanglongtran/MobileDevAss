package com.example.nex_.mobiledev_assignment1.model;

import android.content.Context;
import android.util.Log;

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
    private ArrayList<Double> currentLong;
    private ArrayList<Double> currentLat;
    private String currentData;
    private double meetLong;
    private double meetLat;
    private double meetTime;


    public static void getData(Context context)
    {
        TrackingService trackingService = TrackingService.getSingletonInstance(context);
        Log.i(LOG_TAG, "Parsed File Contents:");

        try
        {
            // 5 mins either side of 05/07/2018 1:05:00 PM
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
            String searchDate = "05/07/2018 1:00:00 PM";
            int searchWindow = 60; // +/- 5 mins
            Date date = dateFormat.parse(searchDate);
            data = trackingService
                    .getTrackingInfoForTimeRange(date, searchWindow, 0);
            Log.i(LOG_TAG, String.format("Matched Query: %s, +/-%d mins", searchDate, searchWindow));
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

    public static int getCurrentTrackableID() {
        return currentTrackableID;
    }

    public static void setCurrentTrackableData(int currentTrackableID){
        TrackingInfoProcessing.currentTrackableID = currentTrackableID;
        for (int i = 0; i< dataString.size(); i++){
            if (dataString.get(i).contains("trackableId="+currentTrackableID)){
                currentTrackableData.add(dataString.get(i));
            }
        }
    }


    public void getLocation(){
        for (int i = 0; i < currentTrackableData.size(); i++){
            String time = getTime();
            if (currentTrackableData.get(i).contains(time)){
                currentData = currentTrackableData.get(i);
            }
        }
        if (Integer.parseInt(currentData.replaceAll(".*stopTime=(\\d).*", "")) > 0 ){
            meetLong = Integer.parseInt(currentData.replaceAll(".*long=(\\d).*", ""));
            meetLat = Integer.parseInt(currentData.replaceAll(".*lat=(\\d).*", ""));

        }else if (Integer.parseInt(currentData.replaceAll(".*stopTime=(\\d).*", "")) == 0 ){
            currentLong.add(Double.parseDouble(currentData.replaceAll(".*long=(\\d).*", "")));
            currentLat.add(Double.parseDouble(currentData.replaceAll(".*lat=(\\d).*", "")));
        }

    }
    private String getTime(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
        return df.format(c);
    }

}
