package com.inspiraspace.jokulid.model.customers;

import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("customer_chatapp_id")
	private String customerChatappId;

	@SerializedName("customer_addr")
	private String customerAddr;

	@SerializedName("customer_userby_user_id")
	private String customerUserbyUserId;

	@SerializedName("image")
	private String image;

	@SerializedName("customer_nohp")
	private String customerNohp;

	@SerializedName("customer_createdat")
	private String customerCreatedat;

	@SerializedName("name")
	private String name;

	@SerializedName("link")
	private String link;

	@SerializedName("customer_total_spent")
	private String customerTotalSpent;

	@SerializedName("customer_name")
	private String customerName;

	@SerializedName("id")
	private String id;

	@SerializedName("customer_id")
	private String customerId;

	public void setCustomerChatappId(String customerChatappId){
		this.customerChatappId = customerChatappId;
	}

	public String getCustomerChatappId(){
		return customerChatappId;
	}

	public void setCustomerAddr(String customerAddr){
		this.customerAddr = customerAddr;
	}

	public String getCustomerAddr(){
		return customerAddr;
	}

	public void setCustomerUserbyUserId(String customerUserbyUserId){
		this.customerUserbyUserId = customerUserbyUserId;
	}

	public String getCustomerUserbyUserId(){
		return customerUserbyUserId;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCustomerNohp(String customerNohp){
		this.customerNohp = customerNohp;
	}

	public String getCustomerNohp(){
		return customerNohp;
	}

	public void setCustomerCreatedat(String customerCreatedat){
		this.customerCreatedat = customerCreatedat;
	}

	public String getCustomerCreatedat(){
		return customerCreatedat;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setCustomerTotalSpent(String customerTotalSpent){
		this.customerTotalSpent = customerTotalSpent;
	}

	public String getCustomerTotalSpent(){
		return customerTotalSpent;
	}

	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}

	public String getCustomerName(){
		return customerName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}
}