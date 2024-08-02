package com.example.flux.service;

import com.example.flux.entity.Task;
import com.example.flux.entity.User;
import com.example.flux.exception.AddObserverException;
import com.example.flux.exception.UpdateErrorException;
import com.example.flux.repository.TaskRepository;
import com.example.flux.repository.UserRepository;
import com.mongodb.MongoNodeIsRecoveringException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.*;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Flux<Task> findAll() {
        Flux<Task> tasks = taskRepository.findAll();
        Flux<User> author = tasks.flatMap(x -> userRepository.findUserById(x.getAuthorId()));
        Flux<User> assignee = tasks.flatMap(x -> userRepository.findUserById(x.getAssigneeId()));
        Flux<List<User>> observers = tasks.flatMap(user -> {
            Set<Mono<User>> monoSet = new HashSet<>();
            for (String observerId : user.getObserverIds()) {
                monoSet.add(userRepository.getUserById(observerId));
            }
            Flux<User> merged = Flux.empty();
            for (Mono<User> out : monoSet) {
                merged = merged.mergeWith(out);
            }
            return merged.collectList();
        });
        return Flux.zip(tasks, author, assignee, observers).
                flatMap(dFlux -> Flux.just(new Task(dFlux.getT1().getId(),
                        dFlux.getT1().getName(),
                        dFlux.getT1().getDescription(),
                        dFlux.getT1().getStatus(),
                        dFlux.getT1().getCreatedAt(),
                        dFlux.getT1().getUpdatedAt(),
                        dFlux.getT1().getAuthorId(),
                        dFlux.getT1().getAssigneeId(),
                        dFlux.getT1().getObserverIds(),
                        dFlux.getT2(),
                        dFlux.getT3(),
                        new HashSet<>(dFlux.getT4()))));
    }

    public Mono<Task> findById(String id) {
        return Mono.from(findAll().filter(x -> x.getId().equals(id)));
    }

    public Mono<Task> save(Task task) {
        task.setId(UUID.randomUUID().toString());
        task.setCreatedAt(Instant.now());
        return taskRepository.save(task);
    }

    public Mono<Task> update(String id, Task task) {
        return findById(id).flatMap(taskForUpdate -> {
            taskForUpdate.setName(task.getName());
            taskForUpdate.setDescription(task.getDescription());
            taskForUpdate.setStatus(task.getStatus());
            taskForUpdate.setUpdatedAt(Instant.now());
            taskForUpdate.setAuthorId(task.getAuthorId());
            taskForUpdate.setAssigneeId(task.getAssigneeId());
            if (task.getObserverIds().size() == taskForUpdate.getObserverIds().size()) {
                taskForUpdate.setObserverIds(task.getObserverIds());
            } else {
                return Mono.error(new UpdateErrorException("You cannot add an observer. Use the another method"));
            }
            return taskRepository.save(taskForUpdate);
        });
    }

    public Mono<Task> addObserver(String taskId, String obsId) {
        return findById(taskId).flatMap(taskForUpdate -> {
            if (taskForUpdate.getObserverIds().contains(obsId) || obsId.isEmpty()){
               return Mono.error(new AddObserverException("Observer`s id is null or already exist"));

            }
                    taskForUpdate.getObserverIds().add(obsId);
                    return taskRepository.save(taskForUpdate);
                }
        );
    }

    public Mono<Void> deleteById(String id) {
        return taskRepository.deleteById(id);
    }
}
