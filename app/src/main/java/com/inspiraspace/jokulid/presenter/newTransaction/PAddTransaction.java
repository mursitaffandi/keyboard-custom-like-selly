package com.inspiraspace.jokulid.presenter.newTransaction;

import com.inspiraspace.jokulid.model.preaddtransaction.Response;
import com.inspiraspace.jokulid.network.main.PulsePostTransaction;
import com.inspiraspace.jokulid.presenter.GeneratorChatappPayment;
import com.inspiraspace.jokulid.presenter.PostTransaction;

/**
 * Created by mursitaffandi on 5/1/18.
 */

public class PAddTransaction implements OnPresentAddTransaction, GeneratorChatappPayment.PulsePreDetailTransaction, PulsePostTransaction {

    GeneratorChatappPayment loaderChatappPayment;
    PostTransaction postTransaction;

    private OnViewAddTransaction onViewAddTransaction;

    public PAddTransaction(OnViewAddTransaction onViewAddTransaction) {
        this.onViewAddTransaction = onViewAddTransaction;
        loaderChatappPayment = new GeneratorChatappPayment(this);
        postTransaction = new PostTransaction(this);
    }

    @Override
    public void OnSuccessAddTransaction() {
        onViewAddTransaction.OnSuccessAddTransaction();
    }

    @Override
    public void OnErrorAddTransaction(String errmsg) {
        onViewAddTransaction.OnErrorAddTransaction(errmsg);
    }


    @Override
    public void onSuccesPaymentChatapp(Response payments) {
        onViewAddTransaction.OnSuccessLoadPaymentChattapp(payments.getArrPayment(), payments.getArrChatapp());
    }

    @Override
    public void onFailurePaymentChatapp(String message) {

    }


    @Override
    public void OnAddTransaction(String strsend_customername, String strsend_customernohp, String strsend_customeraddress, String strsend_transactionnote, String strsend_transactionongkir, String strsend_id_bankaccount, String strsend_id_chatapp, String strsend_item_qty, String strsend_item_name, String strsend_item_price) {
        postTransaction.sendPostTransaction(strsend_customername, strsend_customernohp, strsend_customeraddress, strsend_transactionnote, strsend_transactionongkir,
                strsend_id_bankaccount, strsend_id_chatapp, strsend_item_qty, strsend_item_name, strsend_item_price);
    }
}
