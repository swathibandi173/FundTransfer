package com.Banking.Fundtransfer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Banking.Fundtransfer.dto.AccountDto;
import com.Banking.Fundtransfer.dto.BeneficiaryDto;
import com.Banking.Fundtransfer.dto.FundTransferDto;
import com.Banking.Fundtransfer.entity.CustomerCredentials;
import com.Banking.Fundtransfer.exception.UserNameAlreadyExistsException;
import com.Banking.Fundtransfer.response.AccountCreationAcknowledgement;
import com.Banking.Fundtransfer.service.CustomerService;



@RestController
@RequestMapping("/customers")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/")
	public ResponseEntity<Object> customerAccountOpening(@Valid @RequestBody AccountDto request)  throws UserNameAlreadyExistsException
	{
    	logger.info("inside customerAccountOpening");
    	ResponseEntity<AccountCreationAcknowledgement> saveCustomerDetails = customerService.saveCustomerDetails(request);
		return new ResponseEntity<>(saveCustomerDetails,HttpStatus.OK);
	}
    
    @PostMapping("/{userName}")
	public ResponseEntity<Object> saveBeneficiaryDetails(@Valid @RequestBody BeneficiaryDto request,@PathVariable("userName") String userName)  
	{
    	logger.info("inside saveBeneficiaryDetails");
    	ResponseEntity<String> saveBeneficiary = customerService.saveBeneficiary(request,userName);
		return new ResponseEntity<>(saveBeneficiary,HttpStatus.OK);
	}
    
    @PostMapping("/checkCredentials")
	public ResponseEntity<Object> checkCredentials(@Valid @RequestBody CustomerCredentials custCredentials)  
	{
    	logger.info("inside checkCredentials");
    	ResponseEntity<String> checkLoginCredential = customerService.checkLoginCredential(custCredentials);
		return new ResponseEntity<>(checkLoginCredential,HttpStatus.OK);
	}
    @PostMapping("/fundTransfer/{userName}")
   	public ResponseEntity<Object> fundTransfer(@Valid @RequestBody FundTransferDto fundTransferDto,@PathVariable("userName") String userName)  
   	{
       	logger.info("inside checkCredentials");
       	ResponseEntity<String> fundTransfer = customerService.fundTransfer(fundTransferDto,userName);
   		return new ResponseEntity<>(fundTransfer,HttpStatus.OK);
   	}
    
    @GetMapping("/{userName}")
   	public ResponseEntity<?> getCustomerDetails(@PathVariable("userName") String userName)  
   	{
       	logger.info("inside checkCredentials");
   		return new ResponseEntity<>(customerService.getCustomerDetails(userName),HttpStatus.OK);
   	}
}
