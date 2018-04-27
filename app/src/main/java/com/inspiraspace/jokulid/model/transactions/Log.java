package com.inspiraspace.jokulid.model.transactions;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Log implements Parcelable {

	@SerializedName("transaction_id")
	private String transactionId;

	@SerializedName("createdat")
	private String createdat;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return transactionId;
	}

	public void setCreatedat(String createdat){
		this.createdat = createdat;
	}

	public String getCreatedat(){
		return createdat;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
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
		dest.writeString(this.createdat);
		dest.writeString(this.description);
		dest.writeString(this.id);
	}

	public Log() {
	}

	protected Log(Parcel in) {
		this.transactionId = in.readString();
		this.createdat = in.readString();
		this.description = in.readString();
		this.id = in.readString();
	}

	public static final Parcelable.Creator<Log> CREATOR = new Parcelable.Creator<Log>() {
		@Override
		public Log createFromParcel(Parcel source) {
			return new Log(source);
		}

		@Override
		public Log[] newArray(int size) {
			return new Log[size];
		}
	};
}