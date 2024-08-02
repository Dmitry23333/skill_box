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
public class BriefTaskResponse {
    private String id;
    private String name;
    private String description;
    private Task.TaskStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private String authorId;
    private String assigneeId;
    private Set<String> observerIds;
}
