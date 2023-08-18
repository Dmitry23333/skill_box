package com.example.demo.controllers;

import com.example.demo.services.BookService;
import com.example.demo.data.BooksPageDto;
import com.example.demo.services.TagService;
import com.example.demo.struct.tags.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TagsController {
    private final TagService tagService;
    private final BookService bookService;

    @Autowired
    public TagsController(TagService tagService, BookService bookService) {
        this.tagService = tagService;
        this.bookService = bookService;
    }


    @ModelAttribute("tags")
    public List<TagEntity> tags() {
        return tagService.getTags();
    }

    @GetMapping(value = {"/books/tag", "/books/tag/{id}"})
    public String getBooksByTag(@PathVariable(value = "id", required = false) Integer id,
                                Model model) {
        model.addAttribute("id", id);
        model.addAttribute("tag", tagService.getTagName(id));
        model.addAttribute("booksByTag", bookService.getPageOfBooksByTag(id, 0, 5).getContent());
        return "/tags/index";
    }

    @GetMapping("/books/tag/page/{id}")
    @ResponseBody
    public BooksPageDto getNextPageOfBooksById(@RequestParam("offset") Integer offset,
                                               @RequestParam("limit") Integer limit,
                                               @PathVariable Integer id) {
        return new BooksPageDto(bookService.getPageOfBooksByTag(id, offset, limit).getContent());
    }
}
