package com.inspiraspace.jokulid.model.transactions;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response implements Parcelable {

	@SerializedName("transaction_userby_user_id")
	private String transactionUserbyUserId;

	@SerializedName("bank_account_bank_user_id")
	private String bankAccountBankUserId;

	@SerializedName("courier_flovour")
	private String courierFlovour;

	@SerializedName("transaction_status")
	private String transactionStatus;

	@SerializedName("bank_account_bank_account_number")
	private String bankAccountBankAccountNumber;

	@SerializedName("toko_toko_no_hp")
	private String tokoTokoNoHp;

	@SerializedName("log")
	private List<Log> log;

	@SerializedName("customer_customer_name")
	private String customerCustomerName;

	@SerializedName("customer_customer_nohp")
	private String customerCustomerNohp;

	@SerializedName("chatapp_name")
	private String chatappName;

	@SerializedName("customer_customer_createdat")
	private String customerCustomerCreatedat;

	@SerializedName("customer_customer_addr")
	private String customerCustomerAddr;

	@SerializedName("courier_company")
	private String courierCompany;

	@SerializedName("total")
	private String total;

	@SerializedName("chatapp_nick")
	private String chatappNick;

	@SerializedName("transaction_tr_addr_destination")
	private String transactionTrAddrDestination;

	@SerializedName("shipment_weight")
	private String shipmentWeight;

	@SerializedName("chatapp_package")
	private String chatappPackage;

	@SerializedName("toko_toko_url")
	private String tokoTokoUrl;

	@SerializedName("bank_bank_name")
	private String bankBankName;

	@SerializedName("transaction_id")
	private String transactionId;

	@SerializedName("customer_customer_chatapp_id")
	private String customerCustomerChatappId;

	@SerializedName("item")
	private List<Item> item;

	@SerializedName("transaction_note")
	private String transactionNote;

	@SerializedName("shipment_addr_from")
	private String shipmentAddrFrom;

	@SerializedName("bank_bank_image")
	private String bankBankImage;

	@SerializedName("customer_customer_totalspent")
	private String customerCustomerTotalspent;

	@SerializedName("chatapp_image")
	private String chatappImage;

	@SerializedName("bank_account_bank_account_name")
	private String bankAccountBankAccountName;

	@SerializedName("shipment_addr_destination")
	private String shipmentAddrDestination;

	@SerializedName("shipment_resi")
	private String shipmentResi;

	@SerializedName("shipment_ongkir")
	private String shipmentOngkir;

	@SerializedName("shipment_dayeta")
	private String shipmentDayeta;

	@SerializedName("chatapp_link")
	private String chatappLink;

	@SerializedName("toko_toko_name")
	private String tokoTokoName;

	@SerializedName("customer_customer_id")
	private String customerCustomerId;

	public void setTransactionUserbyUserId(String transactionUserbyUserId){
		this.transactionUserbyUserId = transactionUserbyUserId;
	}

	public String getTransactionUserbyUserId(){
		return transactionUserbyUserId;
	}

	public void setBankAccountBankUserId(String bankAccountBankUserId){
		this.bankAccountBankUserId = bankAccountBankUserId;
	}

	public String getBankAccountBankUserId(){
		return bankAccountBankUserId;
	}

	public void setCourierFlovour(String courierFlovour){
		this.courierFlovour = courierFlovour;
	}

	public String getCourierFlovour(){
		return courierFlovour;
	}

	public void setTransactionStatus(String transactionStatus){
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionStatus(){
		return transactionStatus;
	}

	public void setBankAccountBankAccountNumber(String bankAccountBankAccountNumber){
		this.bankAccountBankAccountNumber = bankAccountBankAccountNumber;
	}

	public String getBankAccountBankAccountNumber(){
		return bankAccountBankAccountNumber;
	}

	public void setTokoTokoNoHp(String tokoTokoNoHp){
		this.tokoTokoNoHp = tokoTokoNoHp;
	}

	public String getTokoTokoNoHp(){
		return tokoTokoNoHp;
	}

	public void setLog(List<Log> log){
		this.log = log;
	}

	public List<Log> getLog(){
		return log;
	}

	public void setCustomerCustomerName(String customerCustomerName){
		this.customerCustomerName = customerCustomerName;
	}

	public String getCustomerCustomerName(){
		return customerCustomerName;
	}

	public void setCustomerCustomerNohp(String customerCustomerNohp){
		this.customerCustomerNohp = customerCustomerNohp;
	}

	public String getCustomerCustomerNohp(){
		return customerCustomerNohp;
	}

	public void setChatappName(String chatappName){
		this.chatappName = chatappName;
	}

	public String getChatappName(){
		return chatappName;
	}

	public void setCustomerCustomerCreatedat(String customerCustomerCreatedat){
		this.customerCustomerCreatedat = customerCustomerCreatedat;
	}

	public String getCustomerCustomerCreatedat(){
		return customerCustomerCreatedat;
	}

	public void setCustomerCustomerAddr(String customerCustomerAddr){
		this.customerCustomerAddr = customerCustomerAddr;
	}

	public String getCustomerCustomerAddr(){
		return customerCustomerAddr;
	}

	public void setCourierCompany(String courierCompany){
		this.courierCompany = courierCompany;
	}

	public String getCourierCompany(){
		return courierCompany;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setChatappNick(String chatappNick){
		this.chatappNick = chatappNick;
	}

	public String getChatappNick(){
		return chatappNick;
	}

	public void setTransactionTrAddrDestination(String transactionTrAddrDestination){
		this.transactionTrAddrDestination = transactionTrAddrDestination;
	}

	public String getTransactionTrAddrDestination(){
		return transactionTrAddrDestination;
	}

	public void setShipmentWeight(String shipmentWeight){
		this.shipmentWeight = shipmentWeight;
	}

	public String getShipmentWeight(){
		return shipmentWeight;
	}

	public void setChatappPackage(String chatappPackage){
		this.chatappPackage = chatappPackage;
	}

	public String getChatappPackage(){
		return chatappPackage;
	}

	public void setTokoTokoUrl(String tokoTokoUrl){
		this.tokoTokoUrl = tokoTokoUrl;
	}

	public String getTokoTokoUrl(){
		return tokoTokoUrl;
	}

	public void setBankBankName(String bankBankName){
		this.bankBankName = bankBankName;
	}

	public String getBankBankName(){
		return bankBankName;
	}

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return transactionId;
	}

	public void setCustomerCustomerChatappId(String customerCustomerChatappId){
		this.customerCustomerChatappId = customerCustomerChatappId;
	}

	public String getCustomerCustomerChatappId(){
		return customerCustomerChatappId;
	}

	public void setItem(List<Item> item){
		this.item = item;
	}

	public List<Item> getItem(){
		return item;
	}

	public void setTransactionNote(String transactionNote){
		this.transactionNote = transactionNote;
	}

	public String getTransactionNote(){
		return transactionNote;
	}

	public void setShipmentAddrFrom(String shipmentAddrFrom){
		this.shipmentAddrFrom = shipmentAddrFrom;
	}

	public String getShipmentAddrFrom(){
		return shipmentAddrFrom;
	}

	public void setBankBankImage(String bankBankImage){
		this.bankBankImage = bankBankImage;
	}

	public String getBankBankImage(){
		return bankBankImage;
	}

	public void setCustomerCustomerTotalspent(String customerCustomerTotalspent){
		this.customerCustomerTotalspent = customerCustomerTotalspent;
	}

	public String getCustomerCustomerTotalspent(){
		return customerCustomerTotalspent;
	}

	public void setChatappImage(String chatappImage){
		this.chatappImage = chatappImage;
	}

	public String getChatappImage(){
		return chatappImage;
	}

	public void setBankAccountBankAccountName(String bankAccountBankAccountName){
		this.bankAccountBankAccountName = bankAccountBankAccountName;
	}

	public String getBankAccountBankAccountName(){
		return bankAccountBankAccountName;
	}

	public void setShipmentAddrDestination(String shipmentAddrDestination){
		this.shipmentAddrDestination = shipmentAddrDestination;
	}

	public String getShipmentAddrDestination(){
		return shipmentAddrDestination;
	}

	public void setShipmentResi(String shipmentResi){
		this.shipmentResi = shipmentResi;
	}

	public String getShipmentResi(){
		return shipmentResi;
	}

	public void setShipmentOngkir(String shipmentOngkir){
		this.shipmentOngkir = shipmentOngkir;
	}

	public String getShipmentOngkir(){
		return shipmentOngkir;
	}

	public void setShipmentDayeta(String shipmentDayeta){
		this.shipmentDayeta = shipmentDayeta;
	}

	public String getShipmentDayeta(){
		return shipmentDayeta;
	}

	public void setChatappLink(String chatappLink){
		this.chatappLink = chatappLink;
	}

	public String getChatappLink(){
		return chatappLink;
	}

	public void setTokoTokoName(String tokoTokoName){
		this.tokoTokoName = tokoTokoName;
	}

	public String getTokoTokoName(){
		return tokoTokoName;
	}

	public void setCustomerCustomerId(String customerCustomerId){
		this.customerCustomerId = customerCustomerId;
	}

	public String getCustomerCustomerId(){
		return customerCustomerId;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.transactionUserbyUserId);
		dest.writeString(this.bankAccountBankUserId);
		dest.writeString(this.courierFlovour);
		dest.writeString(this.transactionStatus);
		dest.writeString(this.bankAccountBankAccountNumber);
		dest.writeString(this.tokoTokoNoHp);
		dest.writeList(this.log);
		dest.writeString(this.customerCustomerName);
		dest.writeString(this.customerCustomerNohp);
		dest.writeString(this.chatappName);
		dest.writeString(this.customerCustomerCreatedat);
		dest.writeString(this.customerCustomerAddr);
		dest.writeString(this.courierCompany);
		dest.writeString(this.total);
		dest.writeString(this.chatappNick);
		dest.writeString(this.transactionTrAddrDestination);
		dest.writeString(this.shipmentWeight);
		dest.writeString(this.chatappPackage);
		dest.writeString(this.tokoTokoUrl);
		dest.writeString(this.bankBankName);
		dest.writeString(this.transactionId);
		dest.writeString(this.customerCustomerChatappId);
		dest.writeList(this.item);
		dest.writeString(this.transactionNote);
		dest.writeString(this.shipmentAddrFrom);
		dest.writeString(this.bankBankImage);
		dest.writeString(this.customerCustomerTotalspent);
		dest.writeString(this.chatappImage);
		dest.writeString(this.bankAccountBankAccountName);
		dest.writeString(this.shipmentAddrDestination);
		dest.writeString(this.shipmentResi);
		dest.writeString(this.shipmentOngkir);
		dest.writeString(this.shipmentDayeta);
		dest.writeString(this.chatappLink);
		dest.writeString(this.tokoTokoName);
		dest.writeString(this.customerCustomerId);
	}

	public Response() {
	}

	protected Response(Parcel in) {
		this.transactionUserbyUserId = in.readString();
		this.bankAccountBankUserId = in.readString();
		this.courierFlovour = in.readString();
		this.transactionStatus = in.readString();
		this.bankAccountBankAccountNumber = in.readString();
		this.tokoTokoNoHp = in.readString();
		this.log = new ArrayList<Log>();
		in.readList(this.log, Log.class.getClassLoader());
		this.customerCustomerName = in.readString();
		this.customerCustomerNohp = in.readString();
		this.chatappName = in.readString();
		this.customerCustomerCreatedat = in.readString();
		this.customerCustomerAddr = in.readString();
		this.courierCompany = in.readString();
		this.total = in.readString();
		this.chatappNick = in.readString();
		this.transactionTrAddrDestination = in.readString();
		this.shipmentWeight = in.readString();
		this.chatappPackage = in.readString();
		this.tokoTokoUrl = in.readString();
		this.bankBankName = in.readString();
		this.transactionId = in.readString();
		this.customerCustomerChatappId = in.readString();
		this.item = new ArrayList<Item>();
		in.readList(this.item, Item.class.getClassLoader());
		this.transactionNote = in.readString();
		this.shipmentAddrFrom = in.readString();
		this.bankBankImage = in.readString();
		this.customerCustomerTotalspent = in.readString();
		this.chatappImage = in.readString();
		this.bankAccountBankAccountName = in.readString();
		this.shipmentAddrDestination = in.readString();
		this.shipmentResi = in.readString();
		this.shipmentOngkir = in.readString();
		this.shipmentDayeta = in.readString();
		this.chatappLink = in.readString();
		this.tokoTokoName = in.readString();
		this.customerCustomerId = in.readString();
	}

	public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
		@Override
		public Response createFromParcel(Parcel source) {
			return new Response(source);
		}

		@Override
		public Response[] newArray(int size) {
			return new Response[size];
		}
	};
}