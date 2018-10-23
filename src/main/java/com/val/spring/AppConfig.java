package com.val.spring;

import com.val.App;
import com.val.beans.Client;
import com.val.beans.EventType;
import com.val.loggers.CacheFileEventLogger;
import com.val.loggers.ConsoleEventLogger;
import com.val.loggers.EventLogger;
import com.val.loggers.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackageClasses = com.val.App.class)
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Autowired
    Client client;

    @Autowired
    ConsoleEventLogger consoleEventLogger;

    @Autowired
    FileEventLogger fileEventLogger;

    @Autowired
    CacheFileEventLogger cacheFileEventLogger;

    @Bean
    public App app() {
        App app = new App(client, consoleEventLogger);

        return app;
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map map = new HashMap();
        map.put(EventType.INFO, consoleEventLogger);
        map.put(EventType.ERROR, fileEventLogger);

        return map;
    }

//    @Bean
//    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
//        return  new PropertyPlaceholderConfigurer();
//    }
}
