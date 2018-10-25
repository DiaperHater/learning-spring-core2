package com.val.loggers;

import com.val.beans.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.stereotype.Component;
import java.io.PrintStream;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@Component
public class ConsoleEventLoggerTest {


    @Mock
    PrintStream printStream;

    @InjectMocks
    ConsoleEventLogger loggerUnderTest;


    @Test
    public void logEvent() {

        Event eventMock = mock(Event.class);
        when(eventMock.toString()).thenReturn("");

        loggerUnderTest.logEvent(eventMock);

        verify(printStream).println(anyString());
    }
}