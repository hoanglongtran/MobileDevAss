package com.example.nex_.mobiledev_assignment1.view;


import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.PermissionHelper;

import java.util.HashMap;
import java.util.Map;

public abstract class ParentActivity extends PermissionActivity {
    protected static boolean isEdit;
    protected static String currentLocation;
    protected static int trackedTrackableID;
    private Map<Integer, PermissionHelper> helpers = new HashMap<>();
    // store main layout for use by permissions Snackbar
    private View layout;

    private String LOG_TAG = this.getClass().getName();

    // this is just an arbitrary static id for the PermissionActivity
    public static final int REQUEST_WRITE_STORAGE = 1;




    public void testPermissions(int requestID)
    {
        Log.i(LOG_TAG, "testPermissions()");
        // call superclass method to check if permission has been granted
        // we assume we would require this permisison but are just logging in this example
        if(checkPermission(requestID))
        {
            Log.i(LOG_TAG, "permission granted to perform action");
            // TODO do something that requires the requested permission ..
        }
    }


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
