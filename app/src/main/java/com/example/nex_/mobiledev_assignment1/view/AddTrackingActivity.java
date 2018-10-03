package com.example.nex_.mobiledev_assignment1.view;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nex_.mobiledev_assignment1.controller.Controller;
import com.example.nex_.mobiledev_assignment1.controller.DeleteEventListener;
import com.example.nex_.mobiledev_assignment1.controller.GetCurrentLocationListener;
import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableDetailActivity;
import com.example.nex_.mobiledev_assignment1.view.tracking.TrackingListActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Locale;

public class AddTrackingActivity extends ParentActivity implements TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "AddTrackingActivity";
    private static int pickedEvent = 0;
    private String title;
    private String stationaryStartTime;
    private String currentMeetTime;
    private String stationaryEndTime;
    private String stationaryLong;
    private String stationaryLat;
    private String meetLocation;
    private Calendar startTimeLimit;
    private Calendar endTimeLimit;
    private Calendar meetTime;
    private static int currentTrackableID = TrackableDetailActivity.getCurrentTrackableID();
    private static int currentTrackableIndex = TrackableDetailActivity.getCurrentTrackableIndex();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tracking);
        if (isEdit){
            setTitle("Edit");
        }
        getIncomingIntent();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        currentTrackableID = TrackingInfoProcessing.getCurrentTrackableID();
        //Button button = (Button) findViewById(R.id.timePicker);
        //button.setOnClickListener(GetCurrentLocationListener.getInstance());


        Button delete = (Button) findViewById(R.id.deleteEvent);
        if (isEdit){
            delete.setVisibility(View.VISIBLE);
            delete.setOnClickListener(DeleteEventListener.getInstance());
        }

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
        int hour = hourOfDay;
        int min = minute;
        meetTime = Calendar.getInstance();
        meetTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        meetTime.set(Calendar.MINUTE, minute);
        String mStringGetTime = String.valueOf(DateFormat.format("h:mm aa",meetTime));

        TextView meetTime = (TextView) findViewById(R.id.addMeetTIme);
        meetTime.setText(mStringGetTime);

        startTimeLimit = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yy h:mm:ss aa", Locale.ENGLISH);
        try {
            startTimeLimit.setTime(sdf.parse(stationaryStartTime));
        }catch (ParseException e) {
            e.printStackTrace();
        }



        endTimeLimit = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
        try {
            endTimeLimit.setTime(sdf.parse(stationaryEndTime));
        }catch (ParseException e) {
            e.printStackTrace();
        }

        int before = startTimeLimit.get(Calendar.HOUR_OF_DAY)*100 + startTimeLimit.get(Calendar.MINUTE);
        int after = endTimeLimit.get(Calendar.HOUR_OF_DAY)*100 + endTimeLimit.get(Calendar.MINUTE);
        int i = hourOfDay*100+ minute;
        //Check if in range
        if (before <= i && i <= after){

            System.out.println("In Range");
        }else {
            //If out of range, display error message and clear the textView
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            dlgAlert.setMessage("Meet time must be between Start time and End time");
            dlgAlert.setTitle("Meet time out of range");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            meetTime.setText("");
        }

    }


    //Setup app bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_buttons, menu);
        menu.findItem(R.id.action_save).setVisible(true);
        menu.findItem(R.id.action_edit).setVisible(false);
        menu.findItem(R.id.action_add_event).setVisible(false);
        menu.findItem(R.id.action_cancel).setVisible(true);
        menu.findItem(R.id.action_search).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if (isEdit){
                    EditText addTitle = (EditText) findViewById(R.id.addTitle);
                    TextView addMeetTIme = (TextView) findViewById(R.id.addMeetTIme);
                    String title = addTitle.getText().toString();
                    int currentTrackableID = TrackableDetailActivity.getCurrentTrackableID();
                    String meetTime = addMeetTIme.getText().toString();
                    //Update the tracking list
                    Controller.getInstance().updateTracking(pickedEvent, title, currentTrackableID, stationaryStartTime,meetTime,stationaryEndTime,meetLocation);

                    TrackingListActivity.getmTrackingTitle().set(pickedEvent, title);
                    TrackingListActivity.getmTrackingMeetTime().set(pickedEvent, meetTime);
                    isEdit = false;
                    Intent goBack = new Intent(this, TrackingListActivity.class);
                    goBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(goBack);
                    finish();
                    return true;
                }
                //Save input item and create a tracking even object
                EditText addTitle = (EditText) findViewById(R.id.addTitle);
                TextView addMeetTIme = (TextView) findViewById(R.id.addMeetTIme);
                String title = addTitle.getText().toString();
                int currentTrackableID = TrackableDetailActivity.getCurrentTrackableID();
                String meetTime = addMeetTIme.getText().toString();

                Controller.getInstance().addTracking(this, title, currentTrackableID, stationaryStartTime ,meetTime, stationaryEndTime, meetLocation);

                Intent goBack = new Intent(this, TrackingListActivity.class);
                goBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(goBack);
                finish();
                return true;
            case R.id.action_cancel:
                Intent cancel = new Intent(this, TrackingListActivity.class);
                cancel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(cancel);
                finish();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    //Handle incoming intent
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        //Check if coming intent has all required info

        /*if (getIntent().hasExtra("stationary_start") && getIntent().hasExtra("stationary_end")
                && getIntent().hasExtra("stationary_long") && getIntent().hasExtra("stationary_lat") && getIntent().hasExtra("clicked_position")) {
            Log.d(TAG, "getIncomingIntent: found intent extra");
            //check if have extra first before calling to prevent crashing when calling getStringExtra
            stationaryStartTime = getIntent().getStringExtra("stationary_start");
            stationaryEndTime = getIntent().getStringExtra("stationary_end");
            stationaryLong = Double.toString(getIntent().getDoubleExtra("stationary_long", 0));
            stationaryLat = Double.toString(getIntent().getDoubleExtra("stationary_lat", 0));
            meetLocation = stationaryLat + ",  " + stationaryLong;
            //Save the clicked position so that we know which stationary time user picked
            pickedEvent = getIntent().getIntExtra("clicked_position", 0);
            //Display start time, end time f
            setEditTrackingDetail();

        }else*/ if (isEdit && getIntent().hasExtra("title") && getIntent().hasExtra("start_time") && getIntent().hasExtra("meet_time") && getIntent().hasExtra("end_time")
                && getIntent().hasExtra("meet_location")){
            title = getIntent().getStringExtra("title");
            stationaryStartTime = getIntent().getStringExtra("start_time");
            currentMeetTime = getIntent().getStringExtra("meet_time");
            stationaryEndTime = getIntent().getStringExtra("end_time");
            meetLocation = getIntent().getStringExtra("meet_location");
            pickedEvent = getIntent().getIntExtra("picked_event", 0);
            setEditTrackingDetail();
        }else if (getIntent().hasExtra("current_trackable_id")){
            Log.d(TAG, "getIncomingIntent: get data from trackable");
            stationaryStartTime = TrackableList.getInstance().getTrackablesList().get(currentTrackableIndex).getStationaryStartTime();
            stationaryEndTime = TrackableList.getInstance().getTrackablesList().get(currentTrackableIndex).getStationaryEndTime();
            stationaryLong = Double.toString(TrackableList.getInstance().getTrackablesList().get(currentTrackableIndex).getStationaryLong());
            stationaryLat = Double.toString(TrackableList.getInstance().getTrackablesList().get(currentTrackableIndex).getStationaryLat());
            meetLocation = stationaryLat + ",  " + stationaryLong;
            currentTrackableID = getIntent().getIntExtra("current_trackable_id", 0);
            //Save the clicked position so that we know which stationary time user picked
            //pickedEvent = getIntent().getIntExtra("clicked_position", 0);
            setEditTrackingDetail();
        }


    }

    private void setEditTrackingDetail(){
        Log.d(TAG, "setTrackingDetail: add event");
        Log.d(TAG, "setEditTrackingDetail: current Trackable index:" + currentTrackableIndex);
        Log.d(TAG, "setEditTrackingDetail: current Trackable ID:" + currentTrackableID);
        EditText titleView = (EditText) findViewById(R.id.addTitle);
        titleView.setText(title);
        TextView startTime = (TextView) findViewById(R.id.addStartTime);
        startTime.setText(stationaryStartTime);
        TextView meetTimeView = (TextView) findViewById(R.id.addMeetTIme);
        meetTimeView.setText(currentMeetTime);
        TextView endTime = (TextView) findViewById(R.id.addEndTime);
        endTime.setText(stationaryEndTime);
        TextView currentLocation = (TextView) findViewById(R.id.addCurrentLocation);
        Controller.getInstance().setCurrentTrackable(currentTrackableID);
        currentLocation.setText(TrackingInfoProcessing.getCurrentLocation());
        TextView meetLocationView = (TextView) findViewById(R.id.addMeetLocation);
        meetLocationView.setText(meetLocation);

    }

    public static int getPickedEvent() {
        return pickedEvent;
    }
}
