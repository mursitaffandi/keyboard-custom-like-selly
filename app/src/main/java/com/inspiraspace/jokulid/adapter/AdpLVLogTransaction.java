package com.inspiraspace.jokulid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.transactions.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mursitaffandi on 4/10/18.
 */

public class AdpLVLogTransaction extends BaseAdapter {
    List<Log> logList;
    Context mContext;

    public AdpLVLogTransaction(List<Log> logList, Context mContext) {
        this.logList = logList;
        this.mContext = mContext;
    }

    public void swapLogs(List<Log> logs){
        this.logList = logs;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return logList==null ? 0 : logList.size();
    }

    @Override
    public Object getItem(int position) {
        return logList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.getLong(logList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView!=null){
            viewHolder = (ViewHolder) convertView.getTag();
        }
       else  {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.item_log, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }
        final Log itemAutoText = logList.get(position);

        // get the TextView for item name and item description
        viewHolder.tv_item_log_time.setText(itemAutoText.getCreatedat());
        viewHolder.tv_item_log_description.setText(itemAutoText.getDescription());

        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.tv_item_log_time)
        TextView tv_item_log_time;
        @BindView(R.id.tv_item_log_description)
        TextView tv_item_log_description;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
