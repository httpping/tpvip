package com.tp.api.exception;

import com.tp.api.constant.ReturnCodeEnum;
import com.tp.api.mode.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/***
 *  http 普通错误抓取
 */
@Slf4j
@RestController
public class RestNotFoundFilter implements ErrorController {
    @Autowired
    private MessageSource messageSource;


    private static final String NOT_FOUND = "404";
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public BaseResponse handleValidationException(HttpServletRequest request) {
        Locale locale = LocaleContextHolder.getLocale();
        return BaseResponse.created(ReturnCodeEnum.CODE_404.getCode(),messageSource.getMessage(ReturnCodeEnum.CODE_404.getValue(), null, locale),request.getRequestURL().toString() );
    }

    // 通用异常的处理，返回500
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handleException(Exception ex) {
        return BaseResponse.created(ReturnCodeEnum.CODE_500.getCode(),messageSource.getMessage("message.order.stockout", null, Locale.CHINA) );

    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}