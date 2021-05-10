package com.revature.models;

public class TransferDTO {

	public int accountId1;		//account taking money from
	public int accountId2;		//account giving money to
	public double amount;
	
	public TransferDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAccountId1() {
		return accountId1;
	}

	public void setAccountId1(int accountId1) {
		this.accountId1 = accountId1;
	}

	public int getAccountId2() {
		return accountId2;
	}

	public void setAccountId2(int accountId2) {
		this.accountId2 = accountId2;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}	
	
}
