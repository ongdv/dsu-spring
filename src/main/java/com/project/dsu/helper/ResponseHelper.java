package com.project.dsu.helper;

import java.util.Locale;

import com.project.dsu.exception.APIException;
import com.project.dsu.handler.APIHandler;

public class ResponseHelper {
	
	public static APIResponse createSuccess(Object result) {
		APIResponse response = new APIResponse();
		response.setResultCode("200");
		response.setResultMessage("SUCCESS");
		response.setResult(result);
		return response;
	}
	
	public static APIResponse createError(APIHandler handler, Locale locale) {
		APIResponse response = new APIResponse();
		String code = handler.getErrorCode();
		response.setResultCode(code);
		response.setResultMessage(handler.getMessage(code, locale));
		return response;
	}
	
	public static APIResponse createError(APIHandler handler, APIException apiException, Locale locale) {
		APIResponse response = new APIResponse();
		String code = apiException.getErrorCode();
		response.setResultCode(code);
		String message = apiException.getErrorMessage();
		if (message == null)
			response.setResultMessage(handler.getMessage(code, locale));
		else
			response.setResultMessage(handler.getMessage(code, locale) + "(" + message + ")");
		return response;
	}
	
}