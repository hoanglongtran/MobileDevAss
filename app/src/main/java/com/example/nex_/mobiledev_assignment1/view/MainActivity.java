package com.example.nex_.mobiledev_assignment1.view;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;

import com.example.nex_.mobiledev_assignment1.controller.TrackingListButtonListener;
import com.example.nex_.mobiledev_assignment1.controller.TrackableListButtonListener;
import com.example.nex_.mobiledev_assignment1.controller.GetCurrentLocationListener;
import com.example.nex_.mobiledev_assignment1.controller.NotificationReceiver;
import com.example.nex_.mobiledev_assignment1.model.JSONGetterAsyncTask;
import com.example.nex_.mobiledev_assignment1.model.NetworkReceiver;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.controller.Controller;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends ParentActivity  implements OnMapReadyCallback {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    public static final int REQUEST_LOCATION = 0;
    GoogleMap map;

    private Context mContext;


    //private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Read through the file to get tracking data
        Controller.getInstance().getTrackingData(this);


        //Request location permission
        addPermissionHelper(REQUEST_LOCATION,
                findViewById(R.id.drawer_layout),"Location Permission Required", Manifest.permission.ACCESS_FINE_LOCATION);
        testPermissions(REQUEST_LOCATION);



        //Add a floating button to get location
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        isServiceOK();

        final Button button = (Button) findViewById(R.id.trackableListButton);
        button.setOnClickListener(TrackableListButtonListener.getInstance());
        final Button button2 = (Button) findViewById(R.id.trackingListButton);
        button2.setOnClickListener(TrackingListButtonListener.getInstance());




    }


    public boolean isServiceOK(){
        Log.d(TAG, "isServiceOK: checking google service version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS){
            Log.d(TAG, "isServiceOK: Google Play Services is working");
            return true;
        }else  if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServiceOK: Error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "We can't make app request", Toast.LENGTH_SHORT).show();
        }
        return false;
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //Display current location of a tracking with a marker
        GetCurrentLocationListener currentLocationListener = new GetCurrentLocationListener(map);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(currentLocationListener);
    }

    //Setup the notification
    public void sendOnChannel1(View v){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Set the intent that will triggered when user taps the notification


        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0 , broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, actionIntent);
        }


    }



}
