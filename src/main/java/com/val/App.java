package com.val;

import com.val.beans.Client;
import com.val.beans.Event;
import com.val.beans.EventType;
import com.val.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class App {

    @Autowired
    Client client;

    @Autowired
    @Qualifier("consoleEventLogger")
    EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    Map<EventType, EventLogger> loggers;

    public App() {
    }

    public App(Client client, EventLogger defaultLogger, Map loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(com.val.spring.AppConfig.class);
//        context.refresh();

        App app = (App) Context.getBean("app");
        Event e = (Event) Context.getBean("event");
        e.setMsg("event msg for 1");
        e.setType(EventType.ERROR);

        app.logEvent(e);

        Context.registerShutdownHook();
    }

    public void logEvent(Event event) {

        String message = event.getMsg().replace(String.valueOf(client.getId()), client.getName());
        event.setMsg(message);

        if(loggers == null){
            defaultLogger.logEvent(event);
            System.out.println("##############++++++++++++++##########################");
            return;
        }
        EventLogger logger = loggers.get(event.getType());

        if(logger == null){
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
