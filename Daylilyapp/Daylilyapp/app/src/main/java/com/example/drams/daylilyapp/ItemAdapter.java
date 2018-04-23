package com.example.drams.daylilyapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drams on 4/23/2018.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> items) {
        super(context, resource, items);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // return super.getView(position, convertView, parent);

        if(convertView ==null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_note, null);

        }
        Item item = getItem(position);
        if(item !=null) {
            TextView title = (TextView) convertView.findViewById(R.id.list_item_title);
            TextView date = (TextView) convertView.findViewById(R.id.list_item_date);
            TextView name = (TextView) convertView.findViewById(R.id.list_item_name);
            TextView desc = (TextView) convertView.findViewById(R.id.list_item_desc);

            title.setText(item.getTitle());
            date.setText(item.getDateTimeFormatted(getContext()));
            name.setText(item.getName());

            if(item.getDescription().length() >50){
                desc.setText(item.getDescription().substring(0,50));
            }else{
                desc.setText(item.getDescription());
            }
        }
        return convertView;
    }
}
