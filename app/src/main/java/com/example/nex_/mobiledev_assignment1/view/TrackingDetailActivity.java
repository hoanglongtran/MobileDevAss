package com.example.nex_.mobiledev_assignment1.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nex_.mobiledev_assignment1.R;

public class TrackingDetailActivity extends ParentActivity {
    private static final String TAG = "TrackingDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_detail);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getIncomingIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_buttons, menu);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_edit).setVisible(true);
        menu.findItem(R.id.action_add_event).setVisible(false);
        menu.findItem(R.id.action_cancel).setVisible(false);
        menu.findItem(R.id.action_filter).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }


    private void getIncomingIntent(){

        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if(getIntent().hasExtra("tracking_title") && getIntent().hasExtra("tracking_start_time")
                && getIntent().hasExtra("tracking_end_time") && getIntent().hasExtra("tracking_meet_time")){
            Log.d(TAG, "getIncomingIntent: found intent extra");
            //check if have extra first before calling to prevent crashing when calling getStringExtra
            String trackingTitle = getIntent().getStringExtra("tracking_title");
            String trackingStartTime = getIntent().getStringExtra("tracking_start_time");
            String TrackingEndTime = getIntent().getStringExtra("tracking_end_time");
            String trackingMeetTime = getIntent().getStringExtra("tracking_meet_time");
            setTrackingDetail(trackingTitle,trackingStartTime,trackingMeetTime,TrackingEndTime);
        }
    }

    private void setTrackingDetail(String trackingTitle, String trackingStartTime, String TrackingMeetTime, String trackingEndTime){
        Log.d(TAG, "setTrackingDetail: setting to tracking details");
        TextView title = (TextView) findViewById(R.id.trackingTitle);
        title.setText(trackingTitle);
        TextView startTime = (TextView) findViewById(R.id.trackingStartTime);
        startTime.setText(trackingStartTime);
        TextView meetTime = (TextView) findViewById(R.id.trackingMeetTime);
        meetTime.setText(TrackingMeetTime);
        TextView endTime = (TextView) findViewById(R.id.trackingEndTime);
        endTime.setText(trackingEndTime);
    }


}
