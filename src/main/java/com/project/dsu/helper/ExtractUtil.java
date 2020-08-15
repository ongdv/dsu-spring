package com.project.dsu.helper;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dsu.constant.CommonConstants;
import com.project.dsu.util.CommonUtil;
import com.project.dsu.util.RequestInfo;
import com.project.dsu.util.URLParserUtil;


public class ExtractUtil {
	/**
	 * 헤더 정보 추출 함수
	 * 
	 * @param req
	 * @param apiName 
	 * @param handlerName 
	 * @return
	 * @throws IOException 
	 */
	public static  RequestInfo extractHeader(HttpServletRequest req, HttpServletResponse res, String handlerName, String apiName) throws IOException {
		String ip = extractClientIp(req);
		String method = handlerName+"/"+apiName;
		String transactionId = req.getHeader(CommonConstants.REQUEST_HEADER_TRANSACTION_ID);
		String referer = req.getHeader(CommonConstants.REQUEST_HEADER_REFERER);
		String userAgent = req.getHeader(CommonConstants.REQUEST_HEADER_USER_AGENT);
		String timeStamp = req.getHeader(CommonConstants.REQUEST_HEADER_TIMESTAMP);
		String clientId  = req.getHeader(CommonConstants.REQUEST_HEADER_CLIENT_ID);
		RequestInfo reqInfo = new RequestInfo();
		reqInfo.setIp(ip);
		reqInfo.setMethod(method);
		reqInfo.setTransactionId(CommonUtil.nullCheck(transactionId));
		reqInfo.setReferer(CommonUtil.nullCheck(referer));
		reqInfo.setUserAgent(CommonUtil.nullCheck(userAgent));
		reqInfo.setTimeStamp(CommonUtil.nullCheck(timeStamp));
		reqInfo.setClientId(clientId);
		reqInfo.setApiCode(URLParserUtil.getApiCode(req));
		reqInfo.setUri(req.getRequestURI());	
		return reqInfo;
	}

	/**
	 * Client IP 추출 메소드
	 * 
	 * @param req
	 * @return
	 */
	public static String extractClientIp(HttpServletRequest req) {

		// Clinet IP 추출 순위
		// 1. X-Forwarded-For
		// 2. Proxy-Client-IP
		// 3. WL-Proxy-Client-IP
		// 4. HTTP_CLIENT_IP
		// 5. HTTP_X_FORWARDED_FOR
		// 6. X-real-IP
		// 7. X-realIP
		// 8. getRemoteAddr()

		String ClientIp = null;

		String ip = req.getHeader(CommonConstants.REQUEST_HEADER_X_FORWARDED_FOR);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = req.getHeader(CommonConstants.REQUEST_HEADER_PROXY_CLIENT_IP);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = req.getHeader(CommonConstants.REQUEST_HEADER_WL_PROXY_CLIENT_IP);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = req.getHeader(CommonConstants.REQUEST_HEADER_HTTP_CLIENT_IP);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = req.getHeader(CommonConstants.REQUEST_HEADER_HTTP_X_FORWARDED_FOR);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = req.getHeader(CommonConstants.REQUEST_HEADER_X_REAR_IP);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = req.getHeader(CommonConstants.REQUEST_HEADER_X_REALIP);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = req.getRemoteAddr();

		// 다수개 존재시 첫번째로 처리를 위함. X-Forwarded-For: client, proxy1, proxy2
		if (ip != null && ip.split(",").length > 0) {
			String[] ipArr = ip.split(",");
			ClientIp = ipArr[0];
		}

		return ClientIp;
	}

}
