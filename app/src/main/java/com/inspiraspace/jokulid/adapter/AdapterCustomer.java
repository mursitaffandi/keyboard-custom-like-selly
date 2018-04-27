package com.inspiraspace.jokulid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inspiraspace.jokulid.BuildConfig;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.customers.ResponseItem;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mursitaffandi on 4/19/18.
 */

public class AdapterCustomer extends RecyclerView.Adapter<AdapterCustomer.ViewHolder> {
    List<ResponseItem> responseItemList;
    Context mContext;

    public AdapterCustomer(Context mContext, List<ResponseItem> responseItemList) {
        this.responseItemList = responseItemList;
        this.mContext = mContext;
    }

    public void swapRefresh(List<ResponseItem> responseItems){
        this.responseItemList = responseItems;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ResponseItem tmpItem = responseItemList.get(position);
        holder.tv_item_customer_name.setText(tmpItem.getCustomerName());
        holder.tv_item_customer_totalspent.setText(tmpItem.getCustomerTotalSpent());
        Glide.with(mContext).load(BuildConfig.BASE_URL_MAIN_IMAGE_PAYMENT + tmpItem.getImage()).into(holder.iv_item_customer_icon_chatapp);

    }

    @Override
    public int getItemCount() {
        return responseItemList==null ? 0 : responseItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_customer_name)
        TextView tv_item_customer_name;

        @BindView(R.id.tv_item_customer_totalspent)
        TextView tv_item_customer_totalspent;

        @BindView(R.id.iv_item_customer_icon_chatapp)
        ImageView iv_item_customer_icon_chatapp;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
