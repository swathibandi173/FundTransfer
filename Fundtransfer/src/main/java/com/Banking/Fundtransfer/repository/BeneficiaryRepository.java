package com.Banking.Fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.Fundtransfer.entity.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long>{
	
	Beneficiary findByBeneficiaryAccountNoAndCustomerCustomerId(long accountNo,long customerId);

}
