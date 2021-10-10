package com.houseofwisdom.houseofwisdom.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class BookDTO {
    private Long id;
    private String name;
    private String authorName;
}
