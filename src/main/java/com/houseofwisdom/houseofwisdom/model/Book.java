package com.houseofwisdom.houseofwisdom.model;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String authorName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="book_meta_id")

    private BookMeta bookMeta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public BookMeta getBookMeta() {
        return bookMeta;
    }

    public void setBookMeta(BookMeta bookMeta) {
        this.bookMeta = bookMeta;
    }
}
