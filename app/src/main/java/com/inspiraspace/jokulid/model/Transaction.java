
package com.inspiraspace.jokulid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Transaction implements Parcelable
{

    @SerializedName("response")
    @Expose
    private List<Response> response = new ArrayList<Response>();
    public final static Parcelable.Creator<Transaction> CREATOR = new Creator<Transaction>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        public Transaction[] newArray(int size) {
            return (new Transaction[size]);
        }

    }
            ;

    protected Transaction(Parcel in) {
        in.readList(this.response, (Response.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Transaction() {
    }

    /**
     *
     * @param response
     */
    public Transaction(List<Response> response) {
        super();
        this.response = response;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(response);
    }

    public int describeContents() {
        return 0;
    }

}