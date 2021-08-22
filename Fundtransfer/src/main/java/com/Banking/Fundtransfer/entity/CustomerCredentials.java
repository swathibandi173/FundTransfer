package com.Banking.Fundtransfer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerCredentials {
	
	@Id
	@GeneratedValue
	@Column(name="custCredentials_id")
	private Long customerCredentialsId;
	
	private String userName;
	
	private String password;
	
	private int accountStatus;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
	private Customer customer;
	public CustomerCredentials() {
		super();
	}
	public CustomerCredentials(Long customerCredentialsId, String userName, String password, int accountStatus,
			Customer customer) {
		super();
		this.customerCredentialsId = customerCredentialsId;
		this.userName = userName;
		this.password = password;
		this.accountStatus = accountStatus;
		this.customer = customer;
	}
	public Long getCustomerCredentialsId() {
		return customerCredentialsId;
	}
	public void setCustomerCredentialsId(Long customerCredentialsId) {
		this.customerCredentialsId = customerCredentialsId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int isAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "CustomerCredentials [customerCredentialsId=" + customerCredentialsId + ", userName=" + userName
				+ ", password=" + password + ", accountStatus=" + accountStatus + ", customer=" + customer + "]";
	}
	

}
