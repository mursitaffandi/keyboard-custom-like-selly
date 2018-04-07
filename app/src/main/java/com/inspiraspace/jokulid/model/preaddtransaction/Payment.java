
package com.inspiraspace.jokulid.model.preaddtransaction;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Payment {

    @SerializedName("bank_account_bank_id")
    private String mBankAccountBankId;
    @SerializedName("bank_account_id")
    private String mBankAccountId;
    @SerializedName("bank_account_name")
    private String mBankAccountName;
    @SerializedName("bank_account_number")
    private String mBankAccountNumber;
    @SerializedName("bank_admin_id")
    private Object mBankAdminId;
    @SerializedName("bank_customer_id")
    private Object mBankCustomerId;
    @SerializedName("bank_id")
    private String mBankId;
    @SerializedName("bank_image")
    private String mBankImage;
    @SerializedName("bank_midtrans_link")
    private Object mBankMidtransLink;
    @SerializedName("bank_name")
    private String mBankName;
    @SerializedName("bank_user_id")
    private String mBankUserId;
    @SerializedName("createdat")
    private String mCreatedat;
    @SerializedName("link")
    private Object mLink;

    public String getBankAccountBankId() {
        return mBankAccountBankId;
    }

    public void setBankAccountBankId(String bankAccountBankId) {
        mBankAccountBankId = bankAccountBankId;
    }

    public String getBankAccountId() {
        return mBankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        mBankAccountId = bankAccountId;
    }

    public String getBankAccountName() {
        return mBankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        mBankAccountName = bankAccountName;
    }

    public String getBankAccountNumber() {
        return mBankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        mBankAccountNumber = bankAccountNumber;
    }

    public Object getBankAdminId() {
        return mBankAdminId;
    }

    public void setBankAdminId(Object bankAdminId) {
        mBankAdminId = bankAdminId;
    }

    public Object getBankCustomerId() {
        return mBankCustomerId;
    }

    public void setBankCustomerId(Object bankCustomerId) {
        mBankCustomerId = bankCustomerId;
    }

    public String getBankId() {
        return mBankId;
    }

    public void setBankId(String bankId) {
        mBankId = bankId;
    }

    public String getBankImage() {
        return mBankImage;
    }

    public void setBankImage(String bankImage) {
        mBankImage = bankImage;
    }

    public Object getBankMidtransLink() {
        return mBankMidtransLink;
    }

    public void setBankMidtransLink(Object bankMidtransLink) {
        mBankMidtransLink = bankMidtransLink;
    }

    public String getBankName() {
        return mBankName;
    }

    public void setBankName(String bankName) {
        mBankName = bankName;
    }

    public String getBankUserId() {
        return mBankUserId;
    }

    public void setBankUserId(String bankUserId) {
        mBankUserId = bankUserId;
    }

    public String getCreatedat() {
        return mCreatedat;
    }

    public void setCreatedat(String createdat) {
        mCreatedat = createdat;
    }

    public Object getLink() {
        return mLink;
    }

    public void setLink(Object link) {
        mLink = link;
    }

}
