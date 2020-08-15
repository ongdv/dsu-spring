package com.project.dsu.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class MessageManager {
	@Autowired
	MessageSource messageSource;
	
	//요청 데이터를 저장합니다.
	private Object data;
	
	public String getMessage(String code, Locale locale) {
		return messageSource.getMessage(code, null, locale);
	}
	
	public String getMessage(String code, String defaultStr) {
		
		return getMessage(code, Locale.KOREA, defaultStr); 
	}
	
	public String getMessage(String code, String defaultStr, String langCode) {
		
		Locale locale = Locale.KOREA;
		if(langCode != null) {
			switch (langCode) {
			case "kr":
				locale = Locale.KOREA;
				break;
			case "en":
				locale = Locale.US;
				break;
			case "jp":
				locale = Locale.JAPAN;
				break;
			case "cn":
				locale = Locale.CHINA;
				break;
			default:
				locale = Locale.KOREA;
				break;
			}
		}		
		
		return getMessage(code, locale, defaultStr); 
	}		
	
	public String getMessage(String code, Locale locale, String defaultStr) {
		
		String resultStr = (defaultStr) != null ? defaultStr : "";
		
		try {
			resultStr = messageSource.getMessage(code, null, locale);
		} catch (Exception e) {
			
		}
		
		return resultStr; 
	}
	
	/**
	 * 다국어 처리용 메소드입니다.
	 */
	public String getMessage(String code, Object[] args, Locale locale) {
		return messageSource.getMessage(code, args, null, locale);
	}
	
//	/**
//	 * 다국어를 처리하기 위하여 사용자의 landCode에 따른 locale을 구하는 메소드입니다.
//	 * @param sessionInfo
//	 * @return
//	 */
//	public static Locale checkLocale(Object data) {
//		Locale locale = Locale.KOREA;
//		if(data != null) {
//			switch () {
//			case "kr":
//				locale = Locale.KOREA;
//				break;
//			case "en":
//				locale = Locale.US;
//				break;
//			case "jp":
//				locale = Locale.JAPAN;
//				break;
//			case "cn":
//				locale = Locale.CHINA;
//				break;
//			default:
//				locale = Locale.KOREA;
//				break;
//			}
//		}
//		return locale;
//	}
}
