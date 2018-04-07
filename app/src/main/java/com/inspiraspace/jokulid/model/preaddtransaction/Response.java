
package com.inspiraspace.jokulid.model.preaddtransaction;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Response {

    @SerializedName("chatapp")
    private List<Chatapp> mChatapp;
    @SerializedName("payment")
    private List<Payment> mPayment;

    public List<Chatapp> getChatapp() {
        return mChatapp;
    }

    public void setChatapp(List<Chatapp> chatapp) {
        mChatapp = chatapp;
    }

    public List<Payment> getPayment() {
        return mPayment;
    }

    public void setPayment(List<Payment> payment) {
        mPayment = payment;
    }

}
