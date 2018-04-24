package com.inspiraspace.jokulid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.transactions.Response;
import com.inspiraspace.jokulid.subactivities.DetailTransaction;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mursitaffandi on 4/23/18.
 */

public class AdpSubcdtPendings extends BaseAdapter {

    List<Response> transactionList;
    Context mContext;

    public AdpSubcdtPendings(List<Response> logList, Context mContext) {
        this.transactionList = logList;
        this.mContext = mContext;
    }

    public void swapLogs(List<Response> logs) {
        this.transactionList = logs;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return transactionList == null ? 0 : transactionList.size();
    }

    @Override
    public Response getItem(int position) {
        return transactionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.getLong(transactionList.get(position).getTransactionId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AdpSubcdtPendings.ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (AdpSubcdtPendings.ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.item_subcdt_pending, parent, false);
            viewHolder = new AdpSubcdtPendings.ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }
        final Response itemPending = transactionList.get(position);

        // get the TextView for item name and item description
        viewHolder.tv_item_subcdt_date.setText(itemPending.getCustomerCustomerCreatedat());
        viewHolder.tv_item_subcdt_customer_name.setText(itemPending.getCustomerCustomerName());
        viewHolder.tv_item_subcdt_customer_nohp.setText(itemPending.getCustomerCustomerNohp());
        viewHolder.tv_item_subcdt_customer_totalprice.setText(itemPending.getTotal());
        viewHolder.iv_item_subcdt_customer_paymentmethod.setText(itemPending.getBankBankName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(mContext, DetailTransaction.class);
                intent.putExtra(Constant.TAG_TRANSACTION, itemPending);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


    public class ViewHolder {
        @BindView(R.id.tv_item_subcdt_date)
        TextView tv_item_subcdt_date;

        @BindView(R.id.tv_item_subcdt_customer_name)
        TextView tv_item_subcdt_customer_name;

        @BindView(R.id.tv_item_subcdt_customer_nohp)
        TextView tv_item_subcdt_customer_nohp;

        @BindView(R.id.tv_item_subcdt_customer_totalprice)
        TextView tv_item_subcdt_customer_totalprice;

        @BindView(R.id.tv_item_subcdt_customer_paymentmethod)
        TextView iv_item_subcdt_customer_paymentmethod;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
