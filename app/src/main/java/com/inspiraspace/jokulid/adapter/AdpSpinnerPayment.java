package com.inspiraspace.jokulid.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inspiraspace.jokulid.BuildConfig;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.ListChatapp;
import com.inspiraspace.jokulid.model.preaddtransaction.Payment;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.ArrayList;

/**
 * Created by mursitaffandi on 4/7/18.
 */

public class AdpSpinnerPayment extends ArrayAdapter<String> {

    private Activity activity;
    private ArrayList data;
    Payment tempValues=null;
    LayoutInflater inflater;

    public AdpSpinnerPayment(
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
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.item_payment, parent, false);
        tempValues = (Payment) data.get(position);
        TextView label = row.findViewById(R.id.tv_item_payment);
        ImageView icon = row.findViewById(R.id.iv_item_payment);
        label.setText(tempValues.getBankName());
        Glide
                .with(activity)
                .load(BuildConfig.BASE_URL_MAIN_IMAGE_PAYMENT + tempValues.getBankImage())
                .into(icon);
        return row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.item_payment, parent, false);
        tempValues = (Payment) data.get(position);
        TextView label = row.findViewById(R.id.tv_item_payment);
        ImageView icon = row.findViewById(R.id.iv_item_payment);
        label.setText(tempValues.getBankName());
        icon.setImageResource(Constant.LIST_IC_CHATAPP[position]);
        return row;
    }
}
