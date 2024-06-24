package com.learning.springboot.socialmedia_blog_app.service;
import com.learning.springboot.socialmedia_blog_app.dto.PostDto;
import com.learning.springboot.socialmedia_blog_app.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();
    PostResponse getAllPosts(int pageNo, int pageSize);

    PostDto getPostById(long id);
    PostDto createPost(PostDto postDto);
    PostDto updatePostById(long id,PostDto postDto);
    String deletePostById(long id);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDirection);
}