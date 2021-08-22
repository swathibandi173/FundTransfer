package com.Banking.Fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.Fundtransfer.entity.Customer;
import com.Banking.Fundtransfer.utils.CustomerDetails;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
	
	Customer findByUserName(String userName);
	
	Customer findByCustomerId(long customerId);

	CustomerDetails findCustomerByUserName(String userName);

}
