package com.tp.api.exception;

import com.tp.api.constant.ReturnCodeEnum;
import com.tp.common.bean.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/***
 *  http 普通错误抓取
 */
@RestController
public class RestNotFoundFilter implements ErrorController {
    @Autowired
    private MessageSource messageSource;


    private static final String NOT_FOUND = "404";
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public BaseResult handleValidationException() {
        Locale locale = LocaleContextHolder.getLocale();
        return BaseResult.created(ReturnCodeEnum.CODE_404.getCode(),messageSource.getMessage(ReturnCodeEnum.CODE_404.getValue(), null, locale) );
    }

    // 通用异常的处理，返回500
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult handleException(Exception ex) {
        return BaseResult.created(ReturnCodeEnum.CODE_500.getCode(),messageSource.getMessage("message.order.stockout", null, Locale.CHINA) );

    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}