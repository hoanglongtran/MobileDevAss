package com.example.nex_.mobiledev_assignment1.model;

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
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;
import com.example.nex_.mobiledev_assignment1.view.TrackingDetailActivity;

import java.util.ArrayList;

public class TrackingListRecycleViewAdapter extends RecyclerView.Adapter<TrackingListRecycleViewAdapter.ViewHolder>{
    private static final String TAG = "TrackingListRecycleViewAdapter";

    private ArrayList<String> mTrackingTitle;
    private ArrayList<String> mTrackingMeetTime;
    private Context mContext;

    public TrackingListRecycleViewAdapter(ArrayList<String> mTrackingTitle, ArrayList<String> mTrackingMeetTime, Context mContext) {

        this.mTrackingTitle = mTrackingTitle;
        this.mTrackingMeetTime = mTrackingMeetTime;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tracking_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        System.out.println("Tracking title from recycler view");
        System.out.println(mTrackingTitle.get(position));
        holder.trackingTitle.setText(mTrackingTitle.get(position));
        holder.trackingMeetTime.setText(mTrackingMeetTime.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trackingDetail = new Intent(v.getContext(), TrackingDetailActivity.class);
                trackingDetail.putExtra("tracking_title", TrackingList.getInstance().getTrackingList().get(position).getTitle());
                trackingDetail.putExtra("tracking_start_time", TrackingList.getInstance().getTrackingList().get(position).getStartTime());
                trackingDetail.putExtra("tracking_meet_time", TrackingList.getInstance().getTrackingList().get(position).getMeetTime());
                trackingDetail.putExtra("tracking_end_time", TrackingList.getInstance().getTrackingList().get(position).getEndTime());
                v.getContext().startActivity(trackingDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTrackingTitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Hold the widget in memory individually
        TextView trackingTitle;
        TextView trackingMeetTime;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            trackingTitle = itemView.findViewById(R.id.trackingTitle);
            trackingMeetTime = itemView.findViewById(R.id.trackingMeetTime);
            parentLayout = itemView.findViewById(R.id.trackingListItemParentLayout);
        }
    }
}
