package com.example.nex_.mobiledev_assignment1.view;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.controller.Controller;
import com.example.nex_.mobiledev_assignment1.controller.Listeners;
import com.example.nex_.mobiledev_assignment1.model.TrackingJobService;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ParentActivity {
    private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Read through the file to get tracking data
        Controller.getInstance().getData(this);

        //Add a floating button to get location
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(Listeners.getInstance());



        //MapView, used for assignment 2
        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.7750, 122.4183))
                        .title("San Francisco")
                        .snippet("Population: 776733"));
            }
        });


        final Button button = (Button) findViewById(R.id.buttonTest);
        button.setOnClickListener(Listeners.getInstance());
        final Button button2 = (Button) findViewById(R.id.buttonTest2);
        button2.setOnClickListener(Listeners.getInstance());
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


    public void scheduleJob(View v){
        ComponentName componentName  = new ComponentName(this, TrackingJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setPeriodic(5000000, JobInfo.getMinFlexMillis()).build();
    }

}
