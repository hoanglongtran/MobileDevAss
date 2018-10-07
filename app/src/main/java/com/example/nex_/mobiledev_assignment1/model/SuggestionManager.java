package com.example.nex_.mobiledev_assignment1.model;

import android.util.Log;

import com.example.nex_.mobiledev_assignment1.controller.Controller;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class SuggestionManager {
    private static ArrayList<Integer> trackableIDList = TrackableList.getInstance().getTrackableIDList();
    public static ArrayList<Suggestion> suggestionsList = new ArrayList<>();
    String currentTrackableLocation;
    public static String distanceText = "";
    public static int distanceValue = 0;

    public static String travelTimeText = "";
    public static int travelTimeValue = 0;
    public static int currentTrackableID;
    JSONGetterAsyncTask task = new JSONGetterAsyncTask();

    public void getDistanceInfo(){

        //Loop through the trackable list to get each trackable's locations so that we can compare and sort them based on distance
        for (int i = 0; i < trackableIDList.size(); i++){
            TrackingInfoProcessing.extractCurrentTrackableData(trackableIDList.get(i));
            currentTrackableID = trackableIDList.get(i);
            currentTrackableLocation = TrackingInfoProcessing.getCurrentLocation();

            //If there are no info of the trackable in that time, then don't update the suggestion list
            if (!currentTrackableLocation.equals("0, 0")){
                //Call the JSON

                task.execute(currentTrackableLocation);


                //Get info out from the JSON
                /*distanceText = JSONGetterAsyncTask.distanceText;
                distanceValue = JSONGetterAsyncTask.distanceValue;
                travelTimeText = JSONGetterAsyncTask.durationText;
                travelTimeValue = JSONGetterAsyncTask.durationValue;*/
                /*Log.d("Suggestion", "trackableID: " + trackableIDList.get(i));
                Log.d("Suggestion", "distanceText: " + distanceText);
                Log.d("Suggestion", "distanceValue: " + distanceValue);
                Log.d("Suggestion", "travelTimeText: " + travelTimeText);
                Log.d("Suggestion", "travelTimeValue: " + travelTimeValue);*/
                /*Suggestion suggestion = new Suggestion(trackableIDList.get(i),distanceText, distanceValue, travelTimeText, travelTimeValue);
                suggestionsList.add(suggestion);*/
                //Reset the current location
                TrackingInfoProcessing.setCurrentLattitude("0");
                TrackingInfoProcessing.setCurrentLongtitude("0");
            }


        }
        suggestionSort();
    }

    private void suggestionSort(){
        Collections.sort(suggestionsList, new Comparator<Suggestion>() {
            @Override
            public int compare(Suggestion o1, Suggestion o2) {
                return o1.distanceValue - o2.distanceValue; //Ascending
            }
        });
    }

    public void setDistanceText(String distanceText) {
        this.distanceText = distanceText;
    }

    public void setDistanceValue(int distanceValue) {
        this.distanceValue = distanceValue;
    }

    public void setTravelTimeText(String travelTimeText) {
        this.travelTimeText = travelTimeText;
    }

    public void setTravelTimeValue(int travelTimeValue) {
        this.travelTimeValue = travelTimeValue;
    }
}
