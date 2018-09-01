package com.example.nex_.mobiledev_assignment1.view.tracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;
import com.example.nex_.mobiledev_assignment1.view.ParentActivity;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableListActivity;

import java.util.ArrayList;

public class TrackingListActivity extends ParentActivity {
    private static final String TAG = "TrackableListActivity";

    private static ArrayList<String> mTrackingTitle = new ArrayList<>();
    private static ArrayList<String> mTrackingMeetTime = new ArrayList<>();
    //private Controller controller = new Controller();


    public static ArrayList<String> getmTrackingTitle() {
        return mTrackingTitle;
    }

    public static ArrayList<String> getmTrackingMeetTime() {
        return mTrackingMeetTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_list);
        Log.d(TAG, "onCreate: started");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        initName();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_buttons, menu);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_edit).setVisible(false);
        menu.findItem(R.id.action_add_event).setVisible(true);
        menu.findItem(R.id.action_cancel).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_event:
                Intent intent = new Intent(this, TrackableListActivity.class);
                intent.putExtra("caller", "ParentActivity");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initName(){
        Log.d(TAG, "initName: preparing names");
        //TrackingList.getInstance().addTracking(0, "First Tracking Event", 1, "18:00", "12:00", "13:00");
        mTrackingTitle = TrackingList.getInstance().getTrackingTitle();
        mTrackingMeetTime = TrackingList.getInstance().getTrackingMeetTime();
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: ");
        RecyclerView trackingRecyclerView = (RecyclerView) findViewById(R.id.tracking_recycle_view);
        TrackingListRecycleViewAdapter adapter = new TrackingListRecycleViewAdapter(mTrackingTitle, mTrackingMeetTime,this);
        trackingRecyclerView.setAdapter(adapter);
        trackingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
