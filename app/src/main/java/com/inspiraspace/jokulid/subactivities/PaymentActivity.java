package com.inspiraspace.jokulid.subactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdpSettingPayment;
import com.inspiraspace.jokulid.model.preaddtransaction.Payment;
import com.inspiraspace.jokulid.model.preaddtransaction.Response;
import com.inspiraspace.jokulid.presenter.GeneratorChatappPayment;
import com.inspiraspace.jokulid.utils.BaseAuthActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentActivity extends BaseAuthActivity implements GeneratorChatappPayment.PulsePreDetailTransaction {
    @BindView(R.id.lv_payment)
    ListView lv_payment;

    @OnClick(R.id.fab_add_payment)
    public void addPayment() {
        startActivity(new Intent(this, AddPaymentActivity.class));
    }

    ArrayList<Payment> paymentsList = new ArrayList<>();
    AdpSettingPayment adpSpinnerPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        adpSpinnerPayment = new AdpSettingPayment(this);
        lv_payment.setAdapter(adpSpinnerPayment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GeneratorChatappPayment(this);

    }

    @Override
    public void onSuccesPaymentChatapp(Response payments) {
        paymentsList = payments.getArrPayment();
        adpSpinnerPayment.swapRefresh(paymentsList);
    }

    @Override
    public void onFailurePaymentChatapp(String message) {

    }
}
