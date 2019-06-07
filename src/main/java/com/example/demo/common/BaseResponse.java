package com.example.demo.common;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable{
	
	private Integer responseCode;
	
	private String responseMessage;
	
	private T responseData;

	public BaseResponse() {
		super();
	}

	//Success response
	public void setSuccessResponse(T data){
		this.setResponseCode(200);
		this.setResponseMessage("Success / Record Found");
		this.setResponseData(data);
	}
	
	public void setEmptyResponse(){
		this.setResponseCode(201);
		this.setResponseMessage("Record Not Found");
	}
	
	public void setRequireParamResponse(){
		   this.setResponseCode(401);
		   this.setResponseMessage("Process Fail");
	}

	public void setProcessFailResponse(){
		   this.setResponseCode(402);
		   this.setResponseMessage("Process Fail");
	}
	

	

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}
	

}
