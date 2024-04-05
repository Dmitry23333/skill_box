package com.example.springbootnewsportal.repository;

import com.example.springbootnewsportal.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    boolean existsPostByAuthor_Id(Long authorId);
}
