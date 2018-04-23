package com.example.drams.daylilyapp;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Drams on 4/23/2018.
 */
public class Item implements Serializable{

    private long mDateTime;
    private String mTitle;
    private String mName;
    private String mDescription;

    public Item(long DateTime, String Title, String Name, String Description) {
        mDateTime = DateTime;
        mTitle = Title;
        mName = Name;
        mDescription = Description;
    }

    public String getDateTimeFormatted(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDateTime));
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getTitle() {

        return mTitle;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public long getDateTime() {

        return mDateTime;
    }
}
