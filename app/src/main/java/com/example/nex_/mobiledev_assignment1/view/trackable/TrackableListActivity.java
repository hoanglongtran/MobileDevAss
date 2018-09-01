package com.example.nex_.mobiledev_assignment1.view.trackable;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.controller.Controller;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;
import com.example.nex_.mobiledev_assignment1.view.ParentActivity;

import java.util.ArrayList;

public class TrackableListActivity extends ParentActivity {
    private static final String TAG = "TrackableListActivity";

    private ArrayList<String> mTrackableName = new ArrayList<>();
    private ArrayList<String> mTrackableCategory = new ArrayList<>();

    private String caller = "";
    private static Boolean key = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackable_list);
        Log.d(TAG, "onCreate: started");
        if (key){
            //prevent initialize objects twice
            Controller.getInstance().initTrackables();

            key = false;
        }
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);

        caller = getIntent().getStringExtra("caller");
        System.out.println("This is the one who called: " + caller);

        if (caller != null && caller.equals("ParentActivity")){
            setTitle("Pick a trackable");
        }
        setSupportActionBar(myToolbar);
        initName();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_buttons, menu);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_edit).setVisible(false);
        menu.findItem(R.id.action_add_event).setVisible(false);
        menu.findItem(R.id.action_cancel).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initName(){
        Log.d(TAG, "initName: preparing names");
        mTrackableName = TrackableList.getInstance().getTrackableNames();
        mTrackableCategory = TrackableList.getInstance().getTrackableCategory();
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: ");
        //need to be called through controller packagae
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.trackable_recycle_view);
        TrackableListRecycleViewAdapter adapter = new TrackableListRecycleViewAdapter(caller, mTrackableName, mTrackableCategory,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
