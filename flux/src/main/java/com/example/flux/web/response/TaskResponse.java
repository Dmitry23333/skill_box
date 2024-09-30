package com.example.flux.web.response;

import com.example.flux.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private String id;
    private String name;
    private String description;
    private Task.TaskStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private UserResponse author;
    private UserResponse assignee;
    private Set<UserResponse> observers;
}
