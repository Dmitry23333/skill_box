package com.example.rest.demo.web.v2;

import ch.qos.logback.core.net.server.Client;
import com.example.rest.demo.mapper.v2.UserMapperV2;
import com.example.rest.demo.model.User;
import com.example.rest.demo.service.UserService;
import com.example.rest.demo.web.model.UpsertUserRequest;
import com.example.rest.demo.web.model.UserListResponse;
import com.example.rest.demo.web.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/user")
@RequiredArgsConstructor
public class UserControllerV2 {
    private final UserService userService;

    private final UserMapperV2 userMapperV2;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll(){
        return ResponseEntity.ok(
                userMapperV2.userToUserResponseList(
                        userService.findAll()
                )
        );
    }

    @GetMapping("/{id}")
    public  ResponseEntity <UserResponse> findById (@PathVariable Long id){
        return ResponseEntity.ok(
                userMapperV2.userToResponse(
                        userService.findById(id)
                )
        );
    }


    @PostMapping
    public ResponseEntity <UserResponse> create (@RequestBody @Valid UpsertUserRequest request){
        User newUser = userService.save(userMapperV2.requestToUser(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapperV2.userToResponse(newUser));
    }


    @PutMapping("/{id}")
    public ResponseEntity <UserResponse> update (@PathVariable("id") Long clientId, @RequestBody @Valid UpsertUserRequest request){
        User updatedUser = userService.update(userMapperV2.requestToUser(clientId,request));

        return ResponseEntity.ok(userMapperV2.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

  //  @PostMapping("/save-with-orders")
  //  public ResponseEntity <UserResponse> createWithOrders(@RequestBody CreateClientWithOrderRequest request){
  //      User client = Client.builder().name(request.getName()).build();
  //      List <News> orders = request.getOrders().stream()
  //              .map(orderRequest -> Order.builder()
  //                      .product(orderRequest.getProduct())
  //                      .cost(orderRequest.getCost())
  //                      .build())
  //              .toList();
  //      return ResponseEntity.status(HttpStatus.CREATED).body(
  //              userMapperV2.clientToResponse(userService.saveWithOrders(client, orders))
  //      );
  //  }
}
