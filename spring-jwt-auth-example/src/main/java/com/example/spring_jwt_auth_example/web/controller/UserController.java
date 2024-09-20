package com.example.spring_jwt_auth_example.web.controller;


import com.example.spring_jwt_auth_example.aop.Accessible;
import com.example.spring_jwt_auth_example.entity.User;
import com.example.spring_jwt_auth_example.mapper.UserMapper;
import com.example.spring_jwt_auth_example.service.UserService;
import com.example.spring_jwt_auth_example.web.model.response.user.UserListResponse;
import com.example.spring_jwt_auth_example.web.model.response.user.UserResponse;
import com.example.spring_jwt_auth_example.web.model.secutiry.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserListResponse> findAll(/*@Valid PaginationRequest request*/) {
        return ResponseEntity.ok(
                userMapper.userToUserResponseList(
                        userService.findAll(/*request*/)
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
    @Accessible
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(
                        userService.findById(id)
                )
        );
    }

    @Operation(
            summary = "Update user by id",
            description = "Update user by id. Params: username",
            tags = {"user", "username"}
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
    @Accessible
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long userId, @RequestBody @Valid CreateUserRequest request) {
        User updatedUser = userService.update(userMapper.RequestToUser(userId, request));
        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }
    @Operation(
            summary = "Delete user by id",
            description = "Delete user by id",
            tags = {"user", "id"}
    )
    @DeleteMapping("/{id}")
    @Accessible
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
