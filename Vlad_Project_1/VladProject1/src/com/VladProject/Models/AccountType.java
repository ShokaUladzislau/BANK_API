package com.VladProject.Models;

public class AccountType {
	private int typeId; // primary key
	private String type; // not null, unique
	
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public AccountType(int typeId, String type) {
		this.typeId = typeId;
		this.type = type;
	}
	
	public AccountType() {
		this.typeId = 0;
		this.type = null;
	}
}