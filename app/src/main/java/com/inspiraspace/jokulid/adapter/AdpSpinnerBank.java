package com.inspiraspace.jokulid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inspiraspace.jokulid.BuildConfig;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.bank.ResponseItem;

import java.util.List;

/**
 * Created by mursitaffandi on 4/24/18.
 */

public class AdpSpinnerBank extends BaseAdapter {
    Context context;
    List<ResponseItem> banklist;
    LayoutInflater inflter;

    public AdpSpinnerBank(Context applicationContext, List<ResponseItem> banklist) {
        this.context = applicationContext;
        this.banklist = banklist;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return banklist.size();
    }

    @Override
    public ResponseItem getItem(int i) {
        return banklist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_payment, null);
        ResponseItem tmpBank = getItem(i);
        ImageView icon = view.findViewById(R.id.iv_item_payment);
        TextView names = view.findViewById(R.id.tv_item_payment);
        names.setText(tmpBank.getBankName());
        Glide.with(context).load(BuildConfig.BASE_URL_MAIN_IMAGE_PAYMENT + tmpBank.getBankImage()).into(icon);
        return view;
    }
}