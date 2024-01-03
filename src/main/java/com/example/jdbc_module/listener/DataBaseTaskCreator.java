package com.example.jdbc_module.listener;


import com.example.jdbc_module.Task;
import com.example.jdbc_module.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.Lifecycle;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.desktop.AppReopenedEvent;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataBaseTaskCreator {
    private final TaskService taskService;

    @EventListener(ApplicationStartedEvent.class)
    public void createTaskData() {
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int value = i + 1;
            Task task = new Task();
            task.setId(value);
            task.setTitle("Title" + value);
            task.setDescription("Description" + value);
            task.setPriority(value);
            tasks.add(task);
        }
        taskService.batchInsert(tasks);
    }
}
