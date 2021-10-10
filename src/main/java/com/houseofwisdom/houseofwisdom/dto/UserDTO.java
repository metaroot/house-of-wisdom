package com.houseofwisdom.houseofwisdom.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String userName;
    private Integer booksIssuedToTheUser;
}
