package com.inspiraspace.jokulid.model.bank;

import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("bank_id")
	private String bankId;

	@SerializedName("bank_image")
	private String bankImage;

	@SerializedName("bank_name")
	private String bankName;

	@SerializedName("link")
	private Object link;

	public void setBankId(String bankId){
		this.bankId = bankId;
	}

	public String getBankId(){
		return bankId;
	}

	public void setBankImage(String bankImage){
		this.bankImage = bankImage;
	}

	public String getBankImage(){
		return bankImage;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return bankName;
	}

	public void setLink(Object link){
		this.link = link;
	}

	public Object getLink(){
		return link;
	}

	@Override
 	public String toString(){
		return 
			"Response{" +
			"bank_id = '" + bankId + '\'' + 
			",bank_image = '" + bankImage + '\'' + 
			",bank_name = '" + bankName + '\'' + 
			",link = '" + link + '\'' + 
			"}";
		}
}