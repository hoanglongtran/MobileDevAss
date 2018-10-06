package com.example.nex_.mobiledev_assignment1.model;

import android.content.Context;
import android.util.Log;

import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableDetailActivity;

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

import static android.support.constraint.Constraints.TAG;

public class TrackingInfoProcessing {

    private static final String LOG_TAG = TrackingService.class.getName();
    private static int currentTrackableID;
    private static int currentTrackableIndex = TrackableDetailActivity.getCurrentTrackableIndex();
    private static List<TrackingService.TrackingInfo> data;
    private static ArrayList<String> dataString = new ArrayList<>();
    private static ArrayList<String> currentTrackableData = new ArrayList<>();
    private static String currentData;
    private static final Pattern stopTimeRegex = Pattern.compile(".*stopTime=(\\d+).*");
    private static final Pattern longitudeRegex = Pattern.compile(".*long=(-?\\d*\\.?\\d*).*");
    private static final Pattern latitudeRegex = Pattern.compile(".*lat=(-?\\d*\\.\\d*).*");
    private static final Pattern timeRegex = Pattern.compile(".*Date\\/Time=(\\d{1,2}\\/\\d{1,2}\\/\\d{2} \\d{1,2}:\\d{2}:\\d{2} (AM|PM))");
    private static String currentLongtitude = "";
    private static String currentLattitude = "";



    public static void getData(Context context)
    {
        TrackingService trackingService = TrackingService.getSingletonInstance(context);
        Log.i(LOG_TAG, "Parsed File Contents:");

        try
        {
            //Get and store all tracking info
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
            String searchDate = "10/10/2018 1:00:00 PM";
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

    //Search through the extracted info and get all the meet location and time
    public static void getMeetLocation(){

        Matcher stopTimeMatcher;
        Matcher v;
        Matcher c;
        Matcher time;
        String currentTime = getTime();
        String startTime;
        int stopDuration = 0;
        double longitude = 0;
        double latitude = 0;

        //Go through the current tracking data
        for (int i = 0; i< currentTrackableData.size(); i++) {

            stopTimeMatcher = stopTimeRegex.matcher(currentTrackableData.get(i));
            Pattern currentTimeRegex = Pattern.compile(".*" + currentTime + ".*");
            Matcher match = currentTimeRegex.matcher(currentTrackableData.get(i));

            //Search for current time
            if (match.find()){
                v = longitudeRegex.matcher(currentTrackableData.get(i));
                c = latitudeRegex.matcher(currentTrackableData.get(i));
                time = timeRegex.matcher(currentTrackableData.get(i));
                // the current time
                if (time.find()){
                    if (stopTimeMatcher.find()){
                        System.out.println(stopTimeMatcher.group(1));
                        stopDuration = Integer.parseInt(stopTimeMatcher.group(1));
                    }
                    if (stopDuration > 0) {
                        startTime = time.group(1);
                        Log.d(TAG, "getMeetLocation: index:" + currentTrackableIndex);
                        Log.d(TAG, "getMeetLocation: current ID:" + currentTrackableID);
                        //startTime = match.group(1);

                        TrackableList.getInstance().getTrackablesList().get(currentTrackableIndex).setStationaryStartTime(startTime);
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm:ss aa");
                        try {
                            Date d = df.parse(startTime);
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(d);
                            cal.add(Calendar.MINUTE, stopDuration);
                            String endTime = df.format(cal.getTime());
                            TrackableList.getInstance().getTrackablesList().get(currentTrackableIndex).setStationaryEndTime(endTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        if (v.find()) {
                            longitude = Double.parseDouble(v.group(1));
                            TrackableList.getInstance().getTrackablesList().get(currentTrackableIndex).setStationaryLong(longitude);
                        }
                        if (c.find()) {
                            latitude = Double.parseDouble(c.group(1));
                            TrackableList.getInstance().getTrackablesList().get(currentTrackableIndex).setStationaryLat(latitude);
                        }
                    }

                }
            }


        }
        //Clear current data
        currentTrackableData.clear();
    }

    //Compare system time with tracking data then extract the location of matching time
    public static String getCurrentLocation(){

        String time = getTime();
        for (int i = 0; i < currentTrackableData.size(); i++){

            Pattern currentTimeRegex = Pattern.compile(".*" + time + ".*");
            Matcher match = currentTimeRegex.matcher(currentTrackableData.get(i));
            if (match.find()){
                Matcher currentLong = longitudeRegex.matcher(currentTrackableData.get(i));
                Matcher currentLat = latitudeRegex.matcher(currentTrackableData.get(i));
                if(currentLong.find()) {
                    currentLongtitude = currentLong.group(1);
                }
                if (currentLat.find()) {
                    currentLattitude = currentLat.group(1);
                }
            }

        }
        String currentLocation = currentLattitude +", "+currentLongtitude;
        currentTrackableData.clear();

        return currentLocation;
    }

    private static String getTime(){
        Date currentTime;
        Calendar calendar = Calendar.getInstance();


        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 5;
        calendar.add(Calendar.MINUTE, -mod);


        String time = new SimpleDateFormat("h:m").format(calendar.getTime());
        return time ;
        //This one is to test the get current location method, which will return the location at 1:15
        //return "1:20";
        //This one will return the date from the system
        //return new SimpleDateFormat("h:mm").format(new java.util.Date());
    }

    public static int getCurrentTrackableID() {
        return currentTrackableID;
    }

    public static String getCurrentLongtitude() {
        return currentLongtitude;
    }

    public static String getCurrentLattitude() {
        return currentLattitude;
    }
}
