package com.example.nex_.mobiledev_assignment1.controller;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableListActivity;

public class TrackableListButtonListener implements View.OnClickListener{
    private static final TrackableListButtonListener ourInstance = new TrackableListButtonListener();

    public static TrackableListButtonListener getInstance() {
        return ourInstance;
    }
    private TrackableListButtonListener() {
    }
    @Override
    public void onClick(View v) {
        // Code here executes on main thread after user presses button
                // do your code

                Intent trackableList = new Intent(v.getContext(), TrackableListActivity.class);
                v.getContext().startActivity(trackableList);
                //TestTrackingService.test(v.getContext());



    }

    private static class DatabaseAsyncTask extends AsyncTask<Context,Void, Void> {
        @Override
        protected Void doInBackground(Context... contexts) {
            TrackingList.getInstance().setTrackingList(TrackingList.getInstance().getTracking(contexts[0]));
            return null;
        }
    }

}
