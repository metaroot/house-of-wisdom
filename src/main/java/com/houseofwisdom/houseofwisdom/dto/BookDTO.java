package com.houseofwisdom.houseofwisdom.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class BookDTO {
    private Long id;
    private String name;
    private String authorName;
}
