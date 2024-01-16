package com.project.twitterapi.dto;

import lombok.Data;

@Data
public class UserRequestDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
