package com.Banking.Fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Banking.Fundtransfer.entity.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long>{
	
	Beneficiary findByBeneficiaryAccountNoAndCustomerCustomerId(long accountNo,long customerId);

}
