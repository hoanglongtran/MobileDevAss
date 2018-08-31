package com.example.nex_.mobiledev_assignment1.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.nex_.mobiledev_assignment1.controller.Listeners;
import com.example.nex_.mobiledev_assignment1.R;

public class AddTrackingActivity extends ParentActivity {
    private static final String TAG = "AddTrackingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tracking);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_buttons, menu);
        menu.findItem(R.id.action_save).setVisible(true);
        menu.findItem(R.id.action_edit).setVisible(false);
        menu.findItem(R.id.action_add_event).setVisible(false);
        menu.findItem(R.id.action_cancel).setVisible(true);
        menu.findItem(R.id.action_filter).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    private void setEditTrackingDetail(String trackingStartTime, String trackingEndTime, String trackingCurrentLocation, String trackingMeetLocation){
        Log.d(TAG, "setTrackingDetail: add event");

        TextView startTime = (TextView) findViewById(R.id.trackingStartTime);
        startTime.setText(trackingStartTime);
        TextView endTime = (TextView) findViewById(R.id.trackingEndTime);
        endTime.setText(trackingEndTime);
        TextView currentLocation = (TextView) findViewById(R.id.trackingCurrentLocation);
        currentLocation.setText(trackingCurrentLocation);
        TextView meetLocation = (TextView) findViewById(R.id.trackingMeetLocation);
        meetLocation.setText(trackingMeetLocation);
    }

}
