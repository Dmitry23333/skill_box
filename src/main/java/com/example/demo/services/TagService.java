package com.example.demo.services;

import com.example.demo.repositories.TagRepository;
import com.example.demo.struct.tags.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;


    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    public List<TagEntity> getTags() {
        return tagRepository.findAll();
    }

    public String getTagName(Integer id) {
        return tagRepository.getTagsNameById(id);
    }
}
