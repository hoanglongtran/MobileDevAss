package com.example.nex_.mobiledev_assignment1.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.TrackingListRecycleViewAdapter;

import java.util.ArrayList;

public class TrackingListActivity extends ParentActivity {
    private static final String TAG = "TrackableListActivity";

    private ArrayList<String> mTrackingTitle = new ArrayList<>();
    private ArrayList<String> mTrackingMeetTime = new ArrayList<>();
    //private TrackablesController controller = new TrackablesController();



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
        menu.findItem(R.id.action_cancel).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    private void initName(){
        Log.d(TAG, "initName: preparing names");
        mTrackingTitle.add("Hello");
        mTrackingMeetTime.add("adfas");
        mTrackingTitle.add("adfasdfxcvx");
        mTrackingMeetTime.add("vcbser");
        //mTrackingTitle = TrackableList.getInstance().getTrackableNames();
        //mTrackingMeetTime = TrackableList.getInstance().getTrackableCategory();
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
