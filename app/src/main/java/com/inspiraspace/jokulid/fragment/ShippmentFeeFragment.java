package com.inspiraspace.jokulid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.model.rajaongkir.Result;
import com.inspiraspace.jokulid.network.ongkir.PulseOngkir;
import com.inspiraspace.jokulid.presenter.GeneratorOngkir;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShippmentFeeFragment extends Fragment implements PulseOngkir {
GeneratorOngkir generatorOngkir;

    public ShippmentFeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shippment_fee, container, false);
        generatorOngkir = new GeneratorOngkir(this);
        generatorOngkir.getOngkir(
                "1145",
                "subdistrict",
                "2785",
                "subdistrict",
                "1000",
                "jne"
        );
        return view;
    }

    @Override
    public void onSuccessGetOngkir(List<Result> resultOngkir) {
        System.out.println("resultOngkir "+ resultOngkir.get(0).getCosts().get(0).getService());
    }

    @Override
    public void onFailOccureTransactions(String errmsg) {
        System.out.println("error ongkir"+errmsg);
    }
}
