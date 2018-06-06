package com.tp.api.config;

import com.tp.api.exception.NoFoundExcepiton;
import com.tp.common.bean.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler
	public BaseResult processException(Exception ex, HttpServletRequest request, HttpServletResponse response){
		ex.printStackTrace();
		
		if(ex instanceof MissingServletRequestParameterException){
			return new BaseResult(400, ex.getMessage(), null);
		}
		if(ex instanceof NoFoundExcepiton){
	        LOGGER.error("======="+ex.getMessage()+"=======");
			return new BaseResult(401, ex.getMessage(), null);
			
		}
		
		return new BaseResult(500, ex.getMessage(), null);
		
	}
}
