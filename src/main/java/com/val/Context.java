package com.val;

import com.val.spring.AppConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Context {

    public static final AnnotationConfigApplicationContext item =
            new AnnotationConfigApplicationContext(AppConfig.class);

    public static Object getBean(String name) throws BeansException {
        return item.getBean(name);
    }

    public static void registerShutdownHook() {
        item.registerShutdownHook();
    }
}
