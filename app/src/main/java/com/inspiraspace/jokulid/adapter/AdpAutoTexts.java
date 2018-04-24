package com.inspiraspace.jokulid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.autotext.Response;
import com.inspiraspace.jokulid.subactivities.DetailTransaction;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mursitaffandi on 4/23/18.
 */

public class AdpAutoTexts extends BaseAdapter {

    List<Response> autotextList;
    Context mContext;
    public AdpAutoTexts( Context mContext) {
        this.mContext = mContext;
    }

    public void swapLogs(List<Response> logs) {
        this.autotextList = logs;
        notifyDataSetChanged();

    }


    @Override
    public int getCount() {
        return autotextList == null ? 0 : autotextList.size();
    }

    @Override
    public Response getItem(int position) {
        return autotextList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1L;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.item_autotext, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }
        final Response itemAutotext = autotextList.get(position);
        // get the TextView for item name and item description
        viewHolder.tv_item_autotext_title.setText(itemAutotext.getShortcut());
        viewHolder.tv_item_autotext_content.setText(itemAutotext.getContent());

        return convertView;
    }


    public class ViewHolder {
        @BindView(R.id.tv_item_autotext_title)
        TextView tv_item_autotext_title;

        @BindView(R.id.tv_item_autotext_content)
        TextView tv_item_autotext_content;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
