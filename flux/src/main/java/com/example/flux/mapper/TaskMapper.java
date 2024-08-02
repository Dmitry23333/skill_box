package com.example.flux.mapper;

import com.example.flux.entity.Task;
import com.example.flux.web.requset.UpsertTaskRequest;
import com.example.flux.web.response.BriefTaskResponse;
import com.example.flux.web.response.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TaskMapper {
    Task requestToTask(UpsertTaskRequest request);
    TaskResponse taskToResponse(Task task);
    BriefTaskResponse upsertTaskToResponse(Task task);
}
