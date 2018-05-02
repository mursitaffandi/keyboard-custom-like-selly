package com.inspiraspace.jokulid.model.login;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("response")
	private Response response;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}
}