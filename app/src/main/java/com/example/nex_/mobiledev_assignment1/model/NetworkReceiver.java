package com.example.nex_.mobiledev_assignment1.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import static android.support.constraint.Constraints.TAG;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /*if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            Toast.makeText(context, "Connectivity Changed", Toast.LENGTH_SHORT).show();
        }*/
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            Log.d(TAG, "onReceive: Network: Have Internet");
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to mobile data
            }
        } else {
            Log.d(TAG, "onReceive: Network: No Internet");
            Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            // not connected to the internet
        }
    }
}
