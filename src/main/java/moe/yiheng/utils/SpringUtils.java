package moe.yiheng.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringUtils.getApplicationContext() == null) {
            SpringUtils.applicationContext = applicationContext;
        }
    }

    public static Object getBean(String beanId) {
        return applicationContext.getBean(beanId);
    }

}
