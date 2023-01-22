package com.wallet.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Statement")
public class Statement {
	@Id
	private String statementId;
	private String transactionType;
	private Double amount;
	private LocalDate date;

	private int orderId;
	private String transactionRemarks;
	
	
	private String ewalletId;
	


	
	public Statement(String statementId, String transactionType, Double amount, LocalDate date, int orderId,
			String transactionRemarks, String ewalletId) {
		super();
		this.statementId = statementId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.date = date;
		this.orderId = orderId;
		this.transactionRemarks = transactionRemarks;
		this.ewalletId = ewalletId;
	}
	public Statement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStatementId() {
		return statementId;
	}
	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getTransactionRemarks() {
		return transactionRemarks;
	}
	public void setTransactionRemarks(String transactionRemarks) {
		this.transactionRemarks = transactionRemarks;
	}

	
	public String getEwalletId() {
		return ewalletId;
	}
	public void setEwalletId(String ewalletId) {
		this.ewalletId = ewalletId;
	}
	@Override
	public String toString() {
		return "Statement [statementId=" + statementId + ", transactionType=" + transactionType + ", amount=" + amount
				+ ", date=" + date + ", orderId=" + orderId + ", transactionRemarks=" + transactionRemarks + "]";
	}

	
}
