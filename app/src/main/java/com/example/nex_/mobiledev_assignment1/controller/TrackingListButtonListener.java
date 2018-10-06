package com.example.nex_.mobiledev_assignment1.controller;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;
import com.example.nex_.mobiledev_assignment1.view.tracking.TrackingListActivity;

public class TrackingListButtonListener implements View.OnClickListener {
    private static final TrackingListButtonListener ourInstance = new TrackingListButtonListener();

    public static TrackingListButtonListener getInstance() {
        return ourInstance;
    }
    private TrackingListButtonListener() {
    }
    @Override
    public void onClick(View v) {


        Intent trackableDetail = new Intent(v.getContext(), TrackingListActivity.class);
        v.getContext().startActivity(trackableDetail);

    }


}
