package com.example.nex_.mobiledev_assignment1.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableDetailActivity;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableListActivity;
import com.example.nex_.mobiledev_assignment1.view.tracking.TrackingListActivity;

public abstract class ParentActivity extends AppCompatActivity {
    //This abstract class handles all action from every other class on the app bar

    protected String stationaryStartTime;
    protected String stationaryEndTime;
    protected String stationaryLong;
    protected String stationaryLat;
    protected String meetLocation;
    protected Boolean isEdit = false;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                //Placeholder for About activity
                return true;
            case R.id.action_settings:
                // Placeholder for setting activity
                return true;
            case R.id.action_add_event:
                Intent intent = new Intent(this, TrackableListActivity.class);
                intent.putExtra("caller", "ParentActivity");
                startActivity(intent);
                return true;
            case R.id.action_edit:
                isEdit = true;
                AddTrackingActivity editing = new AddTrackingActivity();
                Intent edit = new Intent(this, AddTrackingActivity.class);
                edit.putExtra("title", trackingTitle);
                edit.putExtra("start_time", )
                startActivity(edit);
                return true;
            case R.id.action_save:
                //Save input item and create a tracking even object



                EditText addTitle = (EditText) findViewById(R.id.addTitle);
                TextView addMeetTIme = (TextView) findViewById(R.id.addMeetTIme);
                String title = addTitle.getText().toString();
                int currentTrackableID = TrackableDetailActivity.getCurrentTrackableID();
                String meetTime = addMeetTIme.getText().toString();

                TrackingList.getInstance().addTracking(title, currentTrackableID, stationaryStartTime ,meetTime, stationaryEndTime, meetLocation);
                Intent goBack = new Intent(this, TrackingListActivity.class);
                goBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(goBack);
                finish();
                return true;
            case R.id.action_cancel:
                StationaryPeriodListActivity.getmStationaryEndTime().clear();
                StationaryPeriodListActivity.getmStationaryStart().clear();
                StationaryPeriodListActivity.getmStationaryLat().clear();
                StationaryPeriodListActivity.getmStationaryLong().clear();
                stationaryEndTime = "";
                stationaryLat = "";
                stationaryLong = "";
                stationaryStartTime = "";
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
}
