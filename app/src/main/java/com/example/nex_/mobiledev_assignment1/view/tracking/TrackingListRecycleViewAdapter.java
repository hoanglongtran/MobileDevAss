/*package com.example.nex_.mobiledev_assignment1.view.tracking;

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

import java.util.ArrayList;

public class TrackingListRecycleViewAdapter extends RecyclerView.Adapter<TrackingListRecycleViewAdapter.ViewHolder>{
    private static final String TAG = "TrackingListRecycleViewAdapter";

    private ArrayList<String> mTrackingTitle;
    private ArrayList<String> mTrackingMeetTime;
    private Context mContext;

    TrackingListRecycleViewAdapter(ArrayList<String> mTrackingTitle, ArrayList<String> mTrackingMeetTime, Context mContext) {

        this.mTrackingTitle = mTrackingTitle;
        this.mTrackingMeetTime = mTrackingMeetTime;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tracking_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.trackingTitle.setText(mTrackingTitle.get(position));
        holder.trackingMeetTime.setText(mTrackingMeetTime.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trackingDetail = new Intent(v.getContext(), TrackingDetailActivity.class);
                trackingDetail.putExtra("tracking_title", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getTitle());
                trackingDetail.putExtra("tracking_start_time", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getStartTime());
                trackingDetail.putExtra("tracking_meet_time", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getMeetTime());
                trackingDetail.putExtra("tracking_end_time", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getEndTime());
                trackingDetail.putExtra("tracking_meet_location", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getMeetLocation());
                trackingDetail.putExtra("picked_trackable_id", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getTrackableID());
                trackingDetail.putExtra("picked_event", holder.getAdapterPosition());
                v.getContext().startActivity(trackingDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTrackingTitle.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //Hold the widget in memory individually
        TextView trackingTitle;
        TextView trackingMeetTime;
        RelativeLayout parentLayout;
        ViewHolder(View itemView) {
            super(itemView);
            trackingTitle = itemView.findViewById(R.id.trackingTitle);
            trackingMeetTime = itemView.findViewById(R.id.trackingMeetTime);
            parentLayout = itemView.findViewById(R.id.trackingListItemParentLayout);
        }
    }


}*/



package com.example.nex_.mobiledev_assignment1.view.tracking;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.DatabaseHelper;
import com.example.nex_.mobiledev_assignment1.model.tracking.TrackingList;

public class TrackingListRecycleViewAdapter extends RecyclerView.Adapter<TrackingListRecycleViewAdapter.TrackingListViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public  TrackingListRecycleViewAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class TrackingListViewHolder extends RecyclerView.ViewHolder{
        public TextView trackingTitle;
        public TextView trackingMeetTime;
        RelativeLayout parentLayout;
        public TrackingListViewHolder(View itemView) {
            super(itemView);
            trackingTitle = itemView.findViewById(R.id.trackingTitle);
            trackingMeetTime = itemView.findViewById(R.id.trackingMeetTime);
            parentLayout = itemView.findViewById(R.id.trackingListItemParentLayout);
        }
    }

    @Override
    public TrackingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.tracking_list_item, parent, false);
        return new TrackingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TrackingListViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }
        String title = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.TRACKING_COL_2));
        String ID = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.TRACKING_COL_1));
        String meetTime = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.TRACKING_COL_4));



        holder.trackingTitle.setText(title);
        holder.trackingMeetTime.setText(meetTime);
        holder.itemView.setTag(ID);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trackingDetail = new Intent(v.getContext(), TrackingDetailActivity.class);
                trackingDetail.putExtra("tracking_title", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getTitle());
                trackingDetail.putExtra("tracking_start_time", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getStartTime());
                trackingDetail.putExtra("tracking_meet_time", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getMeetTime());
                trackingDetail.putExtra("tracking_end_time", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getEndTime());
                trackingDetail.putExtra("tracking_meet_location", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getMeetLocation());
                trackingDetail.putExtra("picked_trackable_id", TrackingList.getInstance().getTrackingList().get(holder.getAdapterPosition()).getTrackableID());
                trackingDetail.putExtra("picked_event", holder.getAdapterPosition());
                v.getContext().startActivity(trackingDetail);
            }
        });
    }
    //Get all trackings
    @Override
    public int getItemCount() {
        return mCursor.getCount();

    }


}











































