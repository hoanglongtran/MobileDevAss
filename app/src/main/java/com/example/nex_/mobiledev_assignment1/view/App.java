package com.example.nex_.mobiledev_assignment1.view;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;

import com.example.nex_.mobiledev_assignment1.model.NetworkReceiver;
import com.example.nex_.mobiledev_assignment1.model.SuggestionManager;
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;

public class App extends Application {
    private static final String LOG_TAG = "App";
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
        DatabaseAsyncTask task = new DatabaseAsyncTask();
        task.execute(this);

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

    public static class DatabaseAsyncTask extends AsyncTask<Context,Void, Void> {
        @Override
        protected Void doInBackground(Context... contexts) {
            TrackingList.getInstance().setTrackingList(TrackingList.getInstance().getTracking(contexts[0]));
            return null;
        }
    }

   /* public void startThread(View view){
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
    }*/
}
