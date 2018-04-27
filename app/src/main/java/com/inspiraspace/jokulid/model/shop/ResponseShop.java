package com.inspiraspace.jokulid.model.shop;

import com.google.gson.annotations.SerializedName;

public class ResponseShop{

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
			"ResponseShop{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}