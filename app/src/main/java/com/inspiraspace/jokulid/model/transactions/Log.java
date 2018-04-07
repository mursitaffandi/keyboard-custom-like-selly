
package com.inspiraspace.jokulid.model.transactions;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Log implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("createdat")
    @Expose
    private String createdat;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Parcelable.Creator<Log> CREATOR = new Creator<Log>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Log createFromParcel(Parcel in) {
            return new Log(in);
        }

        public Log[] newArray(int size) {
            return (new Log[size]);
        }

    }
    ;

    protected Log(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.createdat = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Log() {
    }

    /**
     * 
     * @param id
     * @param transactionId
     * @param description
     * @param createdat
     */
    public Log(String id, String transactionId, String createdat, String description) {
        super();
        this.id = id;
        this.transactionId = transactionId;
        this.createdat = createdat;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(transactionId);
        dest.writeValue(createdat);
        dest.writeValue(description);
    }

    public int describeContents() {
        return  0;
    }

}
