package com.houseofwisdom.houseofwisdom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class BookMeta {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer numberOfPages;

    @Column
    private Integer numberOfAvailableCopies;

    @Column
    private String publisherName;

    @OneToOne(mappedBy = "bookMeta")
    @JsonBackReference
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Integer getNumberOfAvailableCopies() {
        return numberOfAvailableCopies;
    }

    public void setNumberOfAvailableCopies(Integer numberOfAvailableCopies) {
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
