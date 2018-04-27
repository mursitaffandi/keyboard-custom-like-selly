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
import com.inspiraspace.jokulid.model.autotext.Response;
import com.inspiraspace.jokulid.model.preaddtransaction.Payment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mursitaffandi on 4/24/18.
 */

public class AdpSettingPayment extends BaseAdapter {
    List<Payment> paymentList;
Context mContext;
    public AdpSettingPayment(Context mContext) {
        this.mContext = mContext;
    }

    public void swapRefresh(List<Payment> objects){
        paymentList = objects;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return paymentList==null ? 0 : paymentList.size();
    }

    @Override
    public Payment getItem(int position) {
        return paymentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.item_payment, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }
        final Payment itemAutotext = getItem(position);
        // get the TextView for item name and item description
        Glide.with(mContext).load(BuildConfig.BASE_URL_MAIN_IMAGE_PAYMENT + itemAutotext.getBankImage()).into(viewHolder.iv_item_payment);
        viewHolder.tv_item_payment.setText(itemAutotext.getBankName());
        viewHolder.tv_item_payment_name.setText(itemAutotext.getBankAccountName());
        viewHolder.tv_item_payment_number.setText(itemAutotext.getBankAccountNumber());

        return convertView;
    }

    public class ViewHolder{
        @BindView(R.id.iv_item_payment)
        ImageView iv_item_payment;

        @BindView(R.id.tv_item_payment)
        TextView tv_item_payment;
        @BindView(R.id.tv_item_payment_name)
        TextView tv_item_payment_name;
        @BindView(R.id.tv_item_payment_number)
        TextView tv_item_payment_number;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
