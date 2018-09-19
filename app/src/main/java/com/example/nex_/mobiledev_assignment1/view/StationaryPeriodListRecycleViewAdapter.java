package com.example.nex_.mobiledev_assignment1.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;
import com.example.nex_.mobiledev_assignment1.view.trackable.TrackableDetailActivity;

import java.util.ArrayList;

public class StationaryPeriodListRecycleViewAdapter extends RecyclerView.Adapter<StationaryPeriodListRecycleViewAdapter.ViewHolder>{
    private static final String TAG = "StationaryPeriodListRecycleViewAdapter";

    private ArrayList<String> mStationaryStartTIme;
    private ArrayList<String> mStationaryEndTime;
    private ArrayList<Double> mStationaryLong;
    private ArrayList<Double> mStationaryLat;
    private int currentTrackableID = TrackableDetailActivity.getCurrentTrackableID();
    private Context mContext;

    public StationaryPeriodListRecycleViewAdapter(ArrayList<String> mStationaryStartTIme, ArrayList<String> mStationaryEndTime, ArrayList<Double> mStationaryLong, ArrayList<Double> mStationaryLat, Context mContext) {
        this.mStationaryStartTIme = mStationaryStartTIme;
        this.mStationaryEndTime = mStationaryEndTime;
        this.mStationaryLong = mStationaryLong;
        this.mStationaryLat = mStationaryLat;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stationary_period_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        TrackableList.getInstance().getTrackablesList().get(TrackableDetailActivity.getCurrentTrackableID()).setChosenStationary(position);
        holder.stationaryStartTime.setText(mStationaryStartTIme.get(position));
        holder.stationaryEndTime.setText(mStationaryEndTime.get(position));
        holder.stationaryLat.setText(Double.toString(mStationaryLat.get(position)));
        holder.stationaryLong.setText(Double.toString(mStationaryLong.get(position)));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addActivity = new Intent(v.getContext(), AddTrackingActivity.class);
                addActivity.putExtra("stationary_start", TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryStartTime().get(position));
                addActivity.putExtra("stationary_end", TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryEndTime().get(position));
                addActivity.putExtra("stationary_long", TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryLong().get(position));
                addActivity.putExtra("stationary_lat", TrackableList.getInstance().getTrackablesList().get(currentTrackableID).getStationaryLat().get(position));
                addActivity.putExtra("clicked_position", position);
                Log.d(TAG, "onClick: Clicked position: " + position);
                v.getContext().startActivity(addActivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        //return  mStationaryStartTIme == null ? 0 : mStationaryStartTIme.size();
        return mStationaryLat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Hold the widget in memory individually
        TextView stationaryStartTime;
        TextView stationaryEndTime;
        TextView stationaryLong;
        TextView stationaryLat;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            stationaryStartTime = itemView.findViewById(R.id.stationaryStartTime);
            stationaryEndTime = itemView.findViewById(R.id.stationaryEndTime);
            stationaryLong = itemView.findViewById(R.id.stationaryLongitude);
            stationaryLat = itemView.findViewById(R.id.stationaryLatitude);
            parentLayout = itemView.findViewById(R.id.stationaryPeriodListItemParentLayout);

        }
    }

}
