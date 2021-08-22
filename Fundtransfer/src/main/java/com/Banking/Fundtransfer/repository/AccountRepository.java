package com.Banking.Fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.Fundtransfer.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	
	Long findByAccountNo(long accountNo);
	Account findByAccountNoAndCustomerCustomerId(long accountNo,long customerId);

}
