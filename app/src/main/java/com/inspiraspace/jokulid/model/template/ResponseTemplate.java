package com.inspiraspace.jokulid.model.template;

import com.google.gson.annotations.SerializedName;

public class ResponseTemplate{

	@SerializedName("template")
	private String template;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("updateat")
	private String updateat;

	@SerializedName("id")
	private String id;

	public void setTemplate(String template){
		this.template = template;
	}

	public String getTemplate(){
		return template;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUpdateat(String updateat){
		this.updateat = updateat;
	}

	public String getUpdateat(){
		return updateat;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"template = '" + template + '\'' + 
			",user_id = '" + userId + '\'' + 
			",updateat = '" + updateat + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}