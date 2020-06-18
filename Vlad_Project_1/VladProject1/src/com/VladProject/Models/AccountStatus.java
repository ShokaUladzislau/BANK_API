package com.VladProject.Models;

	public class AccountStatus {
	private int statusId; // primary key
	private String status; // not null, unique

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AccountStatus(int statusId, String status) {
		this.statusId = statusId;
		this.status = status;
	}
	
	public AccountStatus() {
		this.statusId = 0;
		this.status = null;
	}

}
