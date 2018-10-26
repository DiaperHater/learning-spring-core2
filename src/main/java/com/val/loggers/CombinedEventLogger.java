package com.val.loggers;

import com.val.beans.Event;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class CombinedEventLogger implements EventLogger{

    @Resource(name = "loggersToCombine")
    List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.stream().forEach(logger -> logger.logEvent(event));
    }
}
