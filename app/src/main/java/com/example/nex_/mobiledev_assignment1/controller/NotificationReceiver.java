package com.example.nex_.mobiledev_assignment1.controller;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.SuggestionManager;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;
import com.example.nex_.mobiledev_assignment1.view.MainActivity;

import static com.example.nex_.mobiledev_assignment1.view.App.CHANNEL_1_ID;

public class NotificationReceiver extends BroadcastReceiver {
    private int index = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0, activityIntent, 0);

        if (intent.hasExtra("cancel")){
            cancelAlarm(context);
            Toast.makeText(context, "Alarm Canceled", Toast.LENGTH_SHORT).show();
        }else{
            String message = intent.getStringExtra("toastMessage");
            Toast.makeText(context, "Skipped", Toast.LENGTH_SHORT).show();
        }


        String name = TrackableList.getInstance().getTrackablesList().get(SuggestionManager.suggestionsList.get(index).getTrackableID()).getName();
        String distanceText = SuggestionManager.suggestionsList.get(index).getDistanceText();
        String travelTimeText = SuggestionManager.suggestionsList.get(index).getTravelTimeText();


        Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", "LeeeeeeeeRoooooy JeeeeeKiiiin");
        PendingIntent actionIntent = PendingIntent.getBroadcast(context,
                0 , broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);



        Intent broadcastIntent2 = new Intent(context, NotificationReceiver.class);
        broadcastIntent2.putExtra("cancel", "Suggestion canceled");
        PendingIntent actionIntent2 = PendingIntent.getBroadcast(context,
                1 , broadcastIntent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_search)
                .setContentTitle(name + " is " + distanceText + " away from you")
                .setContentText("Travel time from your location will take " + travelTimeText)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Skip", actionIntent)
                .addAction(R.mipmap.ic_launcher, "Cancel", actionIntent2)
                .build();
        notificationManager.notify(1,notification);
        index++;
        if (index > SuggestionManager.suggestionsList.size()){
            index = 0;
        }
    }

    public void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //Set the intent that will triggered when user taps the notification

        Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
        PendingIntent actionIntent = PendingIntent.getBroadcast(context,
                0 , broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        if (alarmManager != null) {
            alarmManager.cancel(actionIntent);
        }
    }


}
