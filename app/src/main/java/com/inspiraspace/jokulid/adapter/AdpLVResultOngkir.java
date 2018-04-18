package com.inspiraspace.jokulid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.rajaongkir.Result;

import java.util.List;

/**
 * Created by mursitaffandi on 4/17/18.
 */

public class AdpLVResultOngkir  extends BaseAdapter {
    Context mContext;
    private List<Result> autotexts;

    public AdpLVResultOngkir(Context mContext, List<Result> autotexts) {
        this.mContext = mContext;
        this.autotexts = autotexts;
    }

    public void swipeRefresh(List<Result> autotexts) {
        this.autotexts = autotexts;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return autotexts == null ? 0 : autotexts.size();
    }

    @Override
    public Object getItem(int i) {
        return autotexts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.item_ongkir, parent, false);
        }
        final Result itemAutoText = autotexts.get(i);
        TextView tv_item_ongkir_company =
                convertView.findViewById(R.id.tv_item_ongkir_company);

        TextView tv_item_ongkir_fee =
                convertView.findViewById(R.id.tv_item_ongkir_fee);

        tv_item_ongkir_company.setText(itemAutoText.getCode());
        tv_item_ongkir_fee.setText(itemAutoText.getCosts().get(0).getValue().toString());
        return convertView;
    }
}
