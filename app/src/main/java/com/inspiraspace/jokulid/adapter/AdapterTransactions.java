package com.inspiraspace.jokulid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inspiraspace.jokulid.utils.Constant;
import com.inspiraspace.jokulid.subactivities.DetailTransaction;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mursitaffandi on 4/3/18.
 */

public class AdapterTransactions extends RecyclerView.Adapter<VHTransaction> implements VHTransaction.ItemTransactionEvent {
    List<Response> transactionList = new ArrayList<>();
    Context mContext;

    public AdapterTransactions(Context mContext) {
        this.mContext = mContext;
    }

    public void swipeLoadTransactions(List<Response> transactions){
        this.transactionList = transactions;
        notifyDataSetChanged();
    }

    @Override
    public VHTransaction onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, null);
        return new VHTransaction(view, this);
    }

    @Override
    public void onBindViewHolder(VHTransaction holder, int position) {
        holder.tvTopLeft.setText(transactionList.get(position).getCustomerCustomerName());
        holder.tvTopRight.setText(transactionList.get(position).getTotal());
        holder.tvBottomLeft.setText(transactionList.get(position).getCustomerCustomerNohp());

    }

    @Override
    public int getItemCount() {
        return (transactionList!=null) ? transactionList.size()  : 0;
    }

    @Override
    public void OnClickitemTransaction(VHTransaction vhTransaction) {
        final Intent intent = new Intent(mContext, DetailTransaction.class);
        intent.putExtra(Constant.TAG_ID_DETAIL_TRANSACTION, getItem(vhTransaction.getAdapterPosition()).getTransactionId());
        mContext.startActivity(intent);    }

    private Response getItem(int adapterPosition) {
        return transactionList.get(adapterPosition);
    }
}
