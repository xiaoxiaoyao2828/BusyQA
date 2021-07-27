package com.crmbackend.payLoad.response;

public class ReturnMessageResponse {
	private String message;

	public ReturnMessageResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
