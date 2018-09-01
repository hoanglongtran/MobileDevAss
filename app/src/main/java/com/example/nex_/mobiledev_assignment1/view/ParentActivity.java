package com.example.nex_.mobiledev_assignment1.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;

public abstract class ParentActivity extends AppCompatActivity {
    //This abstract class handles all action from every other class on the app bar
    protected String meetLocation;
    protected String stationaryStartTime;
    protected String stationaryEndTime;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                return true;
            case R.id.action_settings:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            case R.id.action_add_event:
                Intent intent = new Intent(this, TrackableListActivity.class);
                intent.putExtra("caller", "ParentActivity");
                startActivity(intent);
                return true;
            case R.id.action_edit:
                return true;
            case R.id.action_save:
                //Save input item and create a tracking even object
                EditText addTitle = (EditText) findViewById(R.id.addTitle);
                TextView addMeetTIme = (TextView) findViewById(R.id.addMeetTIme);
                String title = addTitle.getText().toString();
                int currentTrackableID = TrackableDetailActivity.getCurrentTrackableID();
                String meetTime = addMeetTIme.getText().toString();

                TrackingList.getInstance().addTracking(title, TrackableDetailActivity.getCurrentTrackableID(), stationaryStartTime ,meetTime, stationaryEndTime, meetLocation);
                return true;
            case R.id.action_cancel:
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
