package com.example.nex_.mobiledev_assignment1.view;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.model.NetworkReceiver;
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;

public class App extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    NetworkReceiver networkReceiver = new NetworkReceiver();
    //This will be called as soon as we start the app
    @Override
    public void onCreate() {
        super.onCreate();
        //Register the NetworkReceiver here since we want it to run as long as the app is running
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
        createNotificationChannels();
    }



    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }
    }

    public void startThread(View view){
        DatabaseThread thread = new DatabaseThread(view);
        thread.start();
    }

    class DatabaseThread extends Thread{
        View view;
        DatabaseThread(View view){
            this.view = view;
        }

        @Override
        public void run() {
            TrackingList.getInstance().setTrackingList(TrackingList.getInstance().getTracking(view.getContext()));
        }
    }
}
