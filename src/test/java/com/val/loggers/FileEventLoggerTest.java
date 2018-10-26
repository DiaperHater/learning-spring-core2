package com.val.loggers;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class FileEventLoggerTest {

    FileEventLogger loggerUnderTest;

    @Test(expected = IOException.class)
    public void init_fileNotWritable_throwIOException() throws IOException {
        loggerUnderTest = new FileEventLogger("anyFileName.txt");

        new MockUp<Files>() {
            @Mock
            public boolean exists(Path path, LinkOption... options){
                return true;
            }

            @Mock
            public boolean isWritable(Path path){
                return false;
            }
        };

        loggerUnderTest.init();
    }

}