package com.inspiraspace.jokulid.model.shop;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("toko_url")
	private String tokoUrl;

	@SerializedName("toko_no_hp")
	private String tokoNoHp;

	@SerializedName("toko_name")
	private String tokoName;

	@SerializedName("toko_admin_id")
	private String tokoAdminId;

	@SerializedName("toko_user_id")
	private String tokoUserId;

	@SerializedName("toko_id")
	private String tokoId;

	public void setTokoUrl(String tokoUrl){
		this.tokoUrl = tokoUrl;
	}

	public String getTokoUrl(){
		return tokoUrl;
	}

	public void setTokoNoHp(String tokoNoHp){
		this.tokoNoHp = tokoNoHp;
	}

	public String getTokoNoHp(){
		return tokoNoHp;
	}

	public void setTokoName(String tokoName){
		this.tokoName = tokoName;
	}

	public String getTokoName(){
		return tokoName;
	}

	public void setTokoAdminId(String tokoAdminId){
		this.tokoAdminId = tokoAdminId;
	}

	public String getTokoAdminId(){
		return tokoAdminId;
	}

	public void setTokoUserId(String tokoUserId){
		this.tokoUserId = tokoUserId;
	}

	public String getTokoUserId(){
		return tokoUserId;
	}

	public void setTokoId(String tokoId){
		this.tokoId = tokoId;
	}

	public String getTokoId(){
		return tokoId;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"toko_url = '" + tokoUrl + '\'' + 
			",toko_no_hp = '" + tokoNoHp + '\'' + 
			",toko_name = '" + tokoName + '\'' + 
			",toko_admin_id = '" + tokoAdminId + '\'' + 
			",toko_user_id = '" + tokoUserId + '\'' + 
			",toko_id = '" + tokoId + '\'' + 
			"}";
		}
}