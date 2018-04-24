package com.inspiraspace.jokulid.model.template;

import com.google.gson.annotations.SerializedName;

public class Template{

	@SerializedName("response")
	private ResponseTemplate response;

	public void setResponse(ResponseTemplate response){
		this.response = response;
	}

	public ResponseTemplate getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"Template{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}