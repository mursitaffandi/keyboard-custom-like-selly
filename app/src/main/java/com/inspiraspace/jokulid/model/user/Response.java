package com.inspiraspace.jokulid.model.user;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("user_email")
	private String userEmail;

	@SerializedName("user_password")
	private String userPassword;

	@SerializedName("user_name")
	private String userName;

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"user_email = '" + userEmail + '\'' + 
			",user_password = '" + userPassword + '\'' + 
			",user_name = '" + userName + '\'' + 
			"}";
		}
}