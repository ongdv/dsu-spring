package com.project.dsu.handler.mobile;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import com.project.dsu.handler.AbstractAPIHandler;

@Component("mobile")
public class MobileHandler extends AbstractAPIHandler{

	public MobileHandler() {
		System.out.print("############### Create MobileHandler ###############");
	}

	@Override
	public String getPid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServiceName() {
		// TODO Auto-generated method stub
		return "Mobile";
	}

	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object handle(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			Map<String, Object> request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
