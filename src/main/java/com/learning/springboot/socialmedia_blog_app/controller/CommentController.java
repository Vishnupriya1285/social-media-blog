package com.learning.springboot.socialmedia_blog_app.controller;

import com.learning.springboot.socialmedia_blog_app.dto.CommentDto;
import com.learning.springboot.socialmedia_blog_app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //GET /v1/api/posts/{postId}/comments
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> fetchAllCommentsByPostID(@PathVariable long postId)
    {
        return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId),HttpStatus.OK);
    }
    //GET /v1/api/posts/{postId}/comments/{commentId}
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> fetchCommentByPosIdAndCommentID(@PathVariable long postId, @PathVariable long commentId)
    {
        return new ResponseEntity<>(commentService.getCommentByPostIdAndCommentId(postId,commentId), HttpStatus.OK);
    }

    //PUT /v1/api/posts/{postId}/comments/{commentId}
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentByPosIdAndCommentID(@PathVariable long postId, @PathVariable long commentId,@RequestBody  CommentDto commentDto)
    {
        return new ResponseEntity<>(commentService.updateCommentByPostIdAndCommentId(postId,commentId,commentDto), HttpStatus.OK);
    }

    //DELETE /v1/api/posts/{postId}/comments/{commentId}
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> CommentByPosIdAndCommentID(@PathVariable long postId, @PathVariable long commentId)
    {
        return new ResponseEntity<>(commentService.deleteCommentByPostIdAndCommentId(postId,commentId), HttpStatus.OK);
    }

    //DELETE /v1/api/posts/{postId}/comments
    @DeleteMapping("/posts/{postId}/comments")
    public ResponseEntity<String> deleteAllCommentsByPosId(@PathVariable long postId)
    {
        return new ResponseEntity<>(commentService.deleteAllCommentsOfPostFromPostId(postId),HttpStatus.OK);
    }

}
