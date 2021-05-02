package com.revature.models;

import java.io.Serializable;

public class AccountStatus implements Serializable{
	
	//track status of accounts
	//Pending, Open, Closed, or Denied

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int statusId; // primary key
	private String status; // not null, unique
	
	public AccountStatus() {
		super();
	}
	
	public AccountStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}

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

	@Override
	public String toString() {
		return "AccountStatus [statusId=" + statusId + ", status=" + status + "]";
	}
		
		

}
