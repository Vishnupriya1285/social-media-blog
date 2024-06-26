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
import java.util.stream.Collectors;

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
        List<CommentEntity> commentEntities=commentRepository.findByPostId(postId);
        if(commentEntities!=null&&!commentEntities.isEmpty())
        {
            return commentEntities.stream().map(commentEntity -> mapEntityToDto(commentEntity)).collect(Collectors.toList());
        }
        return List.of();
    }

    @Override
    public CommentDto getCommentByPostIdAndCommentId(long postId, long commentId) {
        //Fetching PostEntity using PostRepository from postId
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",String.valueOf(postId)));

        //Fetching CommentEntity using CommentRepository from commentId
        CommentEntity commentEntity=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","ID",String.valueOf(commentId)));

        //Validate comment belong to particular Post
        if(!(commentEntity.getPostEntity().getId()).equals(postEntity.getId()))
            throw new RuntimeException("Bad Request :: Comment not found!");

        //Map commentEntity to commentDto
        return mapEntityToDto(commentEntity);
    }

    @Override
    public CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto) {
        //Fetching PostEntity using PostRepository from postId
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",String.valueOf(postId)));

        //Fetching CommentEntity using CommentRepository from commentId
        CommentEntity commentEntity=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","ID",String.valueOf(commentId)));

        //Validate comment belong to particular Post
        if(!(commentEntity.getPostEntity().getId()).equals(postEntity.getId()))
            throw new RuntimeException("Bad Request :: Comment not found!");
        //Updating CommentEntity
        commentEntity.setUserName(commentDto.getUserName());
        commentEntity.setEmail(commentDto.getEmail());
        commentEntity.setBody(commentDto.getBody());

        //Save updated CommentEntity to the DB
        CommentEntity updatedCommentEntity=commentRepository.save(commentEntity);

        //Mapping updated commentEntity to Dto and return it
        return mapEntityToDto(updatedCommentEntity);
    }

    @Override
    public String deleteCommentByPostIdAndCommentId(long postId, long commentId) {
        //Fetching PostEntity using PostRepository from postId
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",String.valueOf(postId)));

        //Fetching CommentEntity using CommentRepository from commentId
        CommentEntity commentEntity=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","ID",String.valueOf(commentId)));

        //Validate comment belong to particular Post
        if(!(commentEntity.getPostEntity().getId()).equals(postEntity.getId()))
            throw new RuntimeException("Bad Request :: Comment not found!");
        //Delete CommentEntity
        commentRepository.deleteById(commentId);
        return "Comment with Id :"+commentId+ " for the post with Id : "+postId+" is deleted.";
    }

    @Override
    public String deleteAllCommentsOfPostFromPostId(long postId) {
        //Fetching PostEntity using PostRepository from postId
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",String.valueOf(postId)));
        //deleting all the comments for that particular Post
        commentRepository.deleteByPostId(postEntity.getId());
        return "All the Comments for the post with Id : "+postId+" is deleted.";
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
