package com.example.nex_.mobiledev_assignment1.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;
import com.example.nex_.mobiledev_assignment1.view.tracking.TrackingListActivity;

import java.util.ArrayList;

public class StationaryPeriodListActivity extends ParentActivity {
    private static final String TAG = "StationaryPeriodListActivity";

    private static ArrayList<String> mStationaryStart = new ArrayList<>();
    private static ArrayList<String> mStationaryEndTime = new ArrayList<>();
    private static ArrayList<Double> mStationaryLong = new ArrayList<>();
    private static ArrayList<Double> mStationaryLat = new ArrayList<>();
    private static int currentTrackableID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stationary_period);
        Log.d(TAG, "onCreate: started");
        currentTrackableID = TrackingInfoProcessing.getCurrentTrackableID();
        System.out.println("ID" + currentTrackableID);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(myToolbar);
        initName();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_buttons, menu);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_edit).setVisible(false);
        menu.findItem(R.id.action_add_event).setVisible(false);
        menu.findItem(R.id.action_cancel).setVisible(true);
        menu.findItem(R.id.action_search).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cancel:
                mStationaryEndTime.clear();
                mStationaryStart.clear();
                mStationaryLat.clear();
                mStationaryLong.clear();
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

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: clear data");
        mStationaryStart.clear();
        mStationaryEndTime.clear();
        mStationaryLat.clear();
        mStationaryLong.clear();
        super.onBackPressed();
    }

    private void initName(){
        Log.d(TAG, "initName: preparing names");
        /*mStationaryStart = TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryStartTime() ;
        mStationaryEndTime = TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryEndTime();
        mStationaryLat = TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryLat();
        mStationaryLong = TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryLong();*/
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: ");
        //need to be called through controller packagae
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.stationaryPeriodRecycleView);
        StationaryPeriodListRecycleViewAdapter adapter = new StationaryPeriodListRecycleViewAdapter(mStationaryStart, mStationaryEndTime, mStationaryLong, mStationaryLat,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static ArrayList<String> getmStationaryStart() {
        return mStationaryStart;
    }

    public static ArrayList<String> getmStationaryEndTime() {
        return mStationaryEndTime;
    }

    public static ArrayList<Double> getmStationaryLong() {
        return mStationaryLong;
    }

    public static ArrayList<Double> getmStationaryLat() {
        return mStationaryLat;
    }

}
