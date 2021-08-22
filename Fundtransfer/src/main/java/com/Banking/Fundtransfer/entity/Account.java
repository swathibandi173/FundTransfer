package com.Banking.Fundtransfer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
	@Id
	@GeneratedValue
	@Column(name = "account_id")
	private Long accountId;
	
	@Column(name = "account_no")
	private Long accountNo;
	
	private String accountType;
	
	private Double openingDeposit;
	
	private Double availableBalance;
	
	private String bankName;
	
	private String branchName;

	private Date creationDate;
	
	private String ifscCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
	private Customer customer;

	public Account() {
		super();
	}

	public Account(Long accountId, Long accountNo, String accountType, Double openingDeposit, Double availableBalance,
			String bankName, String branchName, Date creationDate, String ifscCode, Customer customer) {
		super();
		this.accountId = accountId;
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.openingDeposit = openingDeposit;
		this.availableBalance = availableBalance;
		this.bankName = bankName;
		this.branchName = branchName;
		this.creationDate = creationDate;
		this.ifscCode = ifscCode;
		this.customer = customer;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getOpeningDeposit() {
		return openingDeposit;
	}

	public void setOpeningDeposit(Double openingDeposit) {
		this.openingDeposit = openingDeposit;
	}

	public Double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNo=" + accountNo + ", accountType=" + accountType
				+ ", openingDeposit=" + openingDeposit + ", availableBalance=" + availableBalance + ", bankName="
				+ bankName + ", branchName=" + branchName + ", creationDate=" + creationDate + ", ifscCode=" + ifscCode
				+ ", customer=" + customer + "]";
	}

	
	
	
	

}
