package com.wilson.feelings.webArgumentResolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PersonWebArgumentResolver implements HandlerMethodArgumentResolver{

	Log logger = LogFactory.getLog(getClass());

	@Override
	public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1, NativeWebRequest arg2,
			WebDataBinderFactory arg3) throws Exception {
		String message = "FROM THE PersonWEbArgumnetresolver";
		System.out.println(message);
		logger.debug(message);
		return null;
	}
	@Override
	public boolean supportsParameter(MethodParameter arg0) {
		String message = "FROM THE PersonWEbArgumnetresolver";
		System.out.println(message);
		logger.debug(message);
		return false;
	}

}
