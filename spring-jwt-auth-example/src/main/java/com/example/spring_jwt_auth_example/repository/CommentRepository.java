package com.example.spring_jwt_auth_example.repository;


import com.example.spring_jwt_auth_example.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsCommentByUser_Id(Long id);
}
