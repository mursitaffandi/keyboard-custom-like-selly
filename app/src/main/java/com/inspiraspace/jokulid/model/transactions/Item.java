package com.inspiraspace.jokulid.model.transactions;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Item implements Parcelable {

	@SerializedName("transaction_id")
	private String transactionId;

	@SerializedName("price")
	private String price;

	@SerializedName("qty")
	private String qty;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return transactionId;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setQty(String qty){
		this.qty = qty;
	}

	public String getQty(){
		return qty;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.transactionId);
		dest.writeString(this.price);
		dest.writeString(this.qty);
		dest.writeString(this.name);
		dest.writeString(this.id);
	}

	public Item() {
	}

	protected Item(Parcel in) {
		this.transactionId = in.readString();
		this.price = in.readString();
		this.qty = in.readString();
		this.name = in.readString();
		this.id = in.readString();
	}

	public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
		@Override
		public Item createFromParcel(Parcel source) {
			return new Item(source);
		}

		@Override
		public Item[] newArray(int size) {
			return new Item[size];
		}
	};
}