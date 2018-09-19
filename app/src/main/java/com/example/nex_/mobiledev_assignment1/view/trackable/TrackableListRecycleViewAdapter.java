package com.example.nex_.mobiledev_assignment1.view.trackable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nex_.mobiledev_assignment1.R;
import com.example.nex_.mobiledev_assignment1.model.trackable.TrackableList;

import java.util.ArrayList;
import java.util.List;

public class TrackableListRecycleViewAdapter extends RecyclerView.Adapter<TrackableListRecycleViewAdapter.ViewHolder> implements Filterable{
    private static final String TAG = "TrackableListRecycleViewAdapter";

    private ArrayList<String> mTrackableName;
    private ArrayList<String> mTrackableCategory;
    private ArrayList<String> mTrackableCategoryFull;
    private String whoCalling;
    private Context mContext;

    TrackableListRecycleViewAdapter(String whoCalling, ArrayList<String> mTrackableName, ArrayList<String> mTrackableCategory, Context mContext) {
        this.whoCalling = whoCalling;
        this.mTrackableName = mTrackableName;
        this.mTrackableCategory = mTrackableCategory;
        this.mContext = mContext;
        mTrackableCategoryFull = new ArrayList<>(mTrackableCategory);//Copy the list to use independently
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trackable_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.trackableName.setText(mTrackableName.get(position));
        holder.trackableCategory.setText(mTrackableCategory.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trackableDetail = new Intent(v.getContext(), TrackableDetailActivity.class);
                trackableDetail.putExtra("trackable_id", TrackableList.getInstance().getTrackablesList().get(position).getTrackabelID());
                trackableDetail.putExtra("trackable_name", TrackableList.getInstance().getTrackablesList().get(position).getName());
                trackableDetail.putExtra("trackable_des", TrackableList.getInstance().getTrackablesList().get(position).getTackableDes());
                trackableDetail.putExtra("trackable_url", TrackableList.getInstance().getTrackablesList().get(position).getURL());
                trackableDetail.putExtra("trackable_category", TrackableList.getInstance().getTrackablesList().get(position).getCategory());
                trackableDetail.putExtra("whoCalled", whoCalling);
                Log.d(TAG, "onClick: Clicked position:"+position);
                v.getContext().startActivity(trackableDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTrackableCategory.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //Hold the widget in memory individually
        TextView trackableName;
        TextView trackableCategory;
        RelativeLayout parentLayout;
        ViewHolder(View itemView) {
            super(itemView);
            trackableName = itemView.findViewById(R.id.trackable_name);
            trackableCategory = itemView.findViewById(R.id.trackable_category);
            parentLayout = itemView.findViewById(R.id.trackable_list_item_parent_layout);
        }
    }

    @Override
    public Filter getFilter() {
        return mTrackableCategoryFilter;
    }

    private Filter mTrackableCategoryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<String> filteredList = new ArrayList<>();
            if (constraint == null|| constraint.length() == 0){
                filteredList.addAll(mTrackableCategoryFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (String item : mTrackableCategoryFull){
                    if (item.toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mTrackableCategory.clear();
            mTrackableCategory.addAll((List) results.values);

            notifyDataSetChanged();
        }
    };

}
