package com.example.nex_.mobiledev_assignment1.view;

import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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

import static com.example.nex_.mobiledev_assignment1.model.App.CHANNEL_1_ID;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private GoogleMap mMap;
    private NotificationManagerCompat notificationManager;
    private Context mContext;

    //private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //Read through the file to get tracking data
        Controller.getInstance().getData(this);

        //Add a floating button to get location
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(GetCurrentLocationListener.getInstance());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //MapView, used for assignment 2
        //mapView = (MapView)findViewById(R.id.map);
        //mapView.onCreate(savedInstanceState);
        isServiceOK();
            /*mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(37.7750, 122.4183))
                            .title("San Francisco")
                            .snippet("Population: 776733"));
                }
            });*/


        final Button button = (Button) findViewById(R.id.trackableListButton);
        button.setOnClickListener(TrackableListButtonListener.getInstance());
        final Button button2 = (Button) findViewById(R.id.trackingListButton);
        button2.setOnClickListener(TrackingListButtonListener.getInstance());

        notificationManager = NotificationManagerCompat.from(this);

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
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    //Setup the notification
    public void sendOnChannel1(View v){
        //Set the intent that will triggered when user taps the notification
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", "LeeeeeeeeRoooooy JeeeeeKiiiin");
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0 , broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent broadcastIntent2 = new Intent(this, NotificationReceiver.class);
        broadcastIntent2.putExtra("toastMessage", "aasdfsjaxc");
        PendingIntent actionIntent2 = PendingIntent.getBroadcast(this,
                0 , broadcastIntent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_search)
                .setContentTitle("Helllllloooooooo")
                .setContentText("adlfjaslfasdf")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .addAction(R.mipmap.ic_launcher, "Toast 2", actionIntent2)
                .build();
        notificationManager.notify(1,notification);
    }

}
