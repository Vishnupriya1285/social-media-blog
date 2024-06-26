package com.learning.springboot.socialmedia_blog_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;
    @Column(name="description")
    private String description;

    //Single Post can have Many Comments
    @OneToMany(mappedBy = "postEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CommentEntity> comments=new HashSet<>();
}

