package com.example.flux.web.requset;

import com.example.flux.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertTaskRequest {
    private String name;
    private String description;
    private Task.TaskStatus status;
    private String authorId;
    private String assigneeId;
    private Set<String> observerIds;
}
