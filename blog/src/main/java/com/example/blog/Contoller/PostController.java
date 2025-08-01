package com.example.blog.Contoller;

import org.springframework.stereotype.Controller;

import com.example.blog.Service.PostService;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }
    

}
