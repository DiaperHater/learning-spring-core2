package com.val.loggers;

import com.val.beans.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CombinedEventLoggerTest {

    CombinedEventLogger mut;

    @Test
    public void allInnerLoggersAreUsed() {
        List<EventLogger> loggers = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            loggers.add(mock(EventLogger.class));
        }

        mut = new CombinedEventLogger(loggers);
        mut.logEvent(new Event(new Date(), DateFormat.getDateTimeInstance()));

        loggers.stream().forEach(logger -> verify(logger).logEvent(any()));
    }

}