package com.learning.springboot.socialmedia_blog_app.controller;

import com.learning.springboot.socialmedia_blog_app.dto.PostDto;
import com.learning.springboot.socialmedia_blog_app.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/api/posts")
public class PostController {
    @Autowired
    PostService postService;

    // GET -> /v1/api/posts
    @GetMapping
    public List<PostDto> getAllPosts()
    {
        return postService.getAllPosts();
    }
    //POST -> /v1/api/posts
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    //GET -> /v1/api/posts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id)
    {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }
    //PUT -> /v1/api/posts/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable long id,@RequestBody @Valid PostDto postDto)
    {
        //return new ResponseEntity<>(postService.updatePostById(id,postDto),HttpStatus.OK);
        return ResponseEntity.ok(postService.updatePostById(id,postDto));
    }

    //DELETE -> /v1/api/posts/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id)
    {
        return ResponseEntity.ok(postService.deletePostById(id));
    }
}

