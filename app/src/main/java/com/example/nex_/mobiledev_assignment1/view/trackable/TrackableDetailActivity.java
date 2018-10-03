package com.example.nex_.mobiledev_assignment1.view.trackable;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.controller.AddEventListener;
import com.example.nex_.mobiledev_assignment1.controller.GetCurrentLocationListener;
import com.example.nex_.mobiledev_assignment1.view.ParentActivity;

public class TrackableDetailActivity extends ParentActivity {
    private static final String TAG = "TrackableDetailActivity";
    private static String whoCalling;
    private static int currentTrackableID;
    private static int currentTrackableIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackable_detail);
        Log.d(TAG, "onCreate: started");
        getIncomingIntent();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton addEventFab = (FloatingActionButton) findViewById(R.id.add_event_fab);
        addEventFab.setOnClickListener(AddEventListener.getInstance());
        setSupportActionBar(myToolbar);

        Log.d(TAG, "onCreate: Current trackable ID " + currentTrackableID);

        //if (whoCalling != null && whoCalling.equals("ParentActivity")){
            addEventFab.setVisibility(View.VISIBLE);


       // }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_buttons, menu);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_edit).setVisible(false);
        menu.findItem(R.id.action_add_event).setVisible(false);
        menu.findItem(R.id.action_cancel).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }


    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if(getIntent().hasExtra("trackable_name") && getIntent().hasExtra("trackable_des")
                && getIntent().hasExtra("trackable_url") && getIntent().hasExtra("trackable_category")){
            Log.d(TAG, "getIncomingIntent: found intent extra");
            //check if have extra first before calling to prevent crashing when calling getStringExtra
            String trackableName = getIntent().getStringExtra("trackable_name");
            String trackableDes = getIntent().getStringExtra("trackable_des");
            String trackableURL = getIntent().getStringExtra("trackable_url");
            String trackableCategory = getIntent().getStringExtra("trackable_category");
            //currentTrackableID = Integer.parseInt(getIntent().getStringExtra("trackable_id"));
            whoCalling = getIntent().getStringExtra("whoCalled");
            setTrackableDetail(trackableName,trackableDes,trackableURL,trackableCategory);
        }
        if (getIntent().hasExtra("trackable_id")){
            currentTrackableIndex = getIntent().getIntExtra("current_trackable_index",0);
            currentTrackableID = getIntent().getIntExtra("trackable_id",0);
            System.out.println("Test " + getIntent().getIntExtra("trackable_id",0));
        }

    }

    public static int getCurrentTrackableID() {
        return currentTrackableID;
    }
    public static int getCurrentTrackableIndex(){
        return currentTrackableIndex;
    }

    private void setTrackableDetail(String trackableName, String trackableDes, String trackableURL, String trackableCategory){
        Log.d(TAG, "setTrackableDetail: setting to trackable details");
        TextView name = (TextView) findViewById(R.id.trackableName);
        name.setText(trackableName);
        TextView description = (TextView) findViewById(R.id.trackableDescription);
        description.setText(trackableDes);
        TextView URL = (TextView) findViewById(R.id.trackableURL);
        URL.setText(trackableURL);
        TextView category = (TextView) findViewById(R.id.trackableCategory);
        category.setText(trackableCategory);
    }
}
