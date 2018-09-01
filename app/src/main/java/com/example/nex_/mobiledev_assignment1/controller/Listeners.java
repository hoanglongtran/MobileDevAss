package com.example.nex_.mobiledev_assignment1.controller;



import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.view.StationaryPeriodListActivity;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableDetailActivity;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableListActivity;
import com.example.nex_.mobiledev_assignment1.view.tracking.TrackingListActivity;

public class Listeners extends FragmentActivity implements View.OnClickListener  {
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
                Intent addTracking = new Intent(v.getContext(), StationaryPeriodListActivity.class);
                Controller.getInstance().setCurrentTrackable(TrackableDetailActivity.getCurrentTrackableID());
                TrackingInfoProcessing.getMeetLocation();
               v.getContext().startActivity(addTracking);
                break;
                //TODO:
                //get tracking info from selected trackable;
            /*case R.id.timePicker:
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
               timePicker.show(getSupportFragmentManager(), "time picker");
               break;*/
            default:
                break;
        }

        //Trackable trackable1 = new Trackable(v.getContext());

    }

}
