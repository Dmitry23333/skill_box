package com.example.studentslist.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHolderListener {

    @EventListener
    public void add(EventHolderAdd eventHolder) {
        System.out.println("Student was added");
        System.out.println(eventHolder.getStudent());
    }

    @EventListener
    public void remove(EventHolderRemove eventHolder) {
        System.out.println("Student was removed");
        System.out.println(eventHolder.getStudent());
    }


    @EventListener
    public void init(EventHolderStartApp eventHolder) {
        System.out.println("Enabling init is " + eventHolder.getMessage());
    }
}
