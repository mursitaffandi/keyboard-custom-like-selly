package com.inspiraspace.jokulid.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdapterCustomer;
import com.inspiraspace.jokulid.model.customers.ResponseItem;
import com.inspiraspace.jokulid.network.main.PulseCustomer;
import com.inspiraspace.jokulid.presenter.GeneratorCustomers;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomersFragment extends Fragment implements PulseCustomer {
    @BindView(R.id.rv_frg_customers)
    RecyclerView rv_frg_customers;

    Unbinder unbinder;
    GeneratorCustomers generatorCustomers;
    AdapterCustomer adapterCustomer;
    List<ResponseItem> responseItemList = new ArrayList<>();
    Context mContext;

    public CustomersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customers, container, false);
        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, v);
        mContext = v.getContext();
        generatorCustomers = new GeneratorCustomers(this);
        generatorCustomers.getCustomers();
        adapterCustomer = new AdapterCustomer(mContext, responseItemList);
        rv_frg_customers.setLayoutManager(new LinearLayoutManager(mContext));
        rv_frg_customers.setAdapter(adapterCustomer);
        return v;
    }

    @Override
    public void OnSuccesGetCustomers(List<ResponseItem> responseItems) {
        adapterCustomer.swapRefresh(responseItems);
    }

    @Override
    public void OnErrorGetCustomers(String errmsg) {

    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
