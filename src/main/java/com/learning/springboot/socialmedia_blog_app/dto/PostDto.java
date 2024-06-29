package com.learning.springboot.socialmedia_blog_app.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty
    @Size(min=5,message = "The Title should be minimun of 5 characters")
    private String title;
    @NotEmpty
    private String content;
    @Size(min=10,max = 100,message = "The description should be min of 10 characters and max of 100")
    private String description;
}
