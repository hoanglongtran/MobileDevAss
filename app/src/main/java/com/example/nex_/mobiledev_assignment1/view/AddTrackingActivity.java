package com.example.nex_.mobiledev_assignment1.view;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nex_.mobiledev_assignment1.controller.Listeners;
import com.example.nex_.mobiledev_assignment1.R;

import java.util.Calendar;

public class AddTrackingActivity extends ParentActivity implements TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "AddTrackingActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tracking);
        getIncomingIntent();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //Button button = (Button) findViewById(R.id.timePicker);
        //button.setOnClickListener(Listeners.getInstance());

        //Calling this from Listener class will cause java.lang.IllegalStateException
        Button button = (Button) findViewById(R.id.timePicker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    //Setup time picker
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String am_pm = "";

        Calendar datetime = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        datetime.set(Calendar.MINUTE, minute);

        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";

        String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ?"12":datetime.get(Calendar.HOUR)+"";
        strHrsToShow = strHrsToShow+":"+datetime.get(Calendar.MINUTE)+" "+am_pm;
        TextView meetTime = (TextView) findViewById(R.id.addMeetTIme);
        meetTime.setText(strHrsToShow);
    }

    //Setup app bar button
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

    //Handle incoming intent
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        //Check if coming intent has all required info
        if(getIntent().hasExtra("stationary_start") && getIntent().hasExtra("stationary_end")
                && getIntent().hasExtra("stationary_long") && getIntent().hasExtra("stationary_lat") && getIntent().hasExtra("clicked_position")){
            Log.d(TAG, "getIncomingIntent: found intent extra");
            //check if have extra first before calling to prevent crashing when calling getStringExtra
            stationaryStartTime = getIntent().getStringExtra("stationary_start");
            stationaryEndTime = getIntent().getStringExtra("stationary_end");
            String stationaryLong = Double.toString(getIntent().getDoubleExtra("stationary_long", 0));
            String stationaryLat = Double.toString(getIntent().getDoubleExtra("stationary_lat", 0));
            meetLocation = stationaryLat+ ",  " + stationaryLong;
            //Display start time, end time from which stationary time user picked
            setEditTrackingDetail(stationaryStartTime, stationaryEndTime, meetLocation);

        }

    }

    private void setEditTrackingDetail(String stationaryStartTime, String stationaryEndTime, String meetLocation){
        Log.d(TAG, "setTrackingDetail: add event");
        TextView startTime = (TextView) findViewById(R.id.addStartTime);
        startTime.setText(stationaryStartTime);
        TextView endTime = (TextView) findViewById(R.id.addEndTime);
        endTime.setText(stationaryEndTime);
        //TextView currentLocation = (TextView) findViewById(R.id.trackingCurrentLocation);
        //currentLocation.setText(trackingCurrentLocation);
        TextView meetLocationView = (TextView) findViewById(R.id.addMeetLocation);
        meetLocationView.setText(meetLocation);
    }

    public String getMeetLocation() {
        return meetLocation;
    }

    public String getStationaryStartTime() {
        return stationaryStartTime;
    }

    public String getStationaryEndTime() {
        return stationaryEndTime;
    }
}
