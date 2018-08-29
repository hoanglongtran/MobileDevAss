package com.example.nex_.mobiledev_assignment1.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.controller.TrackablesController;
import com.example.nex_.mobiledev_assignment1.model.RecycleViewAdapter;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;

import java.util.ArrayList;

public class TrackableListActivity extends AppCompatActivity {
    private static final String TAG = "TrackableListActivity";

    private ArrayList<String> mTrackableName = new ArrayList<>();
    private ArrayList<String> mTrackableCategory = new ArrayList<>();
    private TrackablesController controller = new TrackablesController();
    private static Boolean key = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackable_list);
        Log.d(TAG, "onCreate: started");
        if (key){
            //prevent initialize objects twice
            controller.initTrackables();

            key = false;
        }

        initName();

    }
    private void initName(){
        Log.d(TAG, "initName: preparing names");
        mTrackableName = TrackableList.getInstance().getTrackableNames();
        mTrackableCategory = TrackableList.getInstance().getTrackableCategory();
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: ");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecycleViewAdapter adapter = new RecycleViewAdapter(mTrackableName, mTrackableCategory,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
