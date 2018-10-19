package com.val;

import com.val.beans.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    Client client;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
        App app = (App) context.getBean("app");

        app.printClient();
    }

    @Autowired
    public void setClient(Client client) {
        this.client = client;
    }

    public void printClient(){
        String s = String.format("%s with id: %d says %s%n",
                client.getName(),
                client.getId(),
                client.getGreeting());

        System.out.println(s);
    }
}
