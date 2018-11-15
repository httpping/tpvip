package com.tp.api.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tp.api.entity.ApiModel;
import com.tp.api.exception.NoFoundExcepiton;
import com.tp.api.mode.BaseResponse;
import com.tp.api.service.ApiModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@CrossOrigin
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Reference
	ApiModelService apiModelService;

	@ExceptionHandler(NoHandlerFoundException.class)
	public String  processNoHandlerException(Exception ex, HttpServletRequest request, HttpServletResponse response){

		String url = request.getRequestURL().toString();
		String path = URI.create(url).getPath();

		 ApiModel apiModel =  apiModelService.selectByUrl(path);

		return  apiModel.getResponse();
	}


	@ExceptionHandler(Exception.class)
	public BaseResponse processException(Exception ex, HttpServletRequest request, HttpServletResponse response){
		ex.printStackTrace();
		
		if(ex instanceof MissingServletRequestParameterException){
			return new BaseResponse(400, ex.getMessage(), null);
		}
		if(ex instanceof NoFoundExcepiton){
	        LOGGER.error("======="+ex.getMessage()+"=======");
			return new BaseResponse(401, ex.getMessage(), null);
		}if (ex instanceof NoHandlerFoundException){

			//可以做接口返回处理
			return new BaseResponse(404,ex.getMessage(),null);
		}
		
		return new BaseResponse(500, ex.getMessage(), null);
		
	}



}
