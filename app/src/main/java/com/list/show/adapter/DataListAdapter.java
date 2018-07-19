package com.list.show.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.list.show.MainActivity;
import com.list.show.R;
import com.list.show.pojo.Entity;
import com.list.show.pojo.RootObject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;


public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.MyViewHolder> {

    private List<Entity> dataSet;
    ImageLoader imageLoader;
    Context context;

    public DataListAdapter(List<Entity> data) {
        this.dataSet = data;
        imageLoader=ImageLoader.getInstance();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {



        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // resource or drawable
                .showImageForEmptyUri(R.mipmap.ic_launcher) // resource or drawable
                .showImageOnFail(R.mipmap.ic_launcher) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(10)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .build();


        TextView username = holder.username;
        TextView address = holder.address;
        TextView time = holder.time;
        TextView comments=holder.comments;
        CircleImageView usericon = holder.usericon;
        final ImageView imageView=holder.image;

        String strDate =dataSet.get(listPosition).getCreatedOn();

        String[] yearmonthdate=dataSet.get(listPosition).getCreatedOn().split("-");


        username.setText(dataSet.get(listPosition).getUserId().getFirstName());
        address.setText(dataSet.get(listPosition).getAddress());
        time.setText(String.valueOf(monthsBetweenDates(Integer.parseInt(yearmonthdate[0]),Integer.parseInt(yearmonthdate[1]),01))+" Months");
        comments.setText(String.valueOf(dataSet.get(listPosition).getCommentCount())+" Comments");
        imageLoader.displayImage(dataSet.get(listPosition).getFileUrl(),usericon, options);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dataSet.get(listPosition).isActive)
                {
                    dataSet.get(listPosition).isActive=false;
                    imageView.setVisibility(View.GONE);
                }else
                {
                    dataSet.get(listPosition).isActive=true;
                    imageView.setVisibility(View.VISIBLE);
                    imageLoader.displayImage(dataSet.get(listPosition).getFileUrl(),imageView);
                }


                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView address;
        CircleImageView usericon;
        TextView comments;
        TextView time;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.username = (TextView) itemView.findViewById(R.id.username);
            this.address = (TextView) itemView.findViewById(R.id.address);
            this.usericon = (CircleImageView) itemView.findViewById(R.id.avatar);
            this.comments = (TextView) itemView.findViewById(R.id.comments);
            this.time = (TextView) itemView.findViewById(R.id.time);
            this.image=(ImageView) itemView.findViewById(R.id.image);
        }
    }




    public int monthsBetweenDates(int year, int month, int day) {

        Calendar dob = Calendar.getInstance();
        dob.set(year, month, day);

        Calendar today = Calendar.getInstance();

        int monthsBetween = 0;
        int dateDiff = today.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH);

        if (dateDiff < 0) {
            int borrrow = today.getActualMaximum(Calendar.DAY_OF_MONTH);
            dateDiff = (today.get(Calendar.DAY_OF_MONTH) + borrrow) - dob.get(Calendar.DAY_OF_MONTH);
            monthsBetween--;

            if (dateDiff > 0) {
                monthsBetween++;
            }
        } else {
            monthsBetween++;
        }
        monthsBetween += today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
        monthsBetween += (today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)) * 12;
        return monthsBetween;
    }
}