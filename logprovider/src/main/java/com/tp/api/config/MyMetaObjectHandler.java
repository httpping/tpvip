package com.tp.api.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.tp.api.ProviderApplication;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  注入公共字段自动填充,任选注入方式即可
 */
//@Component
public class MyMetaObjectHandler extends MetaObjectHandler {

    protected final static Logger logger = LoggerFactory.getLogger(ProviderApplication.class);

    @Override
    public void insertFill(MetaObject metaObject) {

        logger.info("新增的时候干点不可描述的事情");
       /* Object update_time = metaObject.getValue("updateTime");
        Object created_time = metaObject.getValue("createdTime");
        //获取当前登录用户
        if (null == created_time ) {
            metaObject.setValue("createdTime", System.currentTimeMillis()/1000);
        }
        if (null == update_time) {
            metaObject.setValue("updateTime", System.currentTimeMillis()/1000);
        }*/

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("更新的时候干点不可描述的事情");
      /*  Object update_time = metaObject.getValue("updateTime");
        Object created_time = metaObject.getValue("createdTime");
        //获取当前登录用户
        if (null == created_time) {
            metaObject.setValue("createdTime", System.currentTimeMillis()/1000);
        }
        if (null == update_time) {
            metaObject.setValue("updateTime", System.currentTimeMillis()/1000);
        }*/
    }
}
