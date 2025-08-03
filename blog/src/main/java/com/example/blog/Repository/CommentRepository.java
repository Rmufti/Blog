package com.example.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.Model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
