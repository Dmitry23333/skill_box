package com.example.demo.struct.genre;

import com.example.demo.struct.book.BookEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.eclipse.sisu.Nullable;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "genre")
public class GenreEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    @JsonIgnore
    private int id;

    public GenreEntity getParent() {
        return parent;
    }

    public void setParent(GenreEntity parent) {
        this.parent = parent;
    }

    public List<GenreEntity> getChildren() {
        return children;
    }

    public void setChildren(List<GenreEntity> children) {
        this.children = children;
    }
//  @Column(columnDefinition = "INT DEFAULT 0")
    // private int parentId;

    @ManyToOne
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "FK_PARENT_ID"))
    @JsonIgnore
    private GenreEntity parent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<GenreEntity> children = new ArrayList<>();


    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @JsonIgnore
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    @JsonIgnore
    private String name;

    @ManyToMany(mappedBy = "genreEntities")
    @JsonIgnore
    private Set<BookEntity> bookEntityList = new HashSet<>();


    public Set<BookEntity> getBookEntityList() {
        return bookEntityList;
    }

    public void setBookEntityList(Set<BookEntity> bookEntityList) {
        this.bookEntityList = bookEntityList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
