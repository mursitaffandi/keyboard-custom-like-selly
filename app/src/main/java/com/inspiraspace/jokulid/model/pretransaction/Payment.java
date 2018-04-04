
package com.inspiraspace.jokulid.model.pretransaction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment implements Parcelable
{

    @SerializedName("bank_account_id")
    @Expose
    private String bankAccountId;
    @SerializedName("bank_account_bank_id")
    @Expose
    private String bankAccountBankId;
    @SerializedName("bank_account_number")
    @Expose
    private Object bankAccountNumber;
    @SerializedName("bank_account_name")
    @Expose
    private Object bankAccountName;
    @SerializedName("bank_midtrans_link")
    @Expose
    private Object bankMidtransLink;
    @SerializedName("bank_customer_id")
    @Expose
    private Object bankCustomerId;
    @SerializedName("bank_user_id")
    @Expose
    private String bankUserId;
    @SerializedName("bank_admin_id")
    @Expose
    private Object bankAdminId;
    @SerializedName("createdat")
    @Expose
    private String createdat;
    @SerializedName("bank_id")
    @Expose
    private String bankId;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("link")
    @Expose
    private Object link;
    public final static Parcelable.Creator<Payment> CREATOR = new Creator<Payment>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        public Payment[] newArray(int size) {
            return (new Payment[size]);
        }

    }
    ;

    protected Payment(Parcel in) {
        this.bankAccountId = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountBankId = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountNumber = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bankAccountName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bankMidtransLink = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bankCustomerId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bankUserId = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAdminId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.createdat = ((String) in.readValue((String.class.getClassLoader())));
        this.bankId = ((String) in.readValue((String.class.getClassLoader())));
        this.bankName = ((String) in.readValue((String.class.getClassLoader())));
        this.link = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Payment() {
    }

    /**
     * 
     * @param bankUserId
     * @param bankCustomerId
     * @param bankMidtransLink
     * @param link
     * @param bankName
     * @param bankAdminId
     * @param bankAccountNumber
     * @param bankAccountBankId
     * @param createdat
     * @param bankAccountId
     * @param bankId
     * @param bankAccountName
     */
    public Payment(String bankAccountId, String bankAccountBankId, Object bankAccountNumber, Object bankAccountName, Object bankMidtransLink, Object bankCustomerId, String bankUserId, Object bankAdminId, String createdat, String bankId, String bankName, Object link) {
        super();
        this.bankAccountId = bankAccountId;
        this.bankAccountBankId = bankAccountBankId;
        this.bankAccountNumber = bankAccountNumber;
        this.bankAccountName = bankAccountName;
        this.bankMidtransLink = bankMidtransLink;
        this.bankCustomerId = bankCustomerId;
        this.bankUserId = bankUserId;
        this.bankAdminId = bankAdminId;
        this.createdat = createdat;
        this.bankId = bankId;
        this.bankName = bankName;
        this.link = link;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankAccountBankId() {
        return bankAccountBankId;
    }

    public void setBankAccountBankId(String bankAccountBankId) {
        this.bankAccountBankId = bankAccountBankId;
    }

    public Object getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(Object bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Object getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(Object bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public Object getBankMidtransLink() {
        return bankMidtransLink;
    }

    public void setBankMidtransLink(Object bankMidtransLink) {
        this.bankMidtransLink = bankMidtransLink;
    }

    public Object getBankCustomerId() {
        return bankCustomerId;
    }

    public void setBankCustomerId(Object bankCustomerId) {
        this.bankCustomerId = bankCustomerId;
    }

    public String getBankUserId() {
        return bankUserId;
    }

    public void setBankUserId(String bankUserId) {
        this.bankUserId = bankUserId;
    }

    public Object getBankAdminId() {
        return bankAdminId;
    }

    public void setBankAdminId(Object bankAdminId) {
        this.bankAdminId = bankAdminId;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Object getLink() {
        return link;
    }

    public void setLink(Object link) {
        this.link = link;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(bankAccountId);
        dest.writeValue(bankAccountBankId);
        dest.writeValue(bankAccountNumber);
        dest.writeValue(bankAccountName);
        dest.writeValue(bankMidtransLink);
        dest.writeValue(bankCustomerId);
        dest.writeValue(bankUserId);
        dest.writeValue(bankAdminId);
        dest.writeValue(createdat);
        dest.writeValue(bankId);
        dest.writeValue(bankName);
        dest.writeValue(link);
    }

    public int describeContents() {
        return  0;
    }

}
