package org.example;

import org.example.config.DefaultAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DefaultAppConfig.class);
        applicationContext.getBean(ContactsWorker.class).initFile();
        applicationContext.getBean(ContactsWorker.class).addContact();
        applicationContext.getBean(ContactsWorker.class).printContacts();
        applicationContext.getBean(ContactsWorker.class).saveContactsToFile();
    }
}
