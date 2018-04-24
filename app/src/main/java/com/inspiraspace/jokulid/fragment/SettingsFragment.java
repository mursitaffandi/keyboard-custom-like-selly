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
import com.inspiraspace.jokulid.subactivities.DefaultShippmentOriginActivity;
import com.inspiraspace.jokulid.subactivities.DefaultShippmentWeightActivity;
import com.inspiraspace.jokulid.subactivities.DetailShopActivity;
import com.inspiraspace.jokulid.subactivities.DetailUserActivity;
import com.inspiraspace.jokulid.subactivities.PaymentActivity;
import com.inspiraspace.jokulid.subactivities.ScheduleDailyreminderActivity;
import com.inspiraspace.jokulid.subactivities.ShippmentCompanysericeActivity;
import com.inspiraspace.jokulid.subactivities.TemplateActivity;
import com.inspiraspace.jokulid.utils.Constant;

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

   /* @BindView(R.id.btn_main_setting_logout)
    Button btn_main_setting_logout;*/

    private Intent intent = null;
    private Class<?> cls = null;
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
        /*btn_main_setting_logout.setOnClickListener(this);*/

        return view;
    }

    @Override
    public void onClick(View v) {
        intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_main_setting_detailshop:
                cls = DetailShopActivity.class;
                break;
            case R.id.btn_main_setting_detailuser:
                cls = DetailUserActivity.class;
                break;
            case R.id.btn_main_setting_payment:
                cls = PaymentActivity.class;
                break;
            case R.id.btn_main_setting_tempalte_afterpaid:
                intent.putExtra(Constant.KEY_INTENT_TEMPALTE, Constant.KEY_TEMPLATE_AFTERPAID);
            case R.id.btn_main_setting_tempalte_remaindtopay:
                intent.putExtra(Constant.KEY_INTENT_TEMPALTE, Constant.KEY_TEMPLATE_REMINDERTOPAY);
            case R.id.btn_main_setting_tempalte_sendresi:
                intent.putExtra(Constant.KEY_INTENT_TEMPALTE, Constant.KEY_TEMPLATE_SENDRESI);
                cls = TemplateActivity.class;
                break;
            case R.id.btn_main_setting_tempalte_autotext:
                cls = AutoTextActivity.class;
                break;
            case R.id.btn_main_setting_shippment_origin:
                cls = DefaultShippmentOriginActivity.class;
                break;
            case R.id.btn_main_setting_shippment_companyservice:
                cls = ShippmentCompanysericeActivity.class;
                break;
            case R.id.btn_main_setting_shippment_weigth:
                cls = DefaultShippmentWeightActivity.class;
                break;
            case R.id.btn_main_setting_dailyreminder:
                cls = ScheduleDailyreminderActivity.class;
                break;
            /*case R.id.btn_main_setting_logout:
                break;*/
            default:
                break;
        }
        intent.setClass(activity,cls);
        if (cls != null)
            startActivity(intent);
        else return;
    }
}
