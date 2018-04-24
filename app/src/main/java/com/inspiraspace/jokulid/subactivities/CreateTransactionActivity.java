package com.inspiraspace.jokulid.subactivities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.adapter.AdpLVItemTransaction;
import com.inspiraspace.jokulid.adapter.AdpLVLogTransaction;
import com.inspiraspace.jokulid.adapter.AdpSpinnerChatapp;
import com.inspiraspace.jokulid.adapter.AdpSpinnerPayment;
import com.inspiraspace.jokulid.model.preaddtransaction.Chatapp;
import com.inspiraspace.jokulid.model.preaddtransaction.Payment;
import com.inspiraspace.jokulid.model.preaddtransaction.Response;
import com.inspiraspace.jokulid.presenter.PresenterPreDetailTransaction;
import com.inspiraspace.jokulid.utils.Constant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CreateTransactionActivity extends AppCompatActivity implements PresenterPreDetailTransaction.PulsePreDetailTransaction{
    @BindView(R.id.edt_add_transaction_customername)
    TextInputEditText edt_add_transaction_customername;

    @BindView(R.id.edt_add_transaction_customernohp)
    TextInputEditText edt_add_transaction_customernohp;

    @BindView(R.id.edt_add_transaction_customeraddress)
    AutoCompleteTextView edt_add_transaction_customeraddress;

    @BindView(R.id.edt_add_transaction_transactionongkir)
    TextInputEditText edt_add_transaction_transactionongkir;

    @BindView(R.id.sp_add_transaction_bankaccount)
    Spinner sp_add_transaction_bankaccount;

    @BindView(R.id.sp_add_transaction_chatapp)
    Spinner sp_add_transaction_chatapp;

    @BindView(R.id.edt_add_transaction_item_qty)
    TextInputEditText edt_add_transaction_item_qty;

    @BindView(R.id.edt_add_transaction_item_name)
    TextInputEditText edt_add_transaction_item_name;

    @BindView(R.id.edt_add_transaction_item_price)
    TextInputEditText edt_add_transaction_item_price;

    @BindView(R.id.edt_add_transaction_transactionnote)
    TextInputEditText edt_add_transaction_transactionnote;

    Unbinder unbinder;

    PresenterPreDetailTransaction loaderChatappPayment;
    private ArrayList<Payment> dataPayments;
    private ArrayList<Chatapp> dataChatapps;
    private AdpSpinnerChatapp adpSpinnerChatapp;
    private AdpSpinnerPayment adpSpinnerPayment;

    String
            strsend_customername,
            strsend_customernohp,
            strsend_customeraddress,
            strsend_transactionnote,
            strsend_transactionongkir,
            strsend_id_bankaccount,
            strsend_id_chatapp,
            strsend_item_qty,
            strsend_item_name,
            strsend_item_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcdt_createinvoice);

        unbinder = ButterKnife.bind(this);
        sp_add_transaction_chatapp = findViewById(R.id.sp_add_transaction_chatapp);
        sp_add_transaction_bankaccount = findViewById(R.id.sp_add_transaction_bankaccount);

        loaderChatappPayment = new PresenterPreDetailTransaction(this);

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onSuccesPayment(Response payments) {
        dataPayments = payments.getArrPayment();
        dataChatapps = payments.getArrChatapp();

        setupViewFromIntent();
    }

    @Override
    public void onFailure(String message) {

    }

    private void setupViewFromIntent() {
        adpSpinnerChatapp = new AdpSpinnerChatapp(this, R.id.tv_item_chatapp, dataChatapps);
        sp_add_transaction_chatapp.setAdapter(adpSpinnerChatapp);

        adpSpinnerPayment = new AdpSpinnerPayment(this, R.id.tv_item_payment, dataPayments);
        sp_add_transaction_bankaccount.setAdapter(adpSpinnerPayment);
    }

    @OnClick(R.id.btn_add_transaction_done)
    public void submit() {
        strsend_customername =
                edt_add_transaction_customername.getText().toString();
        strsend_customernohp =
                edt_add_transaction_customernohp.getText().toString();
        strsend_customeraddress =
                edt_add_transaction_customeraddress.getText().toString();
        strsend_transactionnote =
                edt_add_transaction_transactionnote.getText().toString();
        strsend_transactionongkir =
                edt_add_transaction_transactionongkir.getText().toString();
        strsend_id_bankaccount =
                dataPayments.get(sp_add_transaction_bankaccount.getSelectedItemPosition()).getBankAccountBankId();
        strsend_id_chatapp =
                dataChatapps.get(sp_add_transaction_chatapp.getSelectedItemPosition()).getId();
        strsend_item_qty =
                edt_add_transaction_item_qty.getText().toString();
        strsend_item_name =
                edt_add_transaction_item_name.getText().toString();
        strsend_item_price =
                edt_add_transaction_item_price.getText().toString();

    }
}
