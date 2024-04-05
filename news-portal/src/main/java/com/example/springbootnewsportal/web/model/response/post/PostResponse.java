package com.example.springbootnewsportal.web.model.response.post;

import com.example.springbootnewsportal.web.model.response.category.CategoryResponse;
import com.example.springbootnewsportal.web.model.response.comment.CommentResponse;
import lombok.*;

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
