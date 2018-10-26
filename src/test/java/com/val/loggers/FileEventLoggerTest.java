package com.val.loggers;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class FileEventLoggerTest {

    FileEventLogger loggerUnderTest;


    @Ignore
    @Test(expected = IOException.class)
    public void logEvent_forbiddenFileName_throwsIOException() {
        loggerUnderTest = new FileEventLogger("//---\\\\");


    }

}