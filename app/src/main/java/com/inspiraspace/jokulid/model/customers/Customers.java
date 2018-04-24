package com.inspiraspace.jokulid.model.customers;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Customers{

	@SerializedName("response")
	private List<ResponseItem> response;

	public void setResponse(List<ResponseItem> response){
		this.response = response;
	}

	public List<ResponseItem> getResponse(){
		return response;
	}
}