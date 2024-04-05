package com.example.springbootnewsportal.web.controller;

import com.example.springbootnewsportal.entity.User;
import com.example.springbootnewsportal.mapper.UserMapper;
import com.example.springbootnewsportal.service.UserService;
import com.example.springbootnewsportal.web.PaginationRequest;
import com.example.springbootnewsportal.web.model.request.user.UpsertUserRequest;
import com.example.springbootnewsportal.web.model.response.user.UserListResponse;
import com.example.springbootnewsportal.web.model.response.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User api")
public class UserController {


    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping()
    @Operation(
            summary = "Get users",
            description = "Get all users. Return name",
            tags = {"user", "name"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserListResponse.class), mediaType = "application/json")
                    }
            )
    })
    @Validated
    public ResponseEntity<UserListResponse> findAll(@Valid PaginationRequest request) {
        return ResponseEntity.ok(
                userMapper.userToUserResponseList(
                        userService.findAll(request)
                )
        );
    }

    @Operation(
            summary = "Get user by id",
            description = "Get user by id. Return name",
            tags = {"user"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(
                        userService.findById(id)
                )
        );
    }


    @Operation(
            summary = "Create users",
            description = "Create users. Params: username",
            tags = {"user", "name"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json")
                    }
            )
    })
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request) {
        User newUser = userService.save(userMapper.RequestToUser(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(newUser));
    }


    @Operation(
            summary = "Update user by id",
            description = "Update user by id. Params: username",
            tags = {"user","username"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json")
                    }
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long userId, @RequestBody @Valid UpsertUserRequest request) {

        User updatedUser = userService.update(userMapper.RequestToUser(userId, request));

        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }

    @Operation(
            summary = "Delete user by id",
            description = "Delete user by id",
            tags = {"user", "id"}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
