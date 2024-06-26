package com.learning.springboot.socialmedia_blog_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    private String userName;
    private String email;
    private String body;

    //Many Comments can belong to Single Post
    //Mapping b/w comments and posts entities or tables
    //comments table is managing the relationship b/w comments and posts tables with post_id as foreign key in comments table
    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private PostEntity postEntity;
}
