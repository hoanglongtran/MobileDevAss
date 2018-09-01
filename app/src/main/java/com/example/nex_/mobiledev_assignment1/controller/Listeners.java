package com.example.nex_.mobiledev_assignment1.controller;



import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;
import com.example.nex_.mobiledev_assignment1.view.AddTrackingActivity;
import com.example.nex_.mobiledev_assignment1.view.MainActivity;
import com.example.nex_.mobiledev_assignment1.view.ParentActivity;
import com.example.nex_.mobiledev_assignment1.view.StationaryPeriodListActivity;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableDetailActivity;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableListActivity;
import com.example.nex_.mobiledev_assignment1.view.tracking.TrackingDetailActivity;
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
            case R.id.deleteEvent:
                int pickedEvent = AddTrackingActivity.getPickedEvent();
                TrackingList.getInstance().getTrackingList().remove(pickedEvent);
                TrackingListActivity.getmTrackingTitle().remove(pickedEvent);
                TrackingListActivity.getmTrackingMeetTime().remove(pickedEvent);
                ParentActivity.setIsEdit(false);
                Intent goBack = new Intent(v.getContext(), TrackingListActivity.class);
                goBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(goBack);
                finish();
                break;
            case R.id.track:
                int trackedTrackableId = TrackingList.getInstance().getTrackingList().get(TrackingDetailActivity.getPickedEvent()).getTrackableID();
                ParentActivity.setTrackedTrackableID(trackedTrackableId);
                Snackbar.make(v, "Tracked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.fab:
                Controller.getInstance().setCurrentTrackable(ParentActivity.getTrackedTrackableID());
                String currentLocation = TrackingInfoProcessing.getCurrentLocation();
                Snackbar.make(v, currentLocation, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                System.out.println("Current location: " + currentLocation);
                Intent backToMain = new Intent(v.getContext(), MainActivity.class);
                backToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(backToMain);
                finish();
                break;
            default:
                break;
        }

        //Trackable trackable1 = new Trackable(v.getContext());

    }

}
