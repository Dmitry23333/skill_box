package com.example.flux.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String name;
    private String description;
    private TaskStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private String authorId;
    private String assigneeId;
    private Set<String> observerIds;
    @ReadOnlyProperty
    private User author;
    @ReadOnlyProperty
    private User assignee;
    @ReadOnlyProperty
    private Set<User> observers;

    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        DONE
    }
}
