package com.example.spring_jwt_auth_example.web.model.response.post;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostListResponse {
    private List<BriefPostResponse> posts = new ArrayList<>();

}
