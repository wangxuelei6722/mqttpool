package com.wangxl.mqttpool.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

/**   
 * @ClassName:  SpringUtil   
 * @Description: 获取上下文
 * @author: Wangxuelei
 * @date:   2022年1月13日 下午2:45:49      
 * @Copyright:  
 */
@Component
public class SpringUtil extends ApplicationObjectSupport {
	
	public static ApplicationContext context;
	    
    public static Object getBean(String serviceName){
        return context.getBean(serviceName);
    }
    
    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        super.initApplicationContext(context);
        SpringUtil.context = context;      
    }
	

}
