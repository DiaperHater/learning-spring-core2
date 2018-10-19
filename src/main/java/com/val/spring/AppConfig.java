package com.val.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = com.val.App.class)
@PropertySource("classpath:client.properties")
public class AppConfig {
}
