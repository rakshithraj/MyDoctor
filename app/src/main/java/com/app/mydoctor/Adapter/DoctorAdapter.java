package com.app.mydoctor.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.app.mydoctor.R;

import java.util.ArrayList;

import com.app.mydoctor.AppInterface.AdapterInterface;
import com.app.mydoctor.WebService.ConnectWebService;
import com.app.mydoctor.dao.DoctorDetailInfo;

/**
 * Created by Rakshith on 10/13/2015.
 */
public class DoctorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    AdapterInterface adapterInterface;
    private ArrayList<DoctorDetailInfo> mDataset;
    ConnectWebService connectWebService=new ConnectWebService();
    ImageLoader imageLoader;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvOfferList;
        ImageView imgOffer;

        public ViewHolder(View v) {
            super(v);
            tvOfferList=(TextView)v.findViewById(R.id.tvOfferList);
            imgOffer=(ImageView)v.findViewById(R.id.imgOffer);

            view = v;
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    public DoctorAdapter(ArrayList<DoctorDetailInfo> myDataset, AdapterInterface adapterInterface) {
        mDataset = myDataset;
        this.adapterInterface = adapterInterface;
    }

    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.doctor_row, parent, false);

            vh = new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress_item, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
           // if (imageLoader == null)
             //   imageLoader = AppController.getInstance().getImageLoader();
            ((ViewHolder) holder).tvOfferList.setText(mDataset.get(position).getDoctor_name());

            ((ViewHolder) holder).view.setOnClickListener(new onItemClickListner(position, mDataset.get(position)));
            //((ViewHolder) holder).imgOffer.setImageUrl(mDataset.get(position).getImageUrl(), imageLoader);


        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private class onItemClickListner implements View.OnClickListener {
        int position;
        DoctorDetailInfo doctorDetailInfo;

        public onItemClickListner(int position, DoctorDetailInfo doctorDetailInfo) {
            this.position = position;
            this.doctorDetailInfo = doctorDetailInfo;
        }

        @Override
        public void onClick(View v) {
            if(adapterInterface!=null)
                adapterInterface.onItem(position,doctorDetailInfo);
        }
    }
}