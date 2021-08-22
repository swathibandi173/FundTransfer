package com.Banking.Fundtransfer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Beneficiary {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="beneficiary_id")
	 private Long beneficiaryId;
	 
	 private String beneficiaryName;
	 
	 private long beneficiaryAccountNo;

	 private double transferLimit;
	
	private String ifscCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
	private Customer customer;

	public Beneficiary() {
		super();
	}

	public Beneficiary(Long beneficiaryId, String beneficiaryName, long beneficiaryAccountNo, double transferLimit,
			String ifscCode, Customer customer) {
		super();
		this.beneficiaryId = beneficiaryId;
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryAccountNo = beneficiaryAccountNo;
		this.transferLimit = transferLimit;
		this.ifscCode = ifscCode;
		this.customer = customer;
	}

	public Long getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public long getBeneficiaryAccountNo() {
		return beneficiaryAccountNo;
	}

	public void setBeneficiaryAccountNo(long beneficiaryAccountNo) {
		this.beneficiaryAccountNo = beneficiaryAccountNo;
	}

	public double getTransferLimit() {
		return transferLimit;
	}

	public void setTransferLimit(double transferLimit) {
		this.transferLimit = transferLimit;
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
		return "Beneficiary [beneficiaryId=" + beneficiaryId + ", beneficiaryName=" + beneficiaryName
				+ ", beneficiaryAccountNo=" + beneficiaryAccountNo + ", transferLimit=" + transferLimit + ", ifscCode="
				+ ifscCode + ", customer=" + customer + "]";
	}

	
	
	
	
}
