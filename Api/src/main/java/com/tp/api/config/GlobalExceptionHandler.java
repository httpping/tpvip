package com.tp.api.config;

import com.tp.api.constant.ReturnCodeEnum;
import com.tp.api.exception.NoFoundExcepiton;
import com.tp.api.service.ApiModelService;
import com.tp.common.bean.BaseResult;
import io.swagger.annotations.ApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.Locale;
import java.util.Set;

@CrossOrigin

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	ApiModelService apiModelService;

	@ExceptionHandler(NoHandlerFoundException.class)
	public String  processNoHandlerException(Exception ex, HttpServletRequest request, HttpServletResponse response){

		String url = request.getRequestURL().toString();
		String path = URI.create(url).getPath();

		com.tp.api.entity.ApiModel apiModel =  apiModelService.selectByUrl(path);

		return  apiModel.getResponse();
	}


	@ExceptionHandler
	public BaseResult processException(Exception ex, HttpServletRequest request, HttpServletResponse response){
		ex.printStackTrace();
		
		if(ex instanceof MissingServletRequestParameterException){
			return new BaseResult(400, ex.getMessage(), null);
		}
		if(ex instanceof NoFoundExcepiton){
	        LOGGER.error("======="+ex.getMessage()+"=======");
			return new BaseResult(401, ex.getMessage(), null);
		}if (ex instanceof NoHandlerFoundException){

			//可以做接口返回处理
			return new BaseResult(404,ex.getMessage(),null);
		}
		
		return new BaseResult(500, ex.getMessage(), null);
		
	}



}
