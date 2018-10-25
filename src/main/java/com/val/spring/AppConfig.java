package com.val.spring;

import com.val.App;
import com.val.beans.Client;
import com.val.beans.EventType;
import com.val.loggers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackageClasses = com.val.App.class)
@PropertySource("classpath:client.properties")
@EnableAspectJAutoProxy
public class AppConfig {

    @Autowired
    Client client;

    @Resource(name = "consoleEventLogger")
    EventLogger consoleEventLogger;

    @Resource(name = "fileEventLogger")
    EventLogger fileEventLogger;

    @Resource(name = "cacheFileEventLogger")
    EventLogger cacheFileEventLogger;

    @Resource(name = "combinedEventLogger")
    EventLogger combinedEventLogger;

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map map = new HashMap();
        map.put(EventType.INFO, consoleEventLogger);
        map.put(EventType.ERROR, combinedEventLogger);

        return map;
    }

}
