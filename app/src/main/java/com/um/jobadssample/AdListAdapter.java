package com.um.jobadssample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class AdListAdapter extends RecyclerView.Adapter<AdListAdapter.AdViewHolder> {

    class AdViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitleView;
        private final TextView mDescdView;

        public AdViewHolder(View itemView) {
            super(itemView);
            mTitleView = itemView.findViewById(R.id.title_text);
            mDescdView = itemView.findViewById(R.id.desc_text);
        }
    }

    private final LayoutInflater mInflater;
    private List<Ad> mAds = Collections.emptyList();

    AdListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public AdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.ad_item, parent, false);
        return new AdViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(AdViewHolder adViewHolder, int position) {
        Ad current = mAds.get(position);
        Log.v(AdListAdapter.class.getName(), current.toString());
        adViewHolder.mTitleView.setText(current.getID() + " " + current.getTitle());
        adViewHolder.mDescdView.setText(current.getDesc());
    }

    void setAds(List<Ad> ads) {
        mAds = ads;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return mAds.size();
    }


}
