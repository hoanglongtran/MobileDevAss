package com.example.nex_.mobiledev_assignment1.controller;



import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.model.TrackingInfoProcessing;
import com.example.nex_.mobiledev_assignment1.view.ParentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GetCurrentLocationListener extends FragmentActivity implements View.OnClickListener  {

    private GoogleMap mMap;

    public GetCurrentLocationListener(GoogleMap googleMap) {
        this.mMap = googleMap;
    }

    @Override
    public void onClick(View v) {
        // Code here executes on main thread after user presses button

        double lattitude =  Double.parseDouble(TrackingInfoProcessing.getCurrentLattitude());
        double longtitude = Double.parseDouble(TrackingInfoProcessing.getCurrentLongtitude());
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(lattitude, longtitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                Controller.getInstance().setCurrentTrackable(ParentActivity.getTrackedTrackableID());
                String currentLocation = TrackingInfoProcessing.getCurrentLocation();
                Snackbar.make(v, currentLocation, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                System.out.println("Current location: " + currentLocation);

    }

}
