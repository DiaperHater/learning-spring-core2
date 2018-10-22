package com.val.loggers;

import com.val.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {

    List<Event> cache;

    @Value("${cacheCapacity}")
    int cacheCapacity;

    public CacheFileEventLogger() {
    }

    public CacheFileEventLogger(String fileName, int cacheCapacity) {
        super(fileName);
        this.cacheCapacity = cacheCapacity;
    }

    @PostConstruct
    public void init() {
        cache = new ArrayList<>(cacheCapacity);
    }

    @PreDestroy
    public void destroy() {
        if (cache != null) {
            writeCacheToAFile();
        }
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if(cache.size() == cacheCapacity){
            writeCacheToAFile();
        }
    }

    private void writeCacheToAFile() {
        cache.stream().forEach(super::logEvent);
    }
}
