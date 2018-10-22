package com.val;

import com.val.beans.Client;
import com.val.beans.Event;
import com.val.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class App {

    @Autowired
    Client client;

    @Autowired
    EventLogger logger;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(com.val.spring.AppConfig.class);
        context.refresh();

        App app = (App) context.getBean("app");
        Event e = (Event) context.getBean("event");
        e.setMsg("event msg for 1");

        app.logEvent(e);
    }

    public void logEvent(Event event) {

        String message = event.getMsg().replace(String.valueOf(client.getId()), client.getName());
        event.setMsg(message);

        logger.logEvent(event);
    }
}
