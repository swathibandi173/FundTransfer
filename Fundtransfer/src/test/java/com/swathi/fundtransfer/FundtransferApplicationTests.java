package com.swathi.fundtransfer;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.Banking.Fundtransfer.dto.AccountDto;
import com.Banking.Fundtransfer.dto.BeneficiaryDto;
import com.Banking.Fundtransfer.dto.FundTransferDto;
import com.Banking.Fundtransfer.entity.Account;
import com.Banking.Fundtransfer.entity.Beneficiary;
import com.Banking.Fundtransfer.entity.Customer;
import com.Banking.Fundtransfer.entity.CustomerCredentials;
import com.Banking.Fundtransfer.exception.InSufficientFundException;
import com.Banking.Fundtransfer.exception.InvalidCredentialsException;
import com.Banking.Fundtransfer.exception.ResourceNotFoundException;
import com.Banking.Fundtransfer.exception.UserNameAlreadyExistsException;
import com.Banking.Fundtransfer.repository.AccountRepository;
import com.Banking.Fundtransfer.repository.BeneficiaryRepository;
import com.Banking.Fundtransfer.repository.CustomerCredentialsRepository;
import com.Banking.Fundtransfer.repository.CustomerRepository;
import com.Banking.Fundtransfer.service.CustomerService;

@SpringBootTest
class FundtransferApplicationTests {

	@Autowired
	CustomerService customerService;
	
	@MockBean
	CustomerCredentialsRepository custCredRepo;

	@MockBean
	CustomerRepository custRepo;
	
	@MockBean
	AccountRepository accRepo;

	@MockBean
	BeneficiaryRepository beneficiaryRepo;


	Customer customer = new Customer();
	Beneficiary benificary = new Beneficiary();
	Account account = new Account();
	CustomerCredentials custCredentials=new CustomerCredentials();
	BeneficiaryDto dto = new BeneficiaryDto();
	FundTransferDto fundTranferDto = new FundTransferDto();
	AccountDto accountDto = new AccountDto();

	String name = "AI0326";

	@BeforeAll
	public void setUp() {
		// mock customer credentials as in database
		custCredentials.setUserName("Swathi");
		custCredentials.setPassword("bandi");
		
		
		// mock customer details as in database
		customer.setCustomerId(1l);
		customer.setUserName("AI0326");
		customer.setFirstName("Karthik");
		customer.setLastName("Maddika");
		//customer.setDateOfBirth(LocalDate.now());
		customer.setGender("male");
		//customer.setMobileNumber(9989705293);
		customer.setEmailId("karthik@gmail.com");
		customer.setPanCard("AULPK1987H");
		customer.setAadharCard("400607192799");
				
		// mock account details as in database
		account.setAccountId(1l);
		account.setAvailableBalance((double) 4500);
		account.setOpeningDeposit((double) 4500);
		account.setBankName("HDFC");
		account.setBranchName("Kukatpally");
		account.setIfscCode("HDFC00314");
		account.setCreationDate(new Date());
		account.setCustomer(customer);
		account.setAccountType("Checking");
		account.setAccountNo(123456l);
				
				
		// mock benificary details as in database
		benificary.setBeneficiaryAccountNo(4567);
		benificary.setBeneficiaryId(1l);
		benificary.setBeneficiaryName("aryan");
		benificary.setCustomer(customer);
		benificary.setIfscCode("HDFC00314");
		benificary.setTransferLimit(6000);
				
		//set values for fund transfer dto
		
		fundTranferDto.setFromAccountNo("123456");
		fundTranferDto.setToAccountNo("4567");
		fundTranferDto.setRemarks("Expenses");
		fundTranferDto.setTransferAmount("1000");
		
		
		//set values for BeneficiaryDTO
		dto.setBeneficiaryAccountNo("4567");
		dto.setBeneficiaryName("Nagesh");
		dto.setIfscCode("HDF00314");
		dto.setTransferLimit("1000");
		
		//set values for accountopening dto
		accountDto.setUserName("AI0327");
		accountDto.setFirstName("Devi");
		accountDto.setLastName("Konatham");
		//accountDto.setDateOfBirth("2018-01-18");
		accountDto.setGender("male");
		accountDto.setMobileNumber("7680092889");
		accountDto.setEmailId("skonatham@gmail.com");
		accountDto.setPanCard("AULPK1507H");
		accountDto.setAadharCard("400607192799");
		accountDto.setAddress1("Adress1");
		accountDto.setAddress2("Address2");
		accountDto.setCity("KP");
		accountDto.setState("Hyd");
		accountDto.setZipCode("500072");
		accountDto.setOpeningDeposit("4500");
		accountDto.setBankName("HDFC");
		accountDto.setBranchName("Kukatpally");
		accountDto.setIfscCode("HDFC00314");
		accountDto.setAccountType("Checking");
		
		
	}
	
	@Test
	@DisplayName("Account Opening")
	@Order(1)
	public void testCreateAccountOpening() throws UserNameAlreadyExistsException {
		Mockito.when(custRepo.findByUserName(customer.getUserName())).thenReturn(customer);
		assertEquals(HttpStatus.OK, customerService.saveCustomerDetails(accountDto).getStatusCode());
	}
	

	private void assertEquals(HttpStatus ok, HttpStatus statusCode) {
		// TODO Auto-generated method stub
		
	}

	@Test
	@DisplayName("Check Customer Login Credentials")
	@Order(2)
	public void testAuthenticateUser() throws InvalidCredentialsException {
		Mockito.when(custCredRepo.findByUserNameAndPassword("Nagesh", "sri")).thenReturn(custCredentials);
		assertEquals(HttpStatus.OK, customerService.checkLoginCredential(custCredentials).getStatusCode());
	}

	@Test
	@DisplayName("Save Beneficiary")
	@Order(3)
	public void testSaveBenificary() throws ResourceNotFoundException, MethodArgumentNotValidException {
		Mockito.when(custRepo.findByUserName(customer.getUserName())).thenReturn(customer);
		assertEquals(HttpStatus.OK, customerService.saveBeneficiary(dto, name).getStatusCode());
	}
	
	@Test
	@DisplayName("Test FundTransfer Transaction")
	@Order(4)
	public void testFundTransfer() throws ResourceNotFoundException, InSufficientFundException {
		Mockito.when(custRepo.findByUserName(customer.getUserName())).thenReturn(customer);
		Mockito.when(accRepo.findByAccountNoAndCustomerCustomerId(account.getAccountNo(), 1l)).thenReturn(account);
		Mockito.when(beneficiaryRepo.findByBeneficiaryAccountNoAndCustomerCustomerId(Long.valueOf(benificary.getBeneficiaryAccountNo()),1l)).thenReturn(benificary);
		
		assertEquals(HttpStatus.OK, customerService.fundTransfer(fundTranferDto, name).getStatusCode());
	}
}
