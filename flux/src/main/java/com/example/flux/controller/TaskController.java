package com.example.flux.controller;

import com.example.flux.mapper.TaskMapper;
import com.example.flux.service.TaskService;
import com.example.flux.web.requset.UpsertTaskRequest;
import com.example.flux.web.response.BriefTaskResponse;
import com.example.flux.web.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','MANAGER')")
    public Flux<TaskResponse> findAll() {
        return taskService.findAll().map(taskMapper::taskToResponse);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','MANAGER')")
    public Mono<ResponseEntity<TaskResponse>> getById(@PathVariable String id) {
        return taskService.findById(id)
                .map(taskMapper::taskToResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public Mono<ResponseEntity<BriefTaskResponse>> createTask(@RequestBody UpsertTaskRequest taskRequest) {
        return taskService.save(taskMapper.requestToTask(taskRequest))
                .map(taskMapper::upsertTaskToResponse)
                .map(ResponseEntity::ok);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public Mono<ResponseEntity<BriefTaskResponse>> updateTask(@PathVariable String id, @RequestBody UpsertTaskRequest taskRequest) {
        return taskService.update(id, taskMapper.requestToTask(taskRequest))
                .map(taskMapper::upsertTaskToResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping("/addObs/{taskId}")
    @PreAuthorize("hasAnyRole('ROLE_USER','MANAGER')")
    public Mono<ResponseEntity<BriefTaskResponse>> addObserverToTask(@PathVariable String taskId, @Param("obsId") String obsId) {
        return taskService.addObserver(taskId, obsId)
                .map(taskMapper::upsertTaskToResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return taskService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }
}
