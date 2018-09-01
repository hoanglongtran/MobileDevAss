package com.example.nex_.mobiledev_assignment1.view;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nex_.mobiledev_assignment1.controller.Listeners;
import com.example.nex_.mobiledev_assignment1.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Locale;

public class AddTrackingActivity extends ParentActivity implements TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "AddTrackingActivity";
    private static int clickedPosition = 0;
    Calendar startTimeLimit;
    Calendar endTimeLimit;
    Calendar meetTime;


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
        if (isEdit){
            setTitle("Edit");
        }

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
            dlgAlert.setTitle("Error Message...");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            System.out.println("Out");
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
        menu.findItem(R.id.action_filter).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    //Handle incoming intent
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        //Check if coming intent has all required info

        if (getIntent().hasExtra("stationary_start") && getIntent().hasExtra("stationary_end")
                && getIntent().hasExtra("stationary_long") && getIntent().hasExtra("stationary_lat") && getIntent().hasExtra("clicked_position")) {
            Log.d(TAG, "getIncomingIntent: found intent extra");
            //check if have extra first before calling to prevent crashing when calling getStringExtra
            stationaryStartTime = getIntent().getStringExtra("stationary_start");
            stationaryEndTime = getIntent().getStringExtra("stationary_end");
            stationaryLong = Double.toString(getIntent().getDoubleExtra("stationary_long", 0));
            stationaryLat = Double.toString(getIntent().getDoubleExtra("stationary_lat", 0));
            meetLocation = stationaryLat + ",  " + stationaryLong;
            //Save the clicked position so that we know which stationary time user picked
            clickedPosition = getIntent().getIntExtra("clicked_position", 0);
            //Display start time, end time f
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

    public static int getClickedPosition() {
        return clickedPosition;
    }
}
