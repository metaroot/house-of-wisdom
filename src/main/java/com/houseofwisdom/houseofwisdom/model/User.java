package com.houseofwisdom.houseofwisdom.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private Integer booksIssuedToTheUser;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBooksIssuedToTheUser() {
        return booksIssuedToTheUser;
    }

    public void setBooksIssuedToTheUser(Integer booksIssuedToTheUser) {
        this.booksIssuedToTheUser = booksIssuedToTheUser;
    }
}
