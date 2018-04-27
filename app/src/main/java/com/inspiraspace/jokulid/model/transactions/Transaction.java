package com.inspiraspace.jokulid.model.transactions;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Transaction implements Parcelable {

	@SerializedName("response")
	private List<Response> response;

	public void setResponse(List<Response> response){
		this.response = response;
	}

	public List<Response> getResponse(){
		return response;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(this.response);
	}

	public Transaction() {
	}

	protected Transaction(Parcel in) {
		this.response = new ArrayList<Response>();
		in.readList(this.response, Response.class.getClassLoader());
	}

	public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
		@Override
		public Transaction createFromParcel(Parcel source) {
			return new Transaction(source);
		}

		@Override
		public Transaction[] newArray(int size) {
			return new Transaction[size];
		}
	};
}