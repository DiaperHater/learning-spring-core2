package com.val.spring;

import com.val.App;
import com.val.beans.Client;
import com.val.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = com.val.App.class)
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Autowired
    Client client;

    @Autowired
    @Qualifier("fileEventLogger")
    EventLogger logger;

    @Bean
    public App app() {
        App app = new App(client, logger);

        return app;
    }
}
