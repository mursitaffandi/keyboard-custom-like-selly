package com.inspiraspace.jokulid.presenter.newTransaction;


import android.widget.Spinner;

import com.inspiraspace.jokulid.model.preaddtransaction.Chatapp;
import com.inspiraspace.jokulid.model.preaddtransaction.Payment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mursitaffandi on 5/1/18.
 */

public interface OnViewAddTransaction {
    void OnSuccessLoadPaymentChattapp(ArrayList<Payment> dataPayments, ArrayList<Chatapp> dataChatapps);
    void OnSuccessAddTransaction();
    void OnErrorAddTransaction(String message);
}
