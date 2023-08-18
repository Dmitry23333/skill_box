package com.example.demo.struct.book;


import com.example.demo.struct.author.AuthorEntity;
import com.example.demo.struct.genre.GenreEntity;
import com.example.demo.struct.tags.TagEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    @Column(columnDefinition = "DATE NOT NULL")
    private Date pubDate;

    @Column (columnDefinition = "INT NOT NULL")
    private Integer isBestseller;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;

    @Column(columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer price;

    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
    }



    @Column(columnDefinition = "INT  DEFAULT 0")
    private Integer discount;
    @Column(columnDefinition = "INT  DEFAULT 0")
    private Integer B;
    @Column(columnDefinition = "INT  DEFAULT 0")
    private Integer C;

    @Column(columnDefinition = "INT  DEFAULT 0")
    private Integer K;

    @Column(columnDefinition = "INT  DEFAULT 0")
    private Double popularity;

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity() {
        this.popularity = B + 0.7 * C + 0.4 * K;
    }

    public TagEntity getTagEntity() {
        return tagEntity;
    }

    public void setTagEntity(TagEntity tagEntity) {
        this.tagEntity = tagEntity;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AuthorEntity authorEntity;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private TagEntity tagEntity;



    @ManyToMany
    @JoinTable(name = "book2genreEntity",
    joinColumns = {@JoinColumn(name = "book_id")})
    private Set<GenreEntity> genreEntities = new HashSet<>();

    public Set<GenreEntity> getGenreEntities() {
        return genreEntities;
    }

    public void setGenreEntities(Set<GenreEntity> genreEntities) {
        this.genreEntities = genreEntities;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer isBestseller() {
        return isBestseller;
    }

    public void setBestseller(Integer bestseller) {
        isBestseller = bestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(Integer isBestseller) {
        this.isBestseller = isBestseller;
    }
    public Integer getB() {
        return B;
    }

    public void setB(Integer b) {
        this.B = b;
    }

    public Integer getC() {
        return C;
    }

    public void setC(Integer c) {
        C = c;
    }

    public Integer getK() {
        return K;
    }

    public void setK(Integer k) {
        K = k;
    }
}
