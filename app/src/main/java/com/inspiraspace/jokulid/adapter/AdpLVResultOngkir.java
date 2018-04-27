package com.inspiraspace.jokulid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.rajaongkir.Item_Ongkir;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mursitaffandi on 4/17/18.
 */

public class AdpLVResultOngkir extends BaseAdapter {

    private ArrayAdapter mArrAdapter;
    private List<Item_Ongkir> mArrDataOngkir;
    private Context mContext;

    public TextView tvEksType, tvCost;
    public CheckBox ckb_item_ongkir;
    private Map<String, String> itemsOngkir_selected = new HashMap<>();

    public AdpLVResultOngkir(Context context, List<Item_Ongkir> arrDataOngkir) {
        this.mContext = context;
        this.mArrDataOngkir = arrDataOngkir;
    }

    @Override
    public int getCount() {
        return mArrDataOngkir == null ? 0 : mArrDataOngkir.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder {
        protected TextView tvCost;
        protected CheckBox ckb_item_ongkir;
    }
    @Override
    public View getView(final int position, View inflateView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (inflateView == null) {
            inflateView = View.inflate(mContext, R.layout.item_ongkir, null);
            viewHolder = new ViewHolder();
            viewHolder.tvCost = inflateView.findViewById(R.id.tv_item_ongkir_fee);
            viewHolder.ckb_item_ongkir = inflateView.findViewById(R.id.ckb_item_ongkir);

            final Item_Ongkir strCost = mArrDataOngkir.get(position);
            viewHolder.tvCost.setText(strCost.getName());
            viewHolder.ckb_item_ongkir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                    mArrDataOngkir.get(getPosition).setSelected(buttonView.isChecked()); // Set the value of checkbox to maintain its state.

                }
            });
            inflateView.setTag(viewHolder);
            inflateView.setTag(R.id.tv_item_ongkir_fee, viewHolder.tvCost);
            inflateView.setTag(R.id.ckb_item_ongkir, viewHolder.ckb_item_ongkir);
        }else {
            viewHolder = (ViewHolder) inflateView.getTag();
        }
        viewHolder.ckb_item_ongkir.setTag(position); // This line is important.

        viewHolder.tvCost.setText(mArrDataOngkir.get(position).getName());
        viewHolder.ckb_item_ongkir.setChecked(mArrDataOngkir.get(position).isSelected());
        return inflateView;
    }

    public void swipeRefresh(List<Item_Ongkir> resultListOngkir) {
        this.mArrDataOngkir = resultListOngkir;
        notifyDataSetChanged();
    }

    public Map<String,String> getItemsOngkir_selected(){
        itemsOngkir_selected = new HashMap<>();
        for (int i = 0; i< mArrDataOngkir.size() ; i++){
            Item_Ongkir tmpItemOngkir = mArrDataOngkir.get(i);
            if (tmpItemOngkir.isSelected())
                if (!itemsOngkir_selected.containsKey(String.valueOf(i)))
                    itemsOngkir_selected.put(String.valueOf(i), tmpItemOngkir.getName());
            else
                if (itemsOngkir_selected.containsKey(String.valueOf(i)))
                    itemsOngkir_selected.remove(String.valueOf(i));
        }
        return itemsOngkir_selected;
    }
}
