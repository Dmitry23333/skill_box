package com.example.demo.repositories;

import com.example.demo.struct.tags.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    @Query(value = "SELECT  name FROM  tag WHERE id = :tagId", nativeQuery = true)
    String getTagsNameById(@Param("tagId") Integer tagId);
}
