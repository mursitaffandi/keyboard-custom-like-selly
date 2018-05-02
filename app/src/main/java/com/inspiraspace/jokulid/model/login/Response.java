package com.inspiraspace.jokulid.model.login;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("user_email")
	private String userEmail;

	@SerializedName("toko_url")
	private String tokoUrl;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("toko_no_hp")
	private String tokoNoHp;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("user_item_unitweight")
	private String userItemUnitweight;

	@SerializedName("toko_name")
	private String tokoName;

	@SerializedName("user_shipment_from")
	private String userShipmentFrom;

	@SerializedName("toko_id")
	private String tokoId;

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setTokoUrl(String tokoUrl){
		this.tokoUrl = tokoUrl;
	}

	public String getTokoUrl(){
		return tokoUrl;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setTokoNoHp(String tokoNoHp){
		this.tokoNoHp = tokoNoHp;
	}

	public String getTokoNoHp(){
		return tokoNoHp;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserItemUnitweight(String userItemUnitweight){
		this.userItemUnitweight = userItemUnitweight;
	}

	public String getUserItemUnitweight(){
		return userItemUnitweight;
	}

	public void setTokoName(String tokoName){
		this.tokoName = tokoName;
	}

	public String getTokoName(){
		return tokoName;
	}

	public void setUserShipmentFrom(String userShipmentFrom){
		this.userShipmentFrom = userShipmentFrom;
	}

	public String getUserShipmentFrom(){
		return userShipmentFrom;
	}

	public void setTokoId(String tokoId){
		this.tokoId = tokoId;
	}

	public String getTokoId(){
		return tokoId;
	}
}