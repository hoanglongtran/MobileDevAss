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
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrackingInfoProcessing {

    private static final String LOG_TAG = TrackingService.class.getName();
    private static int currentTrackableID;
    private static List<TrackingService.TrackingInfo> data;
    private static ArrayList<String> dataString = new ArrayList<>();
    private static ArrayList<String> currentTrackableData = new ArrayList<>();
    private static String currentData;
    private static final Pattern stopTimeRegex = Pattern.compile(".*stopTime=(\\d+).*");
    private static final Pattern longitudeRegex = Pattern.compile(".*long=(-?\\d*\\.?\\d*).*");
    private static final Pattern latitudeRegex = Pattern.compile(".*lat=(-?\\d*\\.\\d*).*");
    private static final Pattern timeRegex = Pattern.compile(".*Date\\/Time=(\\d{1,2}\\/\\d{1,2}\\/\\d{2} \\d{1,2}:\\d{2}:\\d{2} (AM|PM))");



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
            //trackingService.log(data);
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
                System.out.println(dataString.get(i));
            }
        }
    }

    //Search throught the extracted info and get all the meet location and time
    public static void getMeetLocation(){

        Matcher m;
        Matcher v;
        Matcher c;
        Matcher time;
        String startTime;
        int stopDuration = 0;
        double longitude = 0;
        double latitude = 0;
        for (int i = 0; i< currentTrackableData.size(); i++) {
            m = stopTimeRegex.matcher(currentTrackableData.get(i));
            if (m.find()){
                System.out.println(m.group(1));
                stopDuration = Integer.parseInt(m.group(1));
            }
            if (stopDuration > 0) {
                v = longitudeRegex.matcher(currentTrackableData.get(i));
                c = latitudeRegex.matcher(currentTrackableData.get(i));
                time = timeRegex.matcher(currentTrackableData.get(i));
                if (time.find()){
                    startTime = time.group(1);
                    TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryStartTime().add(startTime);
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm:ss aa");
                    try {
                        Date d = df.parse(startTime);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(d);
                        cal.add(Calendar.MINUTE, stopDuration);
                        String endTime = df.format(cal.getTime());
                        TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryEndTime().add(endTime);
                    }catch (ParseException e)
                    {
                        e.printStackTrace();
                    }


                }
                if(v.find()) {
                    longitude = Double.parseDouble(v.group(1));
                    TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryLong().add(longitude);
                }
                if (c.find()) {
                    latitude = Double.parseDouble(c.group(1));
                    TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryLat().add(latitude);
                }
            }
        }
        //Clear current data
        currentTrackableData.clear();
    }

    //Compare system time with tracking data then extract the location of matching time
    public static String getCurrentLocation(){
        String longitude = "";
        String latitude = "";
        String time = getTime();
        for (int i = 0; i < currentTrackableData.size(); i++){

            Pattern currentTimeRegex = Pattern.compile(".*" + time + ".*");
            Matcher match = currentTimeRegex.matcher(currentTrackableData.get(i));
            if (match.find()){
                Matcher currentLong = longitudeRegex.matcher(currentTrackableData.get(i));
                Matcher currentLat = latitudeRegex.matcher(currentTrackableData.get(i));
                if(currentLong.find()) {
                    longitude = currentLong.group(1);
                }
                if (currentLat.find()) {
                    latitude = currentLat.group(1);
                }
            }

        }
        String currentLocation = latitude +", "+longitude;
        currentTrackableData.clear();

        return currentLocation;
    }

    private static String getTime(){
        //This one is to test the get current location method, which will return the location at 1:15
        return "1:20";
        //This one will return the date from the system
        //return new SimpleDateFormat("h:mm").format(new java.util.Date());
    }

    public static int getCurrentTrackableID() {
        return currentTrackableID;
    }
}
