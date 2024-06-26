package com.learning.springboot.socialmedia_blog_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long commentId;
    private String userName;
    private String email;
    private String body;
}
