package com.ZuLinTong.frame;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

public abstract class BaseController implements ApplicationContextAware {

    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected Object service(String serviceName,Object arg){

        return ((BaseService)applicationContext.getBean(serviceName)).exec(arg);
    }

    protected Object service(String serviceName,Object... arg){

        return ((BaseService)applicationContext.getBean(serviceName)).exec(arg);
    }
}
