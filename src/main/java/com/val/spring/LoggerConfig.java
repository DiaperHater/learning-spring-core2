package com.val.spring;

import com.val.beans.Event;
import com.val.loggers.ConsoleEventLogger;
import com.val.loggers.EventLogger;
import com.val.loggers.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@PropertySource("classpath:logger.properties")
public class LoggerConfig {

    @Autowired
    Environment env;

    @Bean
    public EventLogger consoleEventLogger() {
        EventLogger logger = new ConsoleEventLogger();

        return logger;
    }

    @Bean
    public EventLogger fileEventLogger() {

        Path file = Paths.get(env.getProperty("fileName"));

        EventLogger logger = new FileEventLogger(file);

        return logger;
    }
}
