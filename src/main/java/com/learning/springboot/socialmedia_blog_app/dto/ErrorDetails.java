package com.learning.springboot.socialmedia_blog_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;
    private int status;
}
