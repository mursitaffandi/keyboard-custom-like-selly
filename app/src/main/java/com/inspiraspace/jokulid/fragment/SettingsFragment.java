package com.inspiraspace.jokulid.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.subactivities.AutoTextActivity;
import com.inspiraspace.jokulid.subactivities.TemplateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.btn_main_setting_detailshop)
    Button btn_main_setting_detailshop;

    @BindView(R.id.btn_main_setting_detailuser)
    Button btn_main_setting_detailuser;

    @BindView(R.id.btn_main_setting_payment)
    Button btn_main_setting_payment;

    @BindView(R.id.btn_main_setting_tempalte_afterpaid)
    Button btn_main_setting_tempalte_afterpaid;

    @BindView(R.id.btn_main_setting_tempalte_remaindtopay)
    Button btn_main_setting_tempalte_remaindtopay;

    @BindView(R.id.btn_main_setting_tempalte_sendresi)
    Button btn_main_setting_tempalte_sendresi;

    @BindView(R.id.btn_main_setting_tempalte_autotext)
    Button btn_main_setting_tempalte_autotext;

    @BindView(R.id.btn_main_setting_shippment_origin)
    Button btn_main_setting_shippment_origin;

    @BindView(R.id.btn_main_setting_shippment_companyservice)
    Button btn_main_setting_shippment_companyservice;

    @BindView(R.id.btn_main_setting_shippment_weigth)
    Button btn_main_setting_shippment_weigth;

    @BindView(R.id.btn_main_setting_dailyreminder)
    Button btn_main_setting_dailyreminder;

    @BindView(R.id.btn_main_setting_logout)
    Button btn_main_setting_logout;

    private Intent intent;
    private Context activity;
    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        activity = getActivity();
        btn_main_setting_detailshop.setOnClickListener(this);
        btn_main_setting_detailuser.setOnClickListener(this);
        btn_main_setting_payment.setOnClickListener(this);
        btn_main_setting_tempalte_afterpaid.setOnClickListener(this);
        btn_main_setting_tempalte_remaindtopay.setOnClickListener(this);
        btn_main_setting_tempalte_sendresi.setOnClickListener(this);
        btn_main_setting_tempalte_autotext.setOnClickListener(this);
        btn_main_setting_shippment_origin.setOnClickListener(this);
        btn_main_setting_shippment_companyservice.setOnClickListener(this);
        btn_main_setting_shippment_weigth.setOnClickListener(this);
        btn_main_setting_dailyreminder.setOnClickListener(this);
        btn_main_setting_logout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        intent = null;
        switch (v.getId()){
            case R.id.btn_main_setting_detailshop :

                break;
            case R.id.btn_main_setting_detailuser :

                break;
            case R.id.btn_main_setting_payment :

                break;
            case R.id.btn_main_setting_tempalte_afterpaid :
            case R.id.btn_main_setting_tempalte_remaindtopay :
            case R.id.btn_main_setting_tempalte_sendresi :
                intent =  new Intent(activity, TemplateActivity.class);
                break;
            case R.id.btn_main_setting_tempalte_autotext :
                intent =  new Intent(activity, AutoTextActivity.class);

                break;
            case R.id.btn_main_setting_shippment_origin :

                break;
            case R.id.btn_main_setting_shippment_companyservice :

                break;
            case R.id.btn_main_setting_shippment_weigth :

                break;
            case R.id.btn_main_setting_dailyreminder :

                break;
            case R.id.btn_main_setting_logout :

                break;

                default:
                    break;
        }
        if (intent!=null)
        startActivity(intent);
        else return;

    }
}
