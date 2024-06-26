package com.learning.springboot.socialmedia_blog_app.controller;

import com.learning.springboot.socialmedia_blog_app.dto.CommentDto;
import com.learning.springboot.socialmedia_blog_app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //POST /v1/api/posts/{postId}/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createCommentByPostID(@PathVariable long postId, @RequestBody CommentDto commentDto)
    {
        return new ResponseEntity<>(commentService.createCommentByPostId(postId,commentDto), HttpStatus.CREATED);
    }
}
