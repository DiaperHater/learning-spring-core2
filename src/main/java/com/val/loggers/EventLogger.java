package com.val.loggers;

import com.val.beans.Event;
import org.springframework.stereotype.Component;

public interface EventLogger {
    void logEvent(Event event);
}
