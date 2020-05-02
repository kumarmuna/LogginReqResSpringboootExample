package com.javainuse.interceptor.config;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@ControllerAdvice
public class LoggingInterceptor extends RequestBodyAdviceAdapter {
	Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HttpServletRequest httpServletRequest;
    
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, 
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }
    
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
                                MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	log.info("Request Type: "+httpServletRequest.getMethod());
        log.info("Request posted: "+ jsonString);
        
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
