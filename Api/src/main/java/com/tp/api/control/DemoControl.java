package com.tp.api.control;


import com.tp.api.constant.ReturnCodeEnum;
import com.tp.common.bean.BaseResult;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@Slf4j
@RestController
public class DemoControl {


    @Autowired
    private MessageSource messageSource;

    @PostMapping("/hello")
    public BaseResult sayHello() throws NotFoundException {
        log.info("hello world!");
        BaseResult baseResult = new BaseResult();
        baseResult.data = "hello word";
        String msg1 = this.messageSource.getMessage("message.order.stockout", null, Locale.CHINA);

        baseResult.message = ReturnCodeEnum.CODE_200.getValue();
//        throw  new NotFoundException(msg1);
        return  baseResult;
    }
}
