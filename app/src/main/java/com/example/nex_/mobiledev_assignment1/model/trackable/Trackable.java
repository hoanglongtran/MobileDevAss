package com.example.nex_.mobiledev_assignment1.model.trackable;

import java.util.ArrayList;

public class Trackable {

    private int trackabelID;
    private String name;
    private String trackableDes;
    private String URL;
    private String category;
    private Double stationaryLong = 0.0;
    private Double stationaryLat = 0.0;
    private String stationaryStartTime = "";
    private String stationaryEndTime = "";
    private int chosenStationary;

    //How should I create interface and abstract for a class that only holds variables?

    Trackable(int trackabelID, String name, String trackableDes, String URL, String category) {
        this.trackabelID = trackabelID;
        this.name = name;
        this.trackableDes = trackableDes;
        this.URL = URL;
        this.category = category;
    }

    public int getTrackabelID() {
        return trackabelID;
    }

    public String getName() {
        return name;
    }

    public String getTackableDes() {
        return trackableDes;
    }

    public String getURL() {
        return URL;
    }

    public String getCategory() {
        return category;
    }

    /*public ArrayList<Double> getStationaryLong() {
        return stationaryLong;
    }

    public ArrayList<Double> getStationaryLat() {
        return stationaryLat;
    }

    public ArrayList<String> getStationaryStartTime() {
        return stationaryStartTime;
    }

    public ArrayList<String> getStationaryEndTime() {
        return stationaryEndTime;
    }*/

    public void setStationaryLong(Double stationaryLong) {
        this.stationaryLong = stationaryLong;
    }

    public void setStationaryLat(Double stationaryLat) {
        this.stationaryLat = stationaryLat;
    }

    public void setStationaryStartTime(String stationaryStartTime) {
        this.stationaryStartTime = stationaryStartTime;
    }

    public void setStationaryEndTime(String stationaryEndTime) {
        this.stationaryEndTime = stationaryEndTime;
    }

    public Double getStationaryLong() {
        return stationaryLong;
    }

    public Double getStationaryLat() {
        return stationaryLat;
    }

    public String getStationaryStartTime() {
        return stationaryStartTime;
    }

    public String getStationaryEndTime() {
        return stationaryEndTime;
    }

    /*public void setChosenStationary(int chosenStationary) {
        this.chosenStationary = chosenStationary;
    }*/
}
