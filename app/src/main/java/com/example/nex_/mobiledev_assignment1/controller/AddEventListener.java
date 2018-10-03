package com.example.nex_.mobiledev_assignment1.controller;

import android.content.Intent;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.view.AddTrackingActivity;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableDetailActivity;

public class AddEventListener implements View.OnClickListener {
    private static final AddEventListener ourInstance = new AddEventListener();

    public static AddEventListener getInstance() {
        return ourInstance;
    }
    private AddEventListener() {
    }
    @Override
    public void onClick(View v) {
                Intent addTracking = new Intent(v.getContext(), AddTrackingActivity.class);
                addTracking.putExtra("current_trackable_id", TrackableDetailActivity.getCurrentTrackableID());
                Controller.getInstance().setCurrentTrackable(TrackableDetailActivity.getCurrentTrackableID());
                TrackingInfoProcessing.getMeetLocation();

                v.getContext().startActivity(addTracking);


    }
}
