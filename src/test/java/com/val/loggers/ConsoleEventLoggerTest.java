package com.val.loggers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class ConsoleEventLoggerTest {

    @Autowired
    ConsoleEventLogger loggerUnderTest;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void logEvent() {
    }
}