package com.example.spring_jwt_auth_example.repository;

import com.example.spring_jwt_auth_example.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    boolean existsPostByAuthor_Id(Long authorId);
}
