package com.example.spring_jwt_auth_example.web.model.response.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BriefPostResponse {
    private Long id;
    private String title;
    private String description;
    private String body;
    private Integer totalComments;
}
