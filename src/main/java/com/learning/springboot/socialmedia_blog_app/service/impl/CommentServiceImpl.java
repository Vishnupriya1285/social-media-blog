package com.learning.springboot.socialmedia_blog_app.service.impl;

import com.learning.springboot.socialmedia_blog_app.dto.CommentDto;
import com.learning.springboot.socialmedia_blog_app.exceptionhandlers.ResourceNotFoundException;
import com.learning.springboot.socialmedia_blog_app.model.CommentEntity;
import com.learning.springboot.socialmedia_blog_app.model.PostEntity;
import com.learning.springboot.socialmedia_blog_app.repository.CommentRepository;
import com.learning.springboot.socialmedia_blog_app.repository.PostRepository;
import com.learning.springboot.socialmedia_blog_app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDto createCommentByPostId(long postId,CommentDto commentDto) {
        //Map CommentDto to CommentEntity
        CommentEntity commentEntity=mapCommentDtoToEntity(commentDto);
        //Fetch Post from Post Repository using postId
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",String.valueOf(postId)));

        //Set Comment to particular Post
        commentEntity.setPostEntity(postEntity);

        //Save Comment Entity to DB
        CommentEntity savedCommentEntity=commentRepository.save(commentEntity);

        //Map CommentEntity to CommentDto
        return mapEntityToDto(savedCommentEntity);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        return List.of();
    }

    @Override
    public CommentDto getCommentByPostIdAndCommentId(long postId, long commentId) {
        return null;
    }

    private CommentEntity mapCommentDtoToEntity(CommentDto commentDto) {
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setUserName(commentDto.getUserName());
        commentEntity.setEmail(commentDto.getEmail());
        commentEntity.setBody(commentDto.getBody());

        return commentEntity;
    }

    private CommentDto mapEntityToDto(CommentEntity savedCommentEntity) {
        CommentDto commentDto=new CommentDto();
        commentDto.setCommentId(savedCommentEntity.getCommentId());
        commentDto.setUserName(savedCommentEntity.getUserName());
        commentDto.setEmail(savedCommentEntity.getEmail());
        commentDto.setBody(savedCommentEntity.getBody());

        return commentDto;
    }
}
