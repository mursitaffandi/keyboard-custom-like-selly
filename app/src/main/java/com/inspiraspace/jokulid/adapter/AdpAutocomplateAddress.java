package com.inspiraspace.jokulid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.searchsubdistrict.Datum;

import java.util.ArrayList;

/**
 * Created by mursitaffandi on 4/19/18.
 */

public class AdpAutocomplateAddress extends ArrayAdapter{
    private Context mContext;
    private ArrayList<Datum> data;
    Datum tempValues = null;
    int inflater;

    public AdpAutocomplateAddress(Context viewAutoComplate, int resourceId, ArrayList<Datum> objects) {
        super(viewAutoComplate, resourceId, objects);
        this.mContext = viewAutoComplate;
        this.data = objects;
        this.inflater = resourceId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position).getFullAddress();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(inflater, parent, false);
        }
        final String selectedSubdistrictID = data.get(position).getSubdistrictId();
        final int i = position;
        TextView strName =  view.findViewById(R.id.tv_item_subdistrict_complatename);
        strName.setText(data.get(position).getFullAddress());
        return view;
    }

}
