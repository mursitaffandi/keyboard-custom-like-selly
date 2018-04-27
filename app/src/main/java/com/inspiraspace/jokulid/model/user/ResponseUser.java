package com.inspiraspace.jokulid.model.user;

import com.google.gson.annotations.SerializedName;

public class ResponseUser{

	@SerializedName("response")
	private Response response;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"ResponseUser{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}