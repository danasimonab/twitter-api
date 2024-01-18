package com.project.twitterapi.dto;

import lombok.Data;

@Data
public class PostResponseDto {

    private Long id;
    private String username;
    private String message;
    private Long timestamp;
}
