
package com.inspiraspace.jokulid.model.preaddtransaction;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Chatapp {

    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("link")
    private String mLink;
    @SerializedName("name")
    private String mName;
    @SerializedName("nick")
    private String mNick;
    @SerializedName("package")
    private String mPackage;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNick() {
        return mNick;
    }

    public void setNick(String nick) {
        mNick = nick;
    }

    public String getPackage() {
        return mPackage;
    }

    public void setPackage(String vpackage) {
        mPackage = vpackage;
    }

}
