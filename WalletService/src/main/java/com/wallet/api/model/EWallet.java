package com.wallet.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "EWallet")
public class EWallet {
	@Id
	private String walletId;
	private Double currentBalance;
	
	@Indexed(unique = true)
	private String userId;
	
	private String userName;


	public EWallet() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public EWallet(String walletId, Double currentBalance, String userId, String userName) {
		super();
		this.walletId = walletId;
		this.currentBalance = currentBalance;
		this.userId = userId;
		this.userName = userName;
	}


	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}


	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	

}
