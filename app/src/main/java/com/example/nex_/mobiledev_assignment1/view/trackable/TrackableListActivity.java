package com.example.nex_.mobiledev_assignment1.view.trackable;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;


import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.controller.Controller;
import com.example.nex_.mobiledev_assignment1.model.SuggestionManager;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;
import com.example.nex_.mobiledev_assignment1.view.ParentActivity;

import java.util.ArrayList;

public class TrackableListActivity extends ParentActivity {
    private static final String TAG = "TrackableListActivity";

    private ArrayList<String> mTrackableName = new ArrayList<>();
    private ArrayList<String> mTrackableCategory = new ArrayList<>();
    private TrackableListRecycleViewAdapter adapter;
    private String caller = "";
    private static Boolean key = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackable_list);

        addPermissionHelper(REQUEST_WRITE_STORAGE,
                findViewById(R.id.coordinatorLayout),"Storage Permission Required", Manifest.permission.WRITE_EXTERNAL_STORAGE);


        testPermissions(REQUEST_WRITE_STORAGE);

        Log.d(TAG, "onCreate: started");
        if (key){
            //prevent initialize objects twice
            Controller.getInstance().initTrackables(this);

            key = false;
        }
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);

        caller = getIntent().getStringExtra("caller");
        Log.d(TAG, "onCreate: This is the one who called" + caller);

        if (caller != null && caller.equals("ParentActivity")){
            setTitle("Pick a trackable");
        }
        setSupportActionBar(myToolbar);
        initName();
        SuggestionManager manager= new SuggestionManager();
        manager.getDistanceInfo();
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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.trackableRecycleView);
        adapter = new TrackableListRecycleViewAdapter(caller, mTrackableName, mTrackableCategory,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_buttons, menu);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_edit).setVisible(false);
        menu.findItem(R.id.action_add_event).setVisible(false);
        menu.findItem(R.id.action_cancel).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(true);


        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
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




}
