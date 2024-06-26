package com.learning.springboot.socialmedia_blog_app.service;

import com.learning.springboot.socialmedia_blog_app.dto.CommentDto;
import com.learning.springboot.socialmedia_blog_app.repository.CommentRepository;

import java.util.List;

public interface CommentService {
   CommentDto createCommentByPostId(long postId, CommentDto commentDto);
   List<CommentDto> getAllCommentsByPostId(long postId);
   CommentDto getCommentByPostIdAndCommentId(long postId,long commentId);
   CommentDto updateCommentByPostIdAndCommentId(long postId,long commentId,CommentDto commentDto);
   String deleteCommentByPostIdAndCommentId(long postId,long commentId);
   String deleteAllCommentsOfPostFromPostId(long postId);
}
