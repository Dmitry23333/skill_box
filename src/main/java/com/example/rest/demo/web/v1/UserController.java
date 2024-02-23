package com.example.rest.demo.web.v1;

import com.example.rest.demo.mapper.v1.UserMapper;
import com.example.rest.demo.model.User;
import com.example.rest.demo.service.UserService;
import com.example.rest.demo.web.model.UpsertNewsRequest;
import com.example.rest.demo.web.model.UpsertUserRequest;
import com.example.rest.demo.web.model.UserListResponse;
import com.example.rest.demo.web.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity <UserListResponse> findAll(){
        return ResponseEntity.ok(
                userMapper.userListToUserResponseList(userService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity <UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(id))
        );
    }

    @PostMapping
    public  ResponseEntity <UserResponse> create(@RequestBody UpsertUserRequest request){
        User newUser = userService.save(userMapper.requestToUser(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity <UserResponse> update(@PathVariable("id") Long userId, @RequestBody UpsertUserRequest request){
        User updatedUser = userService.update(userMapper.requestToUser(userId,request));
        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
