package com.val.spring;

import com.val.loggers.ConsoleEventLogger;
import com.val.loggers.EventLogger;
import com.val.loggers.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Configuration
@PropertySource("classpath:logger.properties")
public class LoggerConfig {

    @Autowired
    Environment env;

    @Resource(name = "consoleEventLogger")
    EventLogger consoleEventLogger;

    @Resource(name = "fileEventLogger")
    EventLogger fileEventLogger;

    @Bean
    public List<EventLogger> loggersToCombine() {
        List loggers = new ArrayList();
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);

        return loggers;
    }
}
