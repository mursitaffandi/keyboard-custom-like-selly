
package com.inspiraspace.jokulid.model.pretransaction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreMaketransaction implements Parcelable
{

    @SerializedName("response")
    @Expose
    private Response response;
    public final static Parcelable.Creator<PreMaketransaction> CREATOR = new Creator<PreMaketransaction>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PreMaketransaction createFromParcel(Parcel in) {
            return new PreMaketransaction(in);
        }

        public PreMaketransaction[] newArray(int size) {
            return (new PreMaketransaction[size]);
        }

    }
    ;

    protected PreMaketransaction(Parcel in) {
        this.response = ((Response) in.readValue((Response.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public PreMaketransaction() {
    }

    /**
     * 
     * @param response
     */
    public PreMaketransaction(Response response) {
        super();
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(response);
    }

    public int describeContents() {
        return  0;
    }

}
