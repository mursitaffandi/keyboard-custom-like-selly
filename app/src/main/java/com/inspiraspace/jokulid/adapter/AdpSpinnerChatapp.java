package com.inspiraspace.jokulid.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inspiraspace.jokulid.BuildConfig;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.preaddtransaction.Chatapp;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mursitaffandi on 4/7/18.
 */

public class AdpSpinnerChatapp extends ArrayAdapter<String>{

    private Activity activity;
    private ArrayList data;
    Chatapp tempValues =null;
    LayoutInflater inflater;

    public AdpSpinnerChatapp(
            Activity activitySpinner,
            int textViewResourceId,
            ArrayList objects
    )
    {
        super(activitySpinner, textViewResourceId, objects);
        activity = activitySpinner;
        data     = objects;
        inflater = (LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        View row = inflater.inflate(R.layout.item_chatapp, parent, false);
        tempValues = (Chatapp) data.get(position);
        TextView label = row.findViewById(R.id.tv_item_chatapp);
        ImageView icon = row.findViewById(R.id.iv_item_chatapp);
        label.setText(tempValues.getName());
        Glide.with(row).load(BuildConfig.BASE_URL_MAIN_IMAGE_PAYMENT + tempValues.getImage()).into(icon);
        return row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.item_chatapp, parent, false);
        tempValues = (Chatapp) data.get(position);
        TextView label = row.findViewById(R.id.tv_item_chatapp);
        ImageView icon = row.findViewById(R.id.iv_item_chatapp);
        label.setText(tempValues.getNick());
        Glide.with(row).load(BuildConfig.BASE_URL_MAIN_IMAGE_PAYMENT + tempValues.getImage()).into(icon);
        return row;
    }
}
