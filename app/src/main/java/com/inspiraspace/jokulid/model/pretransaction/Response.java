
package com.inspiraspace.jokulid.model.pretransaction;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response implements Parcelable
{

    @SerializedName("chatapp")
    @Expose
    private List<Chatapp> chatapp = new ArrayList<Chatapp>();
    @SerializedName("payment")
    @Expose
    private List<Payment> payment = new ArrayList<Payment>();
    public final static Parcelable.Creator<Response> CREATOR = new Creator<Response>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        public Response[] newArray(int size) {
            return (new Response[size]);
        }

    }
    ;

    protected Response(Parcel in) {
        in.readList(this.chatapp, (Chatapp.class.getClassLoader()));
        in.readList(this.payment, (Payment.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Response() {
    }

    /**
     * 
     * @param payment
     * @param chatapp
     */
    public Response(List<Chatapp> chatapp, List<Payment> payment) {
        super();
        this.chatapp = chatapp;
        this.payment = payment;
    }

    public List<Chatapp> getChatapp() {
        return chatapp;
    }

    public void setChatapp(List<Chatapp> chatapp) {
        this.chatapp = chatapp;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(chatapp);
        dest.writeList(payment);
    }

    public int describeContents() {
        return  0;
    }

}
