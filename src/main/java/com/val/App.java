package com.val;

import com.val.beans.Client;
import com.val.beans.Event;
import com.val.beans.EventType;
import com.val.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class App {

    Client client;
    EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger) {
        this.client = client;
        this.defaultLogger = defaultLogger;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(com.val.spring.AppConfig.class);
        context.refresh();

        App app = (App) context.getBean("app");
        Event e = (Event) context.getBean("event");
        e.setMsg("event msg for 1");
        e.setType(EventType.ERROR);

        app.logEvent(e);

        context.registerShutdownHook();
    }

    public void logEvent(Event event) {

        String message = event.getMsg().replace(String.valueOf(client.getId()), client.getName());
        event.setMsg(message);

        EventLogger logger = loggers.get(event.getType());

        if(logger == null){
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
