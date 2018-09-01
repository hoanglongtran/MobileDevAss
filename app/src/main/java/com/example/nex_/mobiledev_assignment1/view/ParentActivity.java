package com.example.nex_.mobiledev_assignment1.view;


import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.example.nex_.mobiledev_assignment1.R;

public abstract class ParentActivity extends AppCompatActivity {
    protected static boolean isEdit;
    protected static String currentLocation;
    protected static int trackedTrackableID;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                //Placeholder for About activity
                return true;
            case R.id.action_settings:
                // Placeholder for setting activity
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public static int getTrackedTrackableID() {
        return trackedTrackableID;
    }

    public static void setTrackedTrackableID(int trackedTrackableID) {
        ParentActivity.trackedTrackableID = trackedTrackableID;
    }

    public static String getCurrentLocation() {
        return currentLocation;
    }

    public static void setCurrentLocation(String currentLocation) {
        ParentActivity.currentLocation = currentLocation;
    }

    public static void setIsEdit(boolean isEdit) {
        ParentActivity.isEdit = isEdit;
    }
}
