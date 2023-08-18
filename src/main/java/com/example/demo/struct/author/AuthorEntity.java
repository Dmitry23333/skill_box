package com.example.demo.struct.author;

import com.example.demo.struct.book.BookEntity;
import com.example.demo.struct.book.links.Book2AuthorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "author")
@ApiModel(description = "data model of author entity")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "author id generated by database", position = 1)
    private int id;

    @Column(columnDefinition = "VARCHAR(255)")
    @ApiModelProperty(value = "author's photo", position = 2)
    private String photo;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @ApiModelProperty(value = "slug", position = 3)
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @ApiModelProperty(value = "full name of author", example = "Andrey Welden", position = 4)
    private String name;

    @Column(columnDefinition = "TEXT")
    @ApiModelProperty(value = "book's description", example = "some text", position = 5)
    private String description;

    @OneToMany(mappedBy = "authorEntity")
    @JsonIgnore
    private List<BookEntity> bookEntityList = new ArrayList<>();

    public List<BookEntity> getBookEntityList() {
        return bookEntityList;
    }

    public void setBookEntityList(List<BookEntity> bookEntityList) {
        this.bookEntityList = bookEntityList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
