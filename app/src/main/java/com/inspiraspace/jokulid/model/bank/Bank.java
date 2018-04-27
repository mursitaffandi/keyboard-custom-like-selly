package com.inspiraspace.jokulid.model.bank;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Bank{

	@SerializedName("response")
	private List<ResponseItem> response;

	public void setResponse(List<ResponseItem> response){
		this.response = response;
	}

	public List<ResponseItem> getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"Bank{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}