package com.example.spring_jwt_auth_example.web.model.response.post;


import com.example.spring_jwt_auth_example.web.model.response.category.CategoryResponse;
import com.example.spring_jwt_auth_example.web.model.response.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostResponse {
    private Long id;
    private String title;
    private String description;
    private String body;
    private CategoryResponse category;
    private List<CommentResponse> comments = new ArrayList<>();

}
