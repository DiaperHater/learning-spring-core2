package com.val.loggers;

import com.val.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileEventLogger implements EventLogger {

    Path file;

    public FileEventLogger(Path file) {
        this.file = file;
    }

    @Override
    public void logEvent(Event event) {
        try {
            byte[] bytes = String.format("%s%n", event).getBytes();
            Files.write(file, bytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
