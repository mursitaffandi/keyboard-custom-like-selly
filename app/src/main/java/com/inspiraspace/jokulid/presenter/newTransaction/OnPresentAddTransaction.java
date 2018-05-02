package com.inspiraspace.jokulid.presenter.newTransaction;

import android.widget.Spinner;

import com.inspiraspace.jokulid.model.preaddtransaction.Chatapp;
import com.inspiraspace.jokulid.model.preaddtransaction.Payment;

import java.util.ArrayList;

/**
 * Created by mursitaffandi on 5/1/18.
 */

public interface OnPresentAddTransaction {
    void OnAddTransaction(String strsend_customername, String strsend_customernohp, String strsend_customeraddress, String strsend_transactionnote, String strsend_transactionongkir, String strsend_id_bankaccount, String strsend_id_chatapp, String strsend_item_qty, String strsend_item_name, String strsend_item_price);
}
