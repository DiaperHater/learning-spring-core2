package com.val.loggers;

import com.val.beans.Event;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class FileEventLoggerTest {

    FileEventLogger loggerUT;
    String fileName = "testlog.txt";
    Event event;
    Path file;

    @Before
    public void setup() throws IOException {
        loggerUT = new FileEventLogger(fileName);
        file = Paths.get(fileName);

        if (Files.exists(file)) {
            Files.delete(file);
        }

        event = new Event(new Date(), DateFormat.getDateTimeInstance());
    }

    @After
    public void cleanUp() throws IOException {
        if(Files.exists(file)){
            Files.delete(file);
        }
    }



    @Test(expected = InvalidPathException.class)
    public void fileEventLogger_forbiddenFileName_throwsInvalidPathException() throws InvalidPathException, IOException {
        loggerUT = new FileEventLogger("//---\\\\");
    }

    @Test
    public void logEvent_nullArg_noException() throws IOException {
        loggerUT.logEvent(null);
    }

    @Test
    public void logEvent_eventLoggedToFile_fileContainsStringRepresentationOfEvent() throws IOException {

        //action
        loggerUT.logEvent(event);

        //check
        String fileContent = new String(Files.readAllBytes(file));
        String expectedContent = String.format("%s%n", event.toString());

        assertEquals(expectedContent, fileContent);
    }

    @Test
    public void logEvent_3EventsLoggedToFile_fileContainsStringRepresentationOfEvents() throws IOException {
        String expectedFileContent = "";

        loggerUT.logEvent(event);
        expectedFileContent += String.format("%s%n", event);
        event = new Event(new Date(), DateFormat.getDateTimeInstance());

        loggerUT.logEvent(event);
        expectedFileContent += String.format("%s%n", event);
        event = new Event(new Date(), DateFormat.getDateTimeInstance());

        loggerUT.logEvent(event);
        expectedFileContent += String.format("%s%n", event);

        String actualFileContent = new String(Files.readAllBytes(file));

        assertEquals(expectedFileContent, actualFileContent);

    }

}