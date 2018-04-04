
package com.inspiraspace.jokulid.model.pretransaction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chatapp implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Parcelable.Creator<Chatapp> CREATOR = new Creator<Chatapp>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Chatapp createFromParcel(Parcel in) {
            return new Chatapp(in);
        }

        public Chatapp[] newArray(int size) {
            return (new Chatapp[size]);
        }

    }
    ;

    protected Chatapp(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Chatapp() {
    }

    /**
     * 
     * @param id
     * @param name
     */
    public Chatapp(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
