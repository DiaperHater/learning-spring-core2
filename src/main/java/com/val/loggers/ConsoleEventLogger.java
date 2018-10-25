package com.val.loggers;

import com.val.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

@Component
public class ConsoleEventLogger implements EventLogger {

    @Value("#{T(java.lang.System).out}")
    PrintStream out;

    @Override
    public void logEvent(Event event) {
        out.println(event.toString());
    }
}
