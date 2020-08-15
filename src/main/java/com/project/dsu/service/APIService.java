package com.project.dsu.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dsu.exception.APIException;
import com.project.dsu.handler.APIHandler;
import com.project.dsu.handler.HandlerManager;
import com.project.dsu.helper.APIResponse;
import com.project.dsu.helper.ResponseHelper;


/*
	* APIService Class
	* 진입 시점 elasticsearch / redis setting 및 Handler 분기
	* @author  Pakerbb
	* @version 1.0
*/

@Service("aPIService")
public class APIService implements InitializingBean {
	@Autowired
	private HandlerManager handlerManager;


	private Logger logger = LogManager.getLogger(this.getClass());


	/**
	 * ServiceName에 따라 handler 분기 처리
	 * 
	 * @param servletRequest
	 * @param servletResponse
	 * @param request
	 * @param serviceName
	 * @param apiName
	 * @return APIResponse response
	 */
	public Object action(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			Map<String, Object> request, String serviceName) {
		long time = System.currentTimeMillis();
		String apiName = (String) request.get("apiName");
		APIHandler handler = handlerManager.get(serviceName);
		APIResponse response = null;
		if (handler == null) {
			response = new APIResponse();
			response.setResultCode("HANDLER001");
			response.setResultMessage("invalid service : " + serviceName);
			return response;
		}
		try {
			Map<String, Object> header = (Map<String, Object>) request.get("header");
			Map<String, Object> body = (Map<String, Object>) request.get("body");
			logger.info(serviceName + "." + apiName + "-start " + request);
			Object result = handler.handle(servletRequest, servletResponse, request);
			response = ResponseHelper.createSuccess(result);
			time = System.currentTimeMillis() - time;
			logger.info(serviceName + "." + apiName + "-end ET[" + time + "] " + request + response);
		} catch (APIException ae) {

			time = System.currentTimeMillis() - time;
			logger.error(serviceName + "." + apiName + "-error ET[" + time + "] " + request + response + ae);
		} catch (Exception e) {
			time = System.currentTimeMillis() - time;
			logger.error(serviceName + "." + apiName + "-error ET[" + time + "] " + request + response + e);
		}
		return response;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
