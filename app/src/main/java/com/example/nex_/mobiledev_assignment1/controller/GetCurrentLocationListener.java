package com.example.nex_.mobiledev_assignment1.controller;



import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.view.ParentActivity;

public class GetCurrentLocationListener extends FragmentActivity implements View.OnClickListener  {
    private static final GetCurrentLocationListener ourInstance = new GetCurrentLocationListener();

    public static GetCurrentLocationListener getInstance() {
        return ourInstance;
    }
    private GetCurrentLocationListener() {
    }
    @Override
    public void onClick(View v) {
        // Code here executes on main thread after user presses button
                Controller.getInstance().setCurrentTrackable(ParentActivity.getTrackedTrackableID());
                String currentLocation = TrackingInfoProcessing.getCurrentLocation();
                Snackbar.make(v, currentLocation, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                System.out.println("Current location: " + currentLocation);

    }

}
