package com.Banking.Fundtransfer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreationAcknowledgement {
	
	private String StatusMsg;

	public AccountCreationAcknowledgement(String statusMsg) {
		super();
		StatusMsg = statusMsg;
	}

	public AccountCreationAcknowledgement() {
		super();
	}

	public String getStatusMsg() {
		return StatusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		StatusMsg = statusMsg;
	}

	@Override
	public String toString() {
		return "AccountCreationAcknowledgement [StatusMsg=" + StatusMsg + "]";
	}
	
	
	

}
