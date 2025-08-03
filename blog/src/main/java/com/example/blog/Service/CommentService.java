package com.example.blog.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.blog.Model.Comment;
import com.example.blog.Model.Post;
import com.example.blog.Repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepo) {
        this.commentRepository = commentRepo;
    }

    public Comment createComment(Comment comment) {
        if (comment.getContent() == null) {
            throw new IllegalArgumentException("No content inputted");
        }
        if (comment.getContent().length() > 400) {
            throw new IllegalArgumentException("Comment too long");
        }
        comment.setDateAdded(LocalDateTime.now());

        return commentRepository.save(comment);
    }


}
