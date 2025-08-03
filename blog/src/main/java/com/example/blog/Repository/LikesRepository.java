package com.example.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.Model.Likes;
import com.example.blog.Model.Post;
import com.example.blog.Model.User;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {
    boolean existsByUserAndPost(User user, Post post);
    int countByPost(Post post);
    
} 
    
