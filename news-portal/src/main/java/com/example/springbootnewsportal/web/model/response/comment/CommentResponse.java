package com.example.springbootnewsportal.web.model.response.comment;

import com.example.springbootnewsportal.web.model.response.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentResponse {

    private Long id;
    private String comment;
    private UserResponse user;
}
