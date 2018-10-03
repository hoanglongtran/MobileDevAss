package com.example.nex_.mobiledev_assignment1.controller;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;
import com.example.nex_.mobiledev_assignment1.view.ParentActivity;
import com.example.nex_.mobiledev_assignment1.view.tracking.TrackingDetailActivity;

public class TrackEventListener implements View.OnClickListener {
    private static final TrackEventListener ourInstance = new TrackEventListener();

    public static TrackEventListener getInstance() {
        return ourInstance;
    }
    private TrackEventListener() {
    }
    @Override
    public void onClick(View v) {
                int trackedTrackableId = TrackingList.getInstance().getTrackingList().get(TrackingDetailActivity.getPickedEvent()).getTrackableID();
                ParentActivity.setTrackedTrackableID(trackedTrackableId);
                Snackbar.make(v, "Tracked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


    }
}
