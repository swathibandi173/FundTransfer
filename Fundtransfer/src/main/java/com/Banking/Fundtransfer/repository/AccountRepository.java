package com.Banking.Fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Banking.Fundtransfer.entity.Account;


public interface AccountRepository extends JpaRepository<Account,Long> {
	
	Long findByAccountNo(long accountNo);
	Account findByAccountNoAndCustomerCustomerId(long accountNo,long customerId);

}
