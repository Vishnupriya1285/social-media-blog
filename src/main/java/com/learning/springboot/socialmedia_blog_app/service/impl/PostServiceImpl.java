package com.learning.springboot.socialmedia_blog_app.service.impl;

import com.learning.springboot.socialmedia_blog_app.dto.PostDto;
import com.learning.springboot.socialmedia_blog_app.exceptionhandlers.ResourceNotFoundException;
import com.learning.springboot.socialmedia_blog_app.model.PostEntity;
import com.learning.springboot.socialmedia_blog_app.repository.PostRepository;
import com.learning.springboot.socialmedia_blog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> postEntities=postRepository.findAll();

        //Map (or convert) PostEntity to PostDto
        if(postEntities!=null)
            return postEntities.stream()
                    .map(postEntity->mapEntityToDto(postEntity)).collect(Collectors.toList());

        return List.of();
    }

    @Override
    public PostDto getPostById(long id) {
        PostEntity postEntity=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",String.valueOf(id)));
        return mapEntityToDto(postEntity);
    }

    @Override
    public PostDto createPost(PostDto inPostDto) {
        PostEntity postEntity=mapPostDtoToPostEntity(inPostDto);
      //  PostEntity createdPostEntity= postRepository.save(postEntity);
//        PostDto outPostDto=mapEntityToDto(createdPostEntity);
//        return outPostDto;
        return mapEntityToDto(postRepository.save(postEntity));
    }

    @Override
    public PostDto updatePostById(long id,PostDto postDto) {
        PostEntity postEntityToBeUpdated=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",String.valueOf(id)));
        postEntityToBeUpdated.setTitle(postDto.getTitle());
        postEntityToBeUpdated.setContent(postDto.getContent());
        postEntityToBeUpdated.setDescription(postDto.getDescription());
        PostEntity updatedPostEntity=postRepository.save(postEntityToBeUpdated);
        PostDto updatedPostDto=mapEntityToDto(updatedPostEntity);
        return updatedPostDto;
    }

    @Override
    public String deletePostById(long id) {
        PostEntity postEntityToBeDeleted=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",String.valueOf(id)));
        postRepository.delete(postEntityToBeDeleted);
        return "The post with "+id+" is deleted";
    }

    private PostDto mapEntityToDto(PostEntity postEntity) {
        PostDto postDto=new PostDto();
        postDto.setId(postEntity.getId());
        postDto.setTitle(postEntity.getTitle());
        postDto.setDescription(postEntity.getDescription());
        postDto.setContent(postEntity.getContent());
        return postDto;
    }

    private PostEntity mapPostDtoToPostEntity(PostDto postDto) {
        PostEntity postEntity=new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());
        return postEntity;
    }
}