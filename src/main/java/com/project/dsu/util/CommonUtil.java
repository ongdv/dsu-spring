package com.project.dsu.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonUtil {
	public static String getServerAddr(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

		String serverName = servletRequest.getServerName();
		int serverPort = servletRequest.getServerPort();
		String serverAddr = null;

		// http, https 구분
		if(servletRequest.isSecure()) {
			serverAddr = "https://";
		} else {
			serverAddr = "http://";
		}

		serverAddr += (serverName.equals("localhost") ? "dsu.co.kr" : serverName) +
					  (serverPort == 80 ? "" : (serverPort == 8080 || serverPort == 8081 ? "" : ":" + serverPort));

		return serverAddr;
	}
	
	/**
	 * 문자열 null check
	 * 
	 * @return
	 */
	public static String nullCheck(String value) {
		if (value == null)
			value = "";
		return value;
	}

}
