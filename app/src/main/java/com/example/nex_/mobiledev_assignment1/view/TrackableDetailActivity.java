package com.example.nex_.mobiledev_assignment1.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;

import org.w3c.dom.Text;

public class TrackableDetailActivity extends AppCompatActivity {
    private static final String TAG = "TrackableDetailActivity";
    String trackableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackable_detail);
        Log.d(TAG, "onCreate: started");
        System.out.println(TrackableList.getInstance().getTrackablesList().get(0).getName());
        System.out.println(TrackableList.getInstance().getTrackablesList().get(0).getTackableDes());
        System.out.println(TrackableList.getInstance().getTrackablesList().get(0).getURL());
        System.out.println(TrackableList.getInstance().getTrackablesList().get(0).getCategory());
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if(getIntent().hasExtra("trackable_name") && getIntent().hasExtra("trackable_des")
                && getIntent().hasExtra("trackable_url") && getIntent().hasExtra("trackable_category")){
            Log.d(TAG, "getIncomingIntent: found intent extra");
            //check if have extra first before calling to prevent crashing when calling getStringExtra
            String trackableName = getIntent().getStringExtra("trackable_name");
            String trackableDes = getIntent().getStringExtra("trackable_des");
            String trackableURL = getIntent().getStringExtra("trackable_url");
            String trackableCategory = getIntent().getStringExtra("trackable_category");

            setTrackableDetail(trackableName,trackableDes,trackableURL,trackableCategory);
        }
    }

    private void setTrackableDetail(String trackableName, String trackableDes, String trackableURL, String trackableCategory){
        Log.d(TAG, "setTrackableDetail: setting to trackable details");
        TextView name = (TextView) findViewById(R.id.trackableName);
        name.setText(trackableName);
        TextView description = (TextView) findViewById(R.id.trackableDescription);
        description.setText(trackableDes);
        TextView URL = (TextView) findViewById(R.id.trackableURL);
        URL.setText(trackableURL);
        TextView category = (TextView) findViewById(R.id.trackableCategory);
        category.setText(trackableCategory);
    }
}
