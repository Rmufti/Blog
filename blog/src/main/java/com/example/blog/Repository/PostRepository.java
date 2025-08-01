package com.example.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.Model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    
}
