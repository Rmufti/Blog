package com.example.blog.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.blog.Service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CommentController {
    
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    
}
