/*
package com.tp.api.config;

import com.tp.api.constant.ReturnCodeEnum;
import com.tp.common.bean.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Locale;
import java.util.Set;

@RestController
public class ControllerWithExceptionHandling {

    @Autowired
    private MessageSource messageSource;

    // 异常处理方法：
    // 根据特定的异常返回指定的 HTTP 状态码
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public BaseResult handleValidationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : errors) {
            strBuilder.append(violation.getMessage() + "\n");
        }
        return BaseResult.created(ReturnCodeEnum.CODE_404.getCode(),messageSource.getMessage("message.order.stockout", null, Locale.CHINA) );
    }

    // 通用异常的处理，返回500
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult handleException(Exception ex) {
        return BaseResult.created(ReturnCodeEnum.CODE_500.getCode(),messageSource.getMessage("message.order.stockout", null, Locale.CHINA) );

    }
}
*/
