package com.example.nex_.mobiledev_assignment1.view.trackable;

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

import java.util.ArrayList;

public class TrackableListRecycleViewAdapter extends RecyclerView.Adapter<TrackableListRecycleViewAdapter.ViewHolder>{
    private static final String TAG = "TrackableListRecycleViewAdapter";

    private ArrayList<String> mTrackableName;
    private ArrayList<String> mTrackableCategory;
    private String whoCalling;
    private Context mContext;

    public TrackableListRecycleViewAdapter(String whoCalling, ArrayList<String> mTrackableName, ArrayList<String> mTrackableCategory, Context mContext) {
        this.whoCalling = whoCalling;
        this.mTrackableName = mTrackableName;
        this.mTrackableCategory = mTrackableCategory;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trackable_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        System.out.println("Trackable names from recycler view");
        System.out.println(mTrackableName.get(position));
        System.out.println("Current position: " + position);
        holder.trackableName.setText(mTrackableName.get(position));
        holder.trackableCategory.setText(mTrackableCategory.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trackableDetail = new Intent(v.getContext(), TrackableDetailActivity.class);
                trackableDetail.putExtra("trackable_id", TrackableList.getInstance().getTrackablesList().get(3).getTrackabelID());
                trackableDetail.putExtra("trackable_name", TrackableList.getInstance().getTrackablesList().get(position).getName());
                trackableDetail.putExtra("trackable_des", TrackableList.getInstance().getTrackablesList().get(position).getTackableDes());
                trackableDetail.putExtra("trackable_url", TrackableList.getInstance().getTrackablesList().get(position).getURL());
                trackableDetail.putExtra("trackable_category", TrackableList.getInstance().getTrackablesList().get(position).getCategory());
                trackableDetail.putExtra("trackable_id", TrackableList.getInstance().getTrackablesList().get(position).getTrackabelID());
                trackableDetail.putExtra("whoCalled", whoCalling);
                System.out.println("Click postion:" + position);
                v.getContext().startActivity(trackableDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTrackableName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Hold the widget in memory individually
        TextView trackableName;
        TextView trackableCategory;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            trackableName = itemView.findViewById(R.id.trackable_name);
            trackableCategory = itemView.findViewById(R.id.trackable_category);
            parentLayout = itemView.findViewById(R.id.trackable_list_item_parent_layout);
        }
    }

    public void setWhoCalling(String whoCalling) {
        this.whoCalling = whoCalling;
    }
}
