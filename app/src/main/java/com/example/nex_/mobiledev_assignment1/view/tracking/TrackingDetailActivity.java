package com.example.nex_.mobiledev_assignment1.view.tracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.controller.Controller;
import com.example.nex_.mobiledev_assignment1.controller.GetCurrentLocationListener;
import com.example.nex_.mobiledev_assignment1.controller.TrackEventListener;
import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.view.AddTrackingActivity;
import com.example.nex_.mobiledev_assignment1.view.ParentActivity;

public class TrackingDetailActivity extends ParentActivity {
    private static final String TAG = "TrackingDetailActivity";
    private String trackingTitle;
    private String trackingStartTime;
    private String trackingEndTime;
    private String trackingMeetTime;
    private String trackingMeetLocation;
    private static int pickedEvent;
    private static int pickedTrackableID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_detail);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getIncomingIntent();
        Button track = (Button) findViewById(R.id.track);
        track.setOnClickListener(TrackEventListener.getInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_buttons, menu);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_edit).setVisible(true);
        menu.findItem(R.id.action_add_event).setVisible(false);
        menu.findItem(R.id.action_cancel).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                isEdit = true;
                //Push current info to edit activity
                Intent edit = new Intent(this, AddTrackingActivity.class);
                edit.putExtra("title", trackingTitle);
                edit.putExtra("start_time", trackingStartTime);
                edit.putExtra("meet_time", trackingMeetTime);
                edit.putExtra("end_time", trackingEndTime);
                edit.putExtra("meet_location", trackingMeetLocation);
                edit.putExtra("picked_event", pickedEvent);
                startActivity(edit);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getIncomingIntent(){

        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if(getIntent().hasExtra("tracking_title") && getIntent().hasExtra("tracking_start_time")
                && getIntent().hasExtra("tracking_end_time") && getIntent().hasExtra("tracking_meet_time")){
            Log.d(TAG, "getIncomingIntent: found intent extra");
            //check if have extra first before calling to prevent crashing when calling getStringExtra
             trackingTitle = getIntent().getStringExtra("tracking_title");
             trackingStartTime = getIntent().getStringExtra("tracking_start_time");
             trackingEndTime = getIntent().getStringExtra("tracking_end_time");
             trackingMeetTime = getIntent().getStringExtra("tracking_meet_time");
             trackingMeetLocation = getIntent().getStringExtra("tracking_meet_location");
             pickedEvent = getIntent().getIntExtra("picked_event", 0);
             pickedTrackableID = getIntent().getIntExtra("picked_trackable_id",0);
            setTrackingDetail(trackingTitle,trackingStartTime,trackingMeetTime,trackingEndTime,trackingMeetLocation);
        }

    }

    private void setTrackingDetail(String trackingTitle, String trackingStartTime, String TrackingMeetTime, String trackingEndTime, String trackingMeetLocation){
        Log.d(TAG, "setTrackingDetail: setting to tracking details");
        TextView title = (TextView) findViewById(R.id.trackingTitle);
        title.setText(trackingTitle);

        TextView startTime = (TextView) findViewById(R.id.trackingStartTime);
        startTime.setText(trackingStartTime);

        TextView meetTime = (TextView) findViewById(R.id.trackingMeetTime);
        meetTime.setText(TrackingMeetTime);

        TextView endTime = (TextView) findViewById(R.id.trackingEndTime);
        endTime.setText(trackingEndTime);

        TextView currentLocation = (TextView) findViewById(R.id.trackingCurrentLocation);
        Controller.getInstance().setCurrentTrackable(pickedTrackableID);
        currentLocation.setText(TrackingInfoProcessing.getCurrentLocation());

        TextView meetLocation = (TextView) findViewById(R.id.trackingMeetLocation);
        meetLocation.setText(trackingMeetLocation);
    }

    public String getTrackingTitle() {
        return trackingTitle;
    }

    public String getTrackingStartTime() {
        return trackingStartTime;
    }

    public String getTrackingEndTime() {
        return trackingEndTime;
    }

    public String getTrackingMeetTime() {
        return trackingMeetTime;
    }

    public String getTrackingMeetLocation() {
        return trackingMeetLocation;
    }

    public static int getPickedEvent() {
        return pickedEvent;
    }
}
