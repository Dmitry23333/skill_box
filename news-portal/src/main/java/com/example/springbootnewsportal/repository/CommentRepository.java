package com.example.springbootnewsportal.repository;

import com.example.springbootnewsportal.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsCommentByUser_Id(Long id);
}
