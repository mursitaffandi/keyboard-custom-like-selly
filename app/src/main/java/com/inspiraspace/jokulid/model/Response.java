
package com.inspiraspace.jokulid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Response implements Parcelable
{

    @SerializedName("transaction_userby_user_id")
    @Expose
    private String transactionUserbyUserId;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("transaction_tr_addr_destination")
    @Expose
    private String transactionTrAddrDestination;
    @SerializedName("transaction_note")
    @Expose
    private Object transactionNote;
    @SerializedName("transaction_status")
    @Expose
    private String transactionStatus;
    @SerializedName("bank_account_bank_account_number")
    @Expose
    private String bankAccountBankAccountNumber;
    @SerializedName("bank_account_bank_account_name")
    @Expose
    private String bankAccountBankAccountName;
    @SerializedName("bank_account_bank_user_id")
    @Expose
    private String bankAccountBankUserId;
    @SerializedName("bank_bank_name")
    @Expose
    private String bankBankName;
    @SerializedName("shipment_weight")
    @Expose
    private String shipmentWeight;
    @SerializedName("shipment_addr_from")
    @Expose
    private String shipmentAddrFrom;
    @SerializedName("shipment_addr_destination")
    @Expose
    private String shipmentAddrDestination;
    @SerializedName("shipment_dayeta")
    @Expose
    private String shipmentDayeta;
    @SerializedName("shipment_resi")
    @Expose
    private Object shipmentResi;
    @SerializedName("shipment_ongkir")
    @Expose
    private String shipmentOngkir;
    @SerializedName("courier_company")
    @Expose
    private String courierCompany;
    @SerializedName("courier_flovour")
    @Expose
    private String courierFlovour;
    @SerializedName("customer_customer_name")
    @Expose
    private String customerCustomerName;
    @SerializedName("customer_customer_nohp")
    @Expose
    private String customerCustomerNohp;
    @SerializedName("customer_customer_totalspent")
    @Expose
    private Object customerCustomerTotalspent;
    @SerializedName("customer_customer_createdat")
    @Expose
    private String customerCustomerCreatedat;
    @SerializedName("customer_customer_addr")
    @Expose
    private Object customerCustomerAddr;
    @SerializedName("chatapp_name")
    @Expose
    private String chatappName;
    @SerializedName("toko_toko_name")
    @Expose
    private String tokoTokoName;
    @SerializedName("toko_toko_no_hp")
    @Expose
    private String tokoTokoNoHp;
    @SerializedName("toko_toko_url")
    @Expose
    private Object tokoTokoUrl;
    @SerializedName("total")
    @Expose
    private String total;
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
        this.transactionUserbyUserId = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionTrAddrDestination = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionNote = ((Object) in.readValue((Object.class.getClassLoader())));
        this.transactionStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountBankAccountNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountBankAccountName = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountBankUserId = ((String) in.readValue((String.class.getClassLoader())));
        this.bankBankName = ((String) in.readValue((String.class.getClassLoader())));
        this.shipmentWeight = ((String) in.readValue((String.class.getClassLoader())));
        this.shipmentAddrFrom = ((String) in.readValue((String.class.getClassLoader())));
        this.shipmentAddrDestination = ((String) in.readValue((String.class.getClassLoader())));
        this.shipmentDayeta = ((String) in.readValue((String.class.getClassLoader())));
        this.shipmentResi = ((Object) in.readValue((Object.class.getClassLoader())));
        this.shipmentOngkir = ((String) in.readValue((String.class.getClassLoader())));
        this.courierCompany = ((String) in.readValue((String.class.getClassLoader())));
        this.courierFlovour = ((String) in.readValue((String.class.getClassLoader())));
        this.customerCustomerName = ((String) in.readValue((String.class.getClassLoader())));
        this.customerCustomerNohp = ((String) in.readValue((String.class.getClassLoader())));
        this.customerCustomerTotalspent = ((Object) in.readValue((Object.class.getClassLoader())));
        this.customerCustomerCreatedat = ((String) in.readValue((String.class.getClassLoader())));
        this.customerCustomerAddr = ((Object) in.readValue((Object.class.getClassLoader())));
        this.chatappName = ((String) in.readValue((String.class.getClassLoader())));
        this.tokoTokoName = ((String) in.readValue((String.class.getClassLoader())));
        this.tokoTokoNoHp = ((String) in.readValue((String.class.getClassLoader())));
        this.tokoTokoUrl = ((Object) in.readValue((Object.class.getClassLoader())));
        this.total = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Response() {
    }

    /**
     *
     * @param total
     * @param courierFlovour
     * @param bankBankName
     * @param customerCustomerAddr
     * @param transactionId
     * @param transactionUserbyUserId
     * @param shipmentWeight
     * @param shipmentOngkir
     * @param tokoTokoUrl
     * @param customerCustomerCreatedat
     * @param bankAccountBankAccountNumber
     * @param shipmentResi
     * @param bankAccountBankUserId
     * @param customerCustomerName
     * @param shipmentDayeta
     * @param transactionStatus
     * @param shipmentAddrFrom
     * @param tokoTokoName
     * @param bankAccountBankAccountName
     * @param chatappName
     * @param transactionNote
     * @param customerCustomerNohp
     * @param shipmentAddrDestination
     * @param transactionTrAddrDestination
     * @param courierCompany
     * @param customerCustomerTotalspent
     * @param tokoTokoNoHp
     */
    public Response(String transactionUserbyUserId, String transactionId, String transactionTrAddrDestination, Object transactionNote, String transactionStatus, String bankAccountBankAccountNumber, String bankAccountBankAccountName, String bankAccountBankUserId, String bankBankName, String shipmentWeight, String shipmentAddrFrom, String shipmentAddrDestination, String shipmentDayeta, Object shipmentResi, String shipmentOngkir, String courierCompany, String courierFlovour, String customerCustomerName, String customerCustomerNohp, Object customerCustomerTotalspent, String customerCustomerCreatedat, Object customerCustomerAddr, String chatappName, String tokoTokoName, String tokoTokoNoHp, Object tokoTokoUrl, String total) {
        super();
        this.transactionUserbyUserId = transactionUserbyUserId;
        this.transactionId = transactionId;
        this.transactionTrAddrDestination = transactionTrAddrDestination;
        this.transactionNote = transactionNote;
        this.transactionStatus = transactionStatus;
        this.bankAccountBankAccountNumber = bankAccountBankAccountNumber;
        this.bankAccountBankAccountName = bankAccountBankAccountName;
        this.bankAccountBankUserId = bankAccountBankUserId;
        this.bankBankName = bankBankName;
        this.shipmentWeight = shipmentWeight;
        this.shipmentAddrFrom = shipmentAddrFrom;
        this.shipmentAddrDestination = shipmentAddrDestination;
        this.shipmentDayeta = shipmentDayeta;
        this.shipmentResi = shipmentResi;
        this.shipmentOngkir = shipmentOngkir;
        this.courierCompany = courierCompany;
        this.courierFlovour = courierFlovour;
        this.customerCustomerName = customerCustomerName;
        this.customerCustomerNohp = customerCustomerNohp;
        this.customerCustomerTotalspent = customerCustomerTotalspent;
        this.customerCustomerCreatedat = customerCustomerCreatedat;
        this.customerCustomerAddr = customerCustomerAddr;
        this.chatappName = chatappName;
        this.tokoTokoName = tokoTokoName;
        this.tokoTokoNoHp = tokoTokoNoHp;
        this.tokoTokoUrl = tokoTokoUrl;
        this.total = total;
    }

    public String getTransactionUserbyUserId() {
        return transactionUserbyUserId;
    }

    public void setTransactionUserbyUserId(String transactionUserbyUserId) {
        this.transactionUserbyUserId = transactionUserbyUserId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionTrAddrDestination() {
        return transactionTrAddrDestination;
    }

    public void setTransactionTrAddrDestination(String transactionTrAddrDestination) {
        this.transactionTrAddrDestination = transactionTrAddrDestination;
    }

    public Object getTransactionNote() {
        return transactionNote;
    }

    public void setTransactionNote(Object transactionNote) {
        this.transactionNote = transactionNote;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getBankAccountBankAccountNumber() {
        return bankAccountBankAccountNumber;
    }

    public void setBankAccountBankAccountNumber(String bankAccountBankAccountNumber) {
        this.bankAccountBankAccountNumber = bankAccountBankAccountNumber;
    }

    public String getBankAccountBankAccountName() {
        return bankAccountBankAccountName;
    }

    public void setBankAccountBankAccountName(String bankAccountBankAccountName) {
        this.bankAccountBankAccountName = bankAccountBankAccountName;
    }

    public String getBankAccountBankUserId() {
        return bankAccountBankUserId;
    }

    public void setBankAccountBankUserId(String bankAccountBankUserId) {
        this.bankAccountBankUserId = bankAccountBankUserId;
    }

    public String getBankBankName() {
        return bankBankName;
    }

    public void setBankBankName(String bankBankName) {
        this.bankBankName = bankBankName;
    }

    public String getShipmentWeight() {
        return shipmentWeight;
    }

    public void setShipmentWeight(String shipmentWeight) {
        this.shipmentWeight = shipmentWeight;
    }

    public String getShipmentAddrFrom() {
        return shipmentAddrFrom;
    }

    public void setShipmentAddrFrom(String shipmentAddrFrom) {
        this.shipmentAddrFrom = shipmentAddrFrom;
    }

    public String getShipmentAddrDestination() {
        return shipmentAddrDestination;
    }

    public void setShipmentAddrDestination(String shipmentAddrDestination) {
        this.shipmentAddrDestination = shipmentAddrDestination;
    }

    public String getShipmentDayeta() {
        return shipmentDayeta;
    }

    public void setShipmentDayeta(String shipmentDayeta) {
        this.shipmentDayeta = shipmentDayeta;
    }

    public Object getShipmentResi() {
        return shipmentResi;
    }

    public void setShipmentResi(Object shipmentResi) {
        this.shipmentResi = shipmentResi;
    }

    public String getShipmentOngkir() {
        return shipmentOngkir;
    }

    public void setShipmentOngkir(String shipmentOngkir) {
        this.shipmentOngkir = shipmentOngkir;
    }

    public String getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(String courierCompany) {
        this.courierCompany = courierCompany;
    }

    public String getCourierFlovour() {
        return courierFlovour;
    }

    public void setCourierFlovour(String courierFlovour) {
        this.courierFlovour = courierFlovour;
    }

    public String getCustomerCustomerName() {
        return customerCustomerName;
    }

    public void setCustomerCustomerName(String customerCustomerName) {
        this.customerCustomerName = customerCustomerName;
    }

    public String getCustomerCustomerNohp() {
        return customerCustomerNohp;
    }

    public void setCustomerCustomerNohp(String customerCustomerNohp) {
        this.customerCustomerNohp = customerCustomerNohp;
    }

    public Object getCustomerCustomerTotalspent() {
        return customerCustomerTotalspent;
    }

    public void setCustomerCustomerTotalspent(Object customerCustomerTotalspent) {
        this.customerCustomerTotalspent = customerCustomerTotalspent;
    }

    public String getCustomerCustomerCreatedat() {
        return customerCustomerCreatedat;
    }

    public void setCustomerCustomerCreatedat(String customerCustomerCreatedat) {
        this.customerCustomerCreatedat = customerCustomerCreatedat;
    }

    public Object getCustomerCustomerAddr() {
        return customerCustomerAddr;
    }

    public void setCustomerCustomerAddr(Object customerCustomerAddr) {
        this.customerCustomerAddr = customerCustomerAddr;
    }

    public String getChatappName() {
        return chatappName;
    }

    public void setChatappName(String chatappName) {
        this.chatappName = chatappName;
    }

    public String getTokoTokoName() {
        return tokoTokoName;
    }

    public void setTokoTokoName(String tokoTokoName) {
        this.tokoTokoName = tokoTokoName;
    }

    public String getTokoTokoNoHp() {
        return tokoTokoNoHp;
    }

    public void setTokoTokoNoHp(String tokoTokoNoHp) {
        this.tokoTokoNoHp = tokoTokoNoHp;
    }

    public Object getTokoTokoUrl() {
        return tokoTokoUrl;
    }

    public void setTokoTokoUrl(Object tokoTokoUrl) {
        this.tokoTokoUrl = tokoTokoUrl;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(transactionUserbyUserId);
        dest.writeValue(transactionId);
        dest.writeValue(transactionTrAddrDestination);
        dest.writeValue(transactionNote);
        dest.writeValue(transactionStatus);
        dest.writeValue(bankAccountBankAccountNumber);
        dest.writeValue(bankAccountBankAccountName);
        dest.writeValue(bankAccountBankUserId);
        dest.writeValue(bankBankName);
        dest.writeValue(shipmentWeight);
        dest.writeValue(shipmentAddrFrom);
        dest.writeValue(shipmentAddrDestination);
        dest.writeValue(shipmentDayeta);
        dest.writeValue(shipmentResi);
        dest.writeValue(shipmentOngkir);
        dest.writeValue(courierCompany);
        dest.writeValue(courierFlovour);
        dest.writeValue(customerCustomerName);
        dest.writeValue(customerCustomerNohp);
        dest.writeValue(customerCustomerTotalspent);
        dest.writeValue(customerCustomerCreatedat);
        dest.writeValue(customerCustomerAddr);
        dest.writeValue(chatappName);
        dest.writeValue(tokoTokoName);
        dest.writeValue(tokoTokoNoHp);
        dest.writeValue(tokoTokoUrl);
        dest.writeValue(total);
    }

    public int describeContents() {
        return 0;
    }

}