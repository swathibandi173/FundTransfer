package com.Banking.Fundtransfer.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction 
{
	@Id
	@GeneratedValue
	@Column(name="transaction_id")
	private Long transactionId;

	private double amount;

	private long fromAccount;

	private long toAccount;

	private Timestamp transactionTime;

	private String transactionType;

	private String remarks;
	public Transaction(Long transactionId, double amount, long fromAccount, long toAccount, Timestamp transactionTime,
			String transactionType, String remarks) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transactionTime = transactionTime;
		this.transactionType = transactionType;
		this.remarks = remarks;
	}
	public Transaction() {
		super();
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public long getToAccount() {
		return toAccount;
	}
	public void setToAccount(long toAccount) {
		this.toAccount = toAccount;
	}
	public Timestamp getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", fromAccount=" + fromAccount
				+ ", toAccount=" + toAccount + ", transactionTime=" + transactionTime + ", transactionType="
				+ transactionType + ", remarks=" + remarks + "]";
	}
	
	
}
