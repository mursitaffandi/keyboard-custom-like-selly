
package com.inspiraspace.jokulid.model.autotext;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Response {

    @SerializedName("content")
    private String mContent;
    @SerializedName("createdat")
    private String mCreatedat;
    @SerializedName("id")
    private String mId;
    @SerializedName("shortcut")
    private String mShortcut;
    @SerializedName("user_id")
    private String mUserId;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getCreatedat() {
        return mCreatedat;
    }

    public void setCreatedat(String createdat) {
        mCreatedat = createdat;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getShortcut() {
        return mShortcut;
    }

    public void setShortcut(String shortcut) {
        mShortcut = shortcut;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
