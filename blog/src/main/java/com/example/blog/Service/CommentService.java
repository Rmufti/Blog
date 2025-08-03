package com.example.blog.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.blog.Model.Comment;
import com.example.blog.Model.Post;
import com.example.blog.Model.User;
import com.example.blog.Repository.CommentRepository;
import com.example.blog.Repository.PostRepository;
import com.example.blog.Repository.UserRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commRepo,  PostRepository postRepo, UserRepository userRepo){
        this.commentRepository = commRepo;
        this.postRepository = postRepo;
        this.userRepository = userRepo;
    }


    public Comment createComment(int postId, int userId, String content){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(content);

        return commentRepository.save(comment);


    }
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

}
