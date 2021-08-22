package com.Banking.Fundtransfer.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.Banking.Fundtransfer.dto.AccountDto;
import com.Banking.Fundtransfer.dto.BeneficiaryDto;
import com.Banking.Fundtransfer.dto.FundTransferDto;
import com.Banking.Fundtransfer.entity.Account;
import com.Banking.Fundtransfer.entity.Address;
import com.Banking.Fundtransfer.entity.Beneficiary;
import com.Banking.Fundtransfer.entity.Customer;
import com.Banking.Fundtransfer.entity.CustomerCredentials;
import com.Banking.Fundtransfer.entity.Transaction;
import com.Banking.Fundtransfer.exception.InSufficientFundException;
import com.Banking.Fundtransfer.exception.InvalidCredentialsException;
import com.Banking.Fundtransfer.exception.ResourceNotFoundException;
import com.Banking.Fundtransfer.exception.TransactionFailedException;
import com.Banking.Fundtransfer.exception.TransferLimitException;
import com.Banking.Fundtransfer.exception.UserNameAlreadyExistsException;
import com.Banking.Fundtransfer.repository.AccountRepository;
import com.Banking.Fundtransfer.repository.AddressRepository;
import com.Banking.Fundtransfer.repository.BeneficiaryRepository;
import com.Banking.Fundtransfer.repository.CustomerCredentialsRepository;
import com.Banking.Fundtransfer.repository.CustomerRepository;
import com.Banking.Fundtransfer.repository.TransactionRepository;
import com.Banking.Fundtransfer.response.AccountCreationAcknowledgement;
import com.Banking.Fundtransfer.utils.CustomerDetails;
import com.Banking.Fundtransfer.utils.Utilities;


@Service
public class CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerCredentialsRepository customerCredentialsRepository;
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private Utilities utilities;

	@Transactional(rollbackFor = TransactionFailedException.class, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public ResponseEntity<AccountCreationAcknowledgement> saveCustomerDetails(AccountDto request)
			throws UserNameAlreadyExistsException {
		logger.info("inside saveCustomerDetails  method");
		Date date = new Date();
		logger.info("Checking for if User Name exists.");
		if (Optional.ofNullable(customerRepository.findByUserName(request.getUserName())).isPresent())
			throw new UserNameAlreadyExistsException("User Name already exists.Please try with another user name");
		Customer cust = new Customer();
		cust.setUserName(request.getUserName());
		cust.setFirstName(request.getFirstName());
		cust.setLastName(request.getLastName());
		cust.setDateOfBirth(request.getDateOfBirth());
		cust.setGender(request.getGender());
		cust.setMobileNumber(Long.valueOf(request.getMobileNumber()));
		cust.setEmailId(request.getEmailId());
		cust.setAadharCard(request.getAadharCard());
		cust.setPanCard(request.getPanCard());
		cust.setCreationDate(date);
		Address addr = new Address();
		addr.setAddress1(request.getAddress1());
		addr.setAddress2(request.getAddress2());
		addr.setCity(request.getCity());
		addr.setState(request.getState());
		addr.setZipcode(Long.valueOf(request.getZipCode()));
		addressRepository.save(addr);
		cust.setAddress(addr);
		customerRepository.save(cust);
		logger.info("saved address and customer entities");
		
		Account acct = new Account();
		acct.setAccountType(request.getAccountType());
		acct.setOpeningDeposit(Double.valueOf(request.getOpeningDeposit()));
		long accountNo = 0l;
		boolean accountStatus = true;
		do {
			accountNo = utilities.accountNumberGeneration();
			logger.info("accountNo:: " + accountNo);
			logger.info("Checking for if accountNo exists.");
			if (accountRepository.findByAccountNo(accountNo) == null)
				accountStatus = false;
		} while (accountStatus);
		acct.setAccountId(accountNo);
		acct.setAvailableBalance(Double.valueOf(request.getOpeningDeposit()));
		acct.setCreationDate(date);
		acct.setBankName(request.getBankName());
		acct.setBranchName(request.getBranchName());
		acct.setIfscCode(request.getIfscCode());
		acct.setCustomer(cust);
		accountRepository.save(acct);
		logger.info("saved account entity");
		
		CustomerCredentials credentials = new CustomerCredentials();
		credentials.setAccountStatus(1);
		credentials.setCustomer(cust);
		credentials.setUserName(cust.getUserName());
		boolean pwdStatus = true;
		String strPwd=null;
		do {
			strPwd = UUID.randomUUID().toString().split("-")[0];
			logger.info("Checking for if password exists.");
			if (Optional.ofNullable(customerCredentialsRepository.findByPassword(strPwd)).isEmpty())
				pwdStatus = false;
		} while (pwdStatus);
		credentials.setPassword(strPwd);
		customerCredentialsRepository.save(credentials);
		logger.info("saved customer credentials entity");
		StringBuffer strMsg = new StringBuffer();
		strMsg.append("Account Opened for Customer :: ").append(cust.getFirstName()).append(" " + cust.getLastName());
		strMsg.append("");
		strMsg.append("  Use credentials for Login: ").append("User Name:: " + credentials.getUserName())
				.append(" Password:: " + credentials.getPassword());
		return new ResponseEntity<>(new AccountCreationAcknowledgement(strMsg.toString()), HttpStatus.OK);
	}

	public ResponseEntity<String> saveBeneficiary(BeneficiaryDto dto, String userName) {
		logger.info("inside saveBeneficiary  method");
		logger.info("Checking for if customer exists.");
		Customer customer = Optional.ofNullable(customerRepository.findByUserName(userName))
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Name", userName));
		Beneficiary benificary = new Beneficiary();
		benificary.setBeneficiaryAccountNo(Long.valueOf(dto.getBeneficiaryAccountNo()));
		benificary.setBeneficiaryName(benificary.getBeneficiaryName());
		benificary.setIfscCode(dto.getIfscCode());
		benificary.setTransferLimit(Double.valueOf(dto.getTransferLimit()));
		benificary.setCustomer(customer);
		beneficiaryRepository.save(benificary);
		return new ResponseEntity<>("Successfully added Beneficiary", HttpStatus.OK);
	}

	public ResponseEntity<String> checkLoginCredential(CustomerCredentials credentials) {
		logger.info("inside checkLoginCredential method");
		logger.info("Checking for if customer credentials");
		if (Optional.ofNullable(
				customerCredentialsRepository.findByUserNameAndPassword(credentials.getUserName(), credentials.getPassword()))
				.isEmpty()) {
			throw new InvalidCredentialsException("Authetication Failed! Please provide valid User Name or Password ");
		}
		return new ResponseEntity<>("Authetication Success", HttpStatus.OK);
	}

	public ResponseEntity<String> fundTransfer(FundTransferDto fundTransDto, String userName) {
		Customer customer = Optional.ofNullable(customerRepository.findByUserName(userName))
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Name", userName));
		// Check is the account number exists, if does not exists return error message
		// else continue .
		Account fromAccDetails = Optional
				.ofNullable(accountRepository.findByAccountNoAndCustomerCustomerId(
						Long.valueOf(fundTransDto.getFromAccountNo()), customer.getCustomerId()))
				.orElseThrow(() -> new ResourceNotFoundException("Account Details", "Account Number",
						fundTransDto.getFromAccountNo()));

		Beneficiary toAccDetails = Optional
				.ofNullable(beneficiaryRepository.findByBeneficiaryAccountNoAndCustomerCustomerId(
						Long.valueOf(fundTransDto.getToAccountNo()), customer.getCustomerId()))
				.orElseThrow(() -> new ResourceNotFoundException("Customers Beneficiary Account Details  ", "Account Number",
						fundTransDto.getToAccountNo()));

		if (Double.valueOf(fundTransDto.getTransferAmount()) > fromAccDetails.getAvailableBalance()) {
			throw new InSufficientFundException(
					"InSufficent balance for the account number::" + fromAccDetails.getAccountId());
		}

		if (Double.valueOf(fundTransDto.getTransferAmount()) > toAccDetails.getTransferLimit()) {
			throw new TransferLimitException("Transfer Limit Excessed for this benificary account number::"
					+ toAccDetails.getBeneficiaryAccountNo()+" Maximum tranfer Limit: "+toAccDetails.getTransferLimit());
		}

		Date date = new Date();
		Transaction sourceAcc = new Transaction();
		Transaction targetAcc = new Transaction();

		// inserting records to transactions tables for source account transaction
		Timestamp ts = new Timestamp(date.getTime());
		sourceAcc.setAmount(Double.valueOf(fundTransDto.getTransferAmount()));
		sourceAcc.setFromAccount(fromAccDetails.getAccountId());
		sourceAcc.setTransactionTime(ts);
		sourceAcc.setTransactionType("Debit");
		sourceAcc.setRemarks(fundTransDto.getRemarks());
		transactionRepository.save(sourceAcc);

		// inserting records to transactions tables for target account transaction
		targetAcc.setAmount(Double.valueOf(fundTransDto.getTransferAmount()));
		targetAcc.setToAccount(toAccDetails.getBeneficiaryAccountNo());
		targetAcc.setTransactionTime(ts);
		targetAcc.setTransactionType("Credit");
		targetAcc.setRemarks(fundTransDto.getRemarks());
		transactionRepository.save(targetAcc);

		// updating the account details for the given account number
		if (Optional.ofNullable(transactionRepository).isPresent()) {
			fromAccDetails.setAvailableBalance(
					fromAccDetails.getAvailableBalance() - Double.valueOf(fundTransDto.getTransferAmount()));
			fromAccDetails.setOpeningDeposit(
					fromAccDetails.getOpeningDeposit() - Double.valueOf(fundTransDto.getTransferAmount()));
			accountRepository.save(fromAccDetails);
		}

		return new ResponseEntity<>("Transaction Done Successfully ", HttpStatus.OK);
	}

	public ResponseEntity<CustomerDetails> getCustomerDetails(String userName) {
		logger.info("inside getCustomerDetails method");
		return new ResponseEntity<>(customerRepository.findCustomerByUserName(userName), HttpStatus.OK);
	}
	
		
	

}
