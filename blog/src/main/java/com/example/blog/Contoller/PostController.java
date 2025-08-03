package com.example.blog.Contoller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.Model.Post;
import com.example.blog.Service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // get to post.html
    @GetMapping("/blog/create")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post"; // renders post.html
    }

    // create post
    @PostMapping("/blog/create")
    public String createPost(@RequestParam String title, @RequestParam String content,
            @RequestParam(required = false) String returnUrl, HttpSession session, Model model) {
        try {
            Post post = new Post(title, content);
            post = postService.createPost(post);
            session.setAttribute("newPost", post);

            if (returnUrl != null && !returnUrl.isEmpty()) {
                return "redirect:" + returnUrl;
            }
            return "redirect:/blog";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("returnUrl", returnUrl);
            return "post";
        }

    }



}
