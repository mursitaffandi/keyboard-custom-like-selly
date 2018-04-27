package com.inspiraspace.jokulid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.inspiraspace.jokulid.JokulidApplication;
import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.rajaongkir.Item_Ongkir;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mursitaffandi on 4/25/18.
 */

public class AdpLVSettingShippmentcompany extends BaseAdapter {
    List<String> company;
    Context mcontext;
List<String> selectedCompany = new ArrayList<>();
List<String> savedSelectedCompany;
    public AdpLVSettingShippmentcompany(List<String> company, Context mcontext) {
        this.company = company;
        this.mcontext = mcontext;
        savedSelectedCompany = JokulidApplication.getInstance().getShippmentCompany();
    }

    @Override
    public int getCount() {
        return company==null?0:company.size();
    }

    @Override
    public String getItem(int position) {
        return company.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View inflateView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (inflateView == null) {
            inflateView = View.inflate(mcontext, R.layout.item_setting_shippmentcompany, null);
            viewHolder = new ViewHolder(inflateView);
            viewHolder.ckb_item_shippmentcompany.setText(company.get(position));
            if (savedSelectedCompany.contains(company.get(position))) {
                viewHolder.ckb_item_shippmentcompany.setChecked(true);
                selectedCompany.add(company.get(position));
            }

            viewHolder.ckb_item_shippmentcompany.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        selectedCompany.add(company.get(position));
                    }
                    else {
                        selectedCompany.remove(company.get(position));
                    }
                }
            });

        }else {
            viewHolder = (ViewHolder) inflateView.getTag();
        }

        return inflateView;
    }

    public class ViewHolder {
        @BindView(R.id.ckb_item_shippmentcompany)
        CheckBox ckb_item_shippmentcompany;

        public ViewHolder(View v) {
            ButterKnife.bind(this,v);
        }
    }

    public List<String> getSelectedCompany(){
        return selectedCompany;
    }
}
