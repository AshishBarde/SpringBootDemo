package com.example.demo.common;

import java.util.List;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;


@Component
public class BaseResponseHelper<T> {
	

    public BaseResponse<T> setSaveResponse(T responseObj)
    {
		BaseResponse<T> response = new BaseResponse<T>();
		if (responseObj != null) {
			response.setSuccessResponse(responseObj);
		} else {
			response.setEmptyResponse();
		}
		return response;
    }
    
    
    public BaseResponse<T> setGetResponse(T responseObj)
    {
		BaseResponse<T> response = new BaseResponse<T>();
		if (responseObj != null) {
			response.setSuccessResponse(responseObj);
		} else {
			response.setEmptyResponse();
		}
		return response;
    }
    
    public BaseResponse<List<T>> setGetAllResponse(List<T> responseObj)
    {
		BaseResponse<List<T>> response = new BaseResponse<List<T>>();
		if (responseObj != null) {
			response.setSuccessResponse(responseObj);
		} else {
			response.setEmptyResponse();
		}
		return response;
    }
    
    
    public BaseResponse<T> setUpdateResponse(T responseObj)
    {
		BaseResponse<T> response = new BaseResponse<T>();
		if (responseObj != null) {
			response.setSuccessResponse(responseObj);
		} else {
			response.setProcessFailResponse();
		}
     return response;
    }
	

    public BaseResponse<Boolean> setDeleteResponse(Boolean result)
    {
		BaseResponse<Boolean> response = new BaseResponse<Boolean>();
		if (result) {
			response.setSuccessResponse(result);
		} else {
			response.setProcessFailResponse();
		}
		return response;
    }
    
    public BaseResponse<Boolean> setDuplicateResponse(Boolean result)
    {
		BaseResponse<Boolean> response = new BaseResponse<Boolean>();
		if (result!=null) {
			response.setSuccessResponse(result);
		} else {
			response.setProcessFailResponse();
		}
		return response;
    }


    
}
