package com.example.nex_.mobiledev_assignment1.controller;

import android.content.Intent;
import android.view.View;

import android.support.v4.app.FragmentActivity;

import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;
import com.example.nex_.mobiledev_assignment1.view.AddTrackingActivity;
import com.example.nex_.mobiledev_assignment1.view.ParentActivity;
import com.example.nex_.mobiledev_assignment1.view.tracking.TrackingListActivity;

public class DeleteEventListener extends FragmentActivity implements View.OnClickListener {
    private static final DeleteEventListener ourInstance = new DeleteEventListener();

    public static DeleteEventListener getInstance() {
        return ourInstance;
    }
    private DeleteEventListener() {
    }
    @Override
    public void onClick(View v) {
        // Code here executes on main thread after user presses button
        int pickedEvent = AddTrackingActivity.getPickedEvent();
        TrackingList.getInstance().deleteTracking(pickedEvent);
        TrackingListActivity.getmTrackingTitle().remove(pickedEvent);
        TrackingListActivity.getmTrackingMeetTime().remove(pickedEvent);
        ParentActivity.setIsEdit(false);
        Intent goBack = new Intent(v.getContext(), TrackingListActivity.class);
        goBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        v.getContext().startActivity(goBack);
        finish();


    }
}
