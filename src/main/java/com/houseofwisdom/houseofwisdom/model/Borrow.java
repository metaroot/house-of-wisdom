package com.houseofwisdom.houseofwisdom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Borrow {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long bookId;

    @Column
    private Integer combinationOccurences;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getCombinationOccurences() {
        return combinationOccurences;
    }

    public void setCombinationOccurences(Integer combinationOccurences) {
        this.combinationOccurences = combinationOccurences;
    }
}
