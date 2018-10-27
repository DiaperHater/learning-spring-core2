package com.val.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Client {
    int id;
    String name;
    String greeting;

    public Client(int id, String name, String greeting) {
        this.id = id;
        this.name = name;
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }

    @Value("${greeting}")
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public int getId() {
        return id;
    }

    @Value("${id}")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Value("${name}")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                '}';
    }
}
