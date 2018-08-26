package com.example.nex_.mobiledev_assignment1.controller;



import android.content.Intent;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.TestTrackingService;
import com.example.nex_.mobiledev_assignment1.view.AddTrackingActivity;
import com.example.nex_.mobiledev_assignment1.view.TrackableDetailActivity;

public class Listeners implements View.OnClickListener {
    private static final Listeners ourInstance = new Listeners();

    public static Listeners getInstance() {
        return ourInstance;
    }
    private Listeners() {
    }
    @Override
    public void onClick(View v) {
        // Code here executes on main thread after user presses button
        // TODO: add code here
        switch (v.getId()) {

            case R.id.buttonTest:
                // do your code
                Intent addTracking = new Intent(v.getContext(), AddTrackingActivity.class);
                v.getContext().startActivity(addTracking);
                TestTrackingService.test(v.getContext());
                break;
            case R.id.buttonTest2:
                Intent trackableDetail = new Intent(v.getContext(), TrackableDetailActivity.class);
                v.getContext().startActivity(trackableDetail);


            default:
                break;
        }

        //Trackable trackable1 = new Trackable(v.getContext());

    }
}