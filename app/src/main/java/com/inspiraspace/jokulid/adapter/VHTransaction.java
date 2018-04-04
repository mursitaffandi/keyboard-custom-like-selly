package com.inspiraspace.jokulid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.inspiraspace.jokulid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mursitaffandi on 4/3/18.
 */

public class VHTransaction extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.textView4)
    TextView tvTopLeft;

    @BindView(R.id.textView3)
    TextView tvTopRight;

    @BindView(R.id.textView2)
    TextView tvBottomLeft;
    ItemTransactionEvent event;

    public VHTransaction(View itemView, ItemTransactionEvent event) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.event = event;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        event.OnClickitemTransaction(this);
    }

    interface ItemTransactionEvent {
        void OnClickitemTransaction(VHTransaction vhTransaction);
    }
}
