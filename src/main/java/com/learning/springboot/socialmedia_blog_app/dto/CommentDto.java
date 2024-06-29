package com.learning.springboot.socialmedia_blog_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long commentId;
    @NotEmpty(message = "The Username should not be empty")
    private String userName;
    @Email
    @NotNull(message = "The email should not be null or empty")
    private String email;
    @NotEmpty
    @Size(min=10,message = "The body should be min of 5 characters")
    private String body;
}
