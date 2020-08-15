package com.project.dsu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dsu.constant.Constant;
import com.project.dsu.helper.APIResponse;
import com.project.dsu.helper.ExtractUtil;
import com.project.dsu.model.RequestModel;
import com.project.dsu.service.APIService;
import com.project.dsu.util.CommonUtil;
import com.project.dsu.util.RequestInfo;


@Controller
public class MainController {
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	APIService service;
	

	
	@RequestMapping("/hello")
	public String hello() {
		return "/WEB-INF/views/hello.jsp";
	}
	
	
	/**
	 * Common API 처리
	 * 
	 * @param servletRequest
	 * @param servletResponse
	 * @param request
	 * @param handlerName
	 * @param apiName
	 * @return Map(JSON)
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping(value = Constant.URI_CONTEXT_COMMON_API + "{handlerName}" + "/" + "{apiName}", method = {
			RequestMethod.GET, RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object api(HttpServletRequest servletRequest, HttpServletResponse servletResponse,

			@RequestParam Map<String, Object> request, @PathVariable String handlerName, @PathVariable String apiName) throws IOException {

		long time = System.currentTimeMillis();
		RequestInfo requestInfo = ExtractUtil.extractHeader(servletRequest, servletResponse,handlerName,apiName);
		
		if(request instanceof Map) {
			Map map = (Map) request;
			map.put("serverAddr", CommonUtil.getServerAddr(servletRequest, servletResponse));
			map.put("requestInfo" , requestInfo);
			map.put("apiName", apiName);
		}
		logger.info(Constant.URI_CONTEXT_COMMON_API + "[ Request ] =>" + request);

		Object key = service.action(servletRequest, servletResponse, request,
				Constant.URI_CONTEXT_COMMON_API.replace("/", "") + handlerName);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resultData", key);
		time = System.currentTimeMillis() - time;
		logger.info(Constant.URI_CONTEXT_COMMON_API + "[ apiName ] =>" + apiName + "## END TIME : [" + time + "]");
		return result;

	}
}
