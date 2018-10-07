package com.example.nex_.mobiledev_assignment1.model.trackable;

import java.util.ArrayList;

public class TrackableList {
    private static final TrackableList ourInstance = new TrackableList();

    public static TrackableList getInstance() {
        return ourInstance;
    }

    //This class holds an array of Trackable objects
    private ArrayList<Trackable> trackablesList;
    private ArrayList<String> trackableNames;
    private ArrayList<String> trackableCategory;
    private ArrayList<Integer> trackableIDList;

    public ArrayList<Trackable> getTrackablesList() {
        return trackablesList;
    }

    private TrackableList(){
        trackablesList = new ArrayList<>();
        trackableNames = new ArrayList<>();
        trackableCategory = new ArrayList<>();
        trackableIDList = new ArrayList<>();
    }

    public void addTrackables(int ID, String name, String trackableDes, String URL, String category){
        Trackable trackable = new Trackable(ID, name, trackableDes, URL, category);
        trackableIDList.add(ID);
        trackablesList.add(trackable);
        trackableNames.add(name);
        trackableCategory.add(category);

    }

    public ArrayList<Integer> getTrackableIDList() {
        return trackableIDList;
    }
    public ArrayList<String> getTrackableNames() {
        return trackableNames;
    }
    public ArrayList<String> getTrackableCategory(){
        return trackableCategory;
    }
}
