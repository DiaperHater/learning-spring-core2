package com.val.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("prototype")
public class Event {
    static final AtomicInteger integer = new AtomicInteger(0);
    final int id;
    String msg;

    @Value("#{new java.util.Date()}")
    Date date;

    @Value("#{T(java.text.DateFormat).getDateTimeInstance()}")
    DateFormat dateFormat;

    public Event() {
        id = integer.getAndIncrement();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }

    public String getMsg() {
        return msg;
    }
}
