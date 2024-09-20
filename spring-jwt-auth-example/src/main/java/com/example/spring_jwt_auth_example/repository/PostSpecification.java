package com.example.spring_jwt_auth_example.repository;


import com.example.spring_jwt_auth_example.entity.Post;
import com.example.spring_jwt_auth_example.web.model.request.PostFilterRequest;
import org.springframework.data.jpa.domain.Specification;

public interface PostSpecification {
    static Specification<Post> withFilter(PostFilterRequest postFilterRequest) {
        return Specification.where(byAuthorId(postFilterRequest.getAuthorId()))
                .and(byCategoryId(postFilterRequest.getCategoryId()));
    }
    static Specification<Post> byCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }
    static Specification<Post> byAuthorId(Long authorId) {
        return (root, query, criteriaBuilder) -> {
            if (authorId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("author").get("id"), authorId);
        };
    }
}
