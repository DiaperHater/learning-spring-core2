package com.val;

import com.val.beans.Client;
import com.val.beans.Event;
import com.val.beans.EventType;
import com.val.loggers.EventLogger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {

    App mut;
    Client client;
    private final int CLIENT_ID = 0;
    private final String CLIENT_NAME = "Johny Boy";
    private final String CLIENT_GREETING = "hello !";
    EventLogger defaultLoggerMock;

    EventLogger infoLoggerMock;
    EventLogger errorLoggerMock;
    Map<EventType, EventLogger> loggerMap;
    Event event;

    @Before
    public void setUp() throws Exception {
        client = new Client(CLIENT_ID, CLIENT_NAME, CLIENT_GREETING);
        defaultLoggerMock = mock(EventLogger.class);

        infoLoggerMock = mock(EventLogger.class);
        errorLoggerMock = mock(EventLogger.class);

        loggerMap = new HashMap<>();
        loggerMap.put(EventType.INFO, infoLoggerMock);
        loggerMap.put(EventType.ERROR, errorLoggerMock);

        mut = new App(client, defaultLoggerMock, loggerMap);

        event = new Event("From: " + CLIENT_ID, null, new Date(), DateFormat.getDateTimeInstance());
    }

    @Test
    public void logEvent_loggerMapIsNull_loggingToDefaultLogger() {
        //setup
        mut.setLoggerMap(null);

        //action try different types
        event.setType(EventType.INFO);
        mut.logEvent(event);

        event.setType(EventType.ERROR);
        mut.logEvent(event);

        event.setType(null);
        mut.logEvent(event);

        //check
        verify(defaultLoggerMock, times(3)).logEvent(any());
    }

    @Test
    public void logEvent_loggerMapHasNoEntryForEventType_loggingToDefaultLogger() {
        //setup
        event.setType(EventType.ERROR);
        loggerMap.remove(EventType.ERROR);

        //action
        mut.logEvent(event);

        //check
        verify(defaultLoggerMock).logEvent(event);
    }

    @Test
    public void logEvent_loggerMapHasEntryForEventType_loggingToAppropriateLogger() {
        //setup and action
        event.setType(EventType.ERROR);
        mut.logEvent(event);

        event.setType(EventType.INFO);
        mut.logEvent(event);

        //check
        verify(errorLoggerMock).logEvent(event);
        verify(infoLoggerMock).logEvent(event);
    }

    @Test
    public void logEvent_eventMessageContainsClientId_clientIdReplacedByClientName() {
        //setup
        event.setMsg("bla " + CLIENT_ID);

        //action
        mut.logEvent(event);

        //check
        assertTrue(event.getMsg().contains(CLIENT_NAME));
    }

    @Test
    public void logEvent_eventMessageContainsClientId_clientGreetingAddedToTheEndOfClientsName() {
        //setup
        event.setMsg("bla " + CLIENT_ID);

        //action
        mut.logEvent(event);

        //check
        assertTrue(event.getMsg().contains(CLIENT_GREETING));
    }
}