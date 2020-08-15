package com.project.dsu.handler;

import java.util.Locale;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ResourceBundleMessageSource;
public abstract class AbstractAPIHandler implements APIHandler, InitializingBean {


	private ResourceBundleMessageSource messageSource = null;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	@Override
	public String getMessageBaseName() {
		return null;
	
	}
	
	@Override
	public String getMessage(String code, Locale locale) {
		return messageSource.getMessage(code, null, locale);
	}
	
	@Override
	public String getMessage(String code, Object[] args, Locale locale) {
		return messageSource.getMessage(code, args, null, locale);
	}
}
