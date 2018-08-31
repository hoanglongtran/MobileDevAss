package com.example.nex_.mobiledev_assignment1.controller;



import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.TestTrackingService;
import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;
import com.example.nex_.mobiledev_assignment1.view.AddTrackingActivity;
import com.example.nex_.mobiledev_assignment1.view.TrackableDetailActivity;
import com.example.nex_.mobiledev_assignment1.view.TrackableListActivity;
import com.example.nex_.mobiledev_assignment1.view.TrackingListActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Listeners implements View.OnClickListener  {
    private static final Listeners ourInstance = new Listeners();

    public static Listeners getInstance() {
        return ourInstance;
    }
    private Listeners() {
    }
    @Override
    public void onClick(View v) {
        // Code here executes on main thread after user presses button
        switch (v.getId()) {

            case R.id.buttonTest:
                // do your code

                Intent trackableList = new Intent(v.getContext(), TrackableListActivity.class);
                v.getContext().startActivity(trackableList);
                //TestTrackingService.test(v.getContext());
                break;
            case R.id.buttonTest2:
                Intent trackableDetail = new Intent(v.getContext(), TrackingListActivity.class);
                v.getContext().startActivity(trackableDetail);
                break;

            case R.id.add_event_fab:
                Intent addTracking = new Intent(v.getContext(), AddTrackingActivity.class);
                //addTracking.putExtra("tracking_start_time", TrackingList.getInstance().getTrackingList().get(TrackingInfoProcessing.getCurrentTrackableID().get());
                //addTracking.putExtra("tracking_end_time", TrackingList.getInstance().getTrackingList().get(TrackingInfoProcessing.getCurrentTrackableID().getTackableDes());
                ///addTracking.putExtra("tracking_current_location", TrackingList.getInstance().getTrackingList().get(TrackingInfoProcessing.getCurrentTrackableID().getURL());
                //addTracking.putExtra("tracking_meet_location", TrackingList.getInstance().getTrackingList().get(TrackingInfoProcessing.getCurrentTrackableID().getCategory());
               v.getContext().startActivity(addTracking);
                break;
                //TODO:
                //get tracking info from selected trackable;

            default:
                break;
        }

        //Trackable trackable1 = new Trackable(v.getContext());

    }

}
