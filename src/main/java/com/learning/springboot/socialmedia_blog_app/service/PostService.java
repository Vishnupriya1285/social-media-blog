package com.learning.springboot.socialmedia_blog_app.service;
import com.learning.springboot.socialmedia_blog_app.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();
    PostDto getPostById(long id);
    PostDto createPost(PostDto postDto);
    PostDto updatePostById(long id,PostDto postDto);
    String deletePostById(long id);
}