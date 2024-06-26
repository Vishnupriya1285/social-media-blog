package com.learning.springboot.socialmedia_blog_app.repository;

import com.learning.springboot.socialmedia_blog_app.model.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    @Query(value="SELECT * FROM comments WHERE post_id=?1",nativeQuery = true)
    List<CommentEntity> findByPostId(long postId);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM comments WHERE post_id=?1",nativeQuery = true)
    void deleteByPostId(long postId);
}
