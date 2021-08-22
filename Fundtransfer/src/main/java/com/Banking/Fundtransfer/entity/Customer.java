package com.Banking.Fundtransfer.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name="customer_id")
	private Long customerId;

	private String userName;
	
	private String firstName;
	
	private String lastName;

	private Date dateOfBirth;
	

	private String gender;

	private Long mobileNumber;
	@Email
	private String emailId;
	
	private String panCard;

	private String aadharCard;

	private Date creationDate;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
	private Address address;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private List<Account> accounts;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private List<Beneficiary> beneficiaries;
	public Customer() {
		super();
	}
	
	public Customer(Long customerId, String userName, String firstName, String lastName, Date dateOfBirth,
			String gender, Long mobileNumber, @Email String emailId, String panCard, String aadharCard,
			Date creationDate, Address address, List<Account> accounts, List<Beneficiary> beneficiaries) {
		super();
		this.customerId = customerId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.panCard = panCard;
		this.aadharCard = aadharCard;
		this.creationDate = creationDate;
		this.address = address;
		this.accounts = accounts;
		this.beneficiaries = beneficiaries;
	}

	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public @Size(max = 10) Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long long1) {
		this.mobileNumber = long1;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public String getAadharCard() {
		return aadharCard;
	}
	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}
	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", mobileNumber="
				+ mobileNumber + ", emailId=" + emailId + ", panCard=" + panCard + ", aadharCard=" + aadharCard
				+ ", creationDate=" + creationDate + ", address=" + address + ", accounts=" + accounts
				+ ", beneficiaries=" + beneficiaries + "]";
	}
	
	
	

}
