package com.Banking.Fundtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.Fundtransfer.entity.CustomerCredentials;

@Repository
public interface CustomerCredentialsRepository extends JpaRepository<CustomerCredentials,Long>{
	
	String findByPassword(String password);
	CustomerCredentials findByUserNameAndPassword(String userName,String password);

}
