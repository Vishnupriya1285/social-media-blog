package com.learning.springboot.socialmedia_blog_app.repository;

import com.learning.springboot.socialmedia_blog_app.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

}
