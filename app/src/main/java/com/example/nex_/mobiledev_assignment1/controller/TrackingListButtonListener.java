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

        DatabaseAsyncTask task = new DatabaseAsyncTask();
        task.execute(v.getContext());
                Intent trackableDetail = new Intent(v.getContext(), TrackingListActivity.class);
                v.getContext().startActivity(trackableDetail);

    }

    public static class DatabaseAsyncTask extends AsyncTask<Context,Void, Void> {
        @Override
        protected Void doInBackground(Context... contexts) {
            TrackingList.getInstance().setTrackingList(TrackingList.getInstance().getTracking(contexts[0]));
            return null;
        }
    }
}
