package com.val.loggers;

import com.val.beans.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class CacheFileEventLoggerTest {

    String fileName = "testcachedlog.txt";
    int cacheSize = 2;
    Path file;
    CacheFileEventLogger mut;
    Event event;


    @Before
    public void setUp() throws Exception {
        file = Paths.get(fileName);
        if(Files.exists(file)){
            Files.delete(file);
        }

        mut = new CacheFileEventLogger(fileName, cacheSize);
        mut.init();

        event = new Event(new Date(), DateFormat.getDateTimeInstance());
    }

    @After
    public void tearDown() throws Exception {
        if(Files.exists(file)){
            Files.delete(file);
        }
    }

    @Test
    public void logEvent_cacheNotFull_noWriteToFile() throws IOException {

        //cacheSize == 2
        mut.logEvent(event);
        assertTrue(Files.exists(file) ? Files.size(file) == 0 : true);
    }

    @Test
    public void logEvent_cacheFull_writeToFile() throws IOException {
        //cacheSize == 2
        mut.logEvent(event);
        mut.logEvent(event);

        assertTrue(Files.size(file) != 0);
    }


    @Test
    public void destroy_cacheIsNotEmpty_writeToFile() throws IOException {
        //cacheSize == 2
        mut.logEvent(event);
        mut.destroy();

        assertTrue(Files.size(file) != 0);
    }
}