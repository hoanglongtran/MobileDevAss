package com.example.nex_.mobiledev_assignment1.model;

import com.example.nex_.mobiledev_assignment1.controller.Controller;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;

import java.util.ArrayList;

public class SuggestionManager {
    private ArrayList<Integer> trackableIDList = TrackableList.getInstance().getTrackableIDList();
    private ArrayList<Suggestion> suggestionsList = new ArrayList<>();


    public void something(){
        String currentTrackableLocation;
        String distanceText = "";
        int distanceValue = 0;

        String travelTimeText = "";
        int travelTimeValue = 0;
        for (int i = 0; i < trackableIDList.size(); i++){
            TrackingInfoProcessing.extractCurrentTrackableData(trackableIDList.get(i));
            currentTrackableLocation = TrackingInfoProcessing.getCurrentLocation();

            if (currentTrackableLocation.equals("0, 0")){

            }else {
                JSONGetterAsyncTask task = new JSONGetterAsyncTask();
                task.execute(currentTrackableLocation);
                distanceText = JSONGetterAsyncTask.getDistanceText();
                distanceValue = JSONGetterAsyncTask.getDistanceValue();
                travelTimeText = JSONGetterAsyncTask.getDurationText();
                travelTimeValue = JSONGetterAsyncTask.getDurationValue();

                Suggestion suggestion = new Suggestion(trackableIDList.get(i),distanceText, distanceValue, travelTimeText, travelTimeValue);
                suggestionsList.add(suggestion);
            }


        }
    }
}
