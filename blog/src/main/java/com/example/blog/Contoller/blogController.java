package com.example.blog.Contoller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.Model.Comment;
import com.example.blog.Model.Post;
import com.example.blog.Model.User;
import com.example.blog.Service.CommentService;
import com.example.blog.Service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class blogController {

    private final PostService postService;

    // Constructor injection
    public blogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/blog")
    public String blog(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("username", user.getName());
        }

        List<Post> posts = postService.listNewest();
        model.addAttribute("posts", posts);


        return "blog";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePostById(@PathVariable int id) {
        postService.deletePost(id);
        return "redirect:/blog"; // Ensure this points to your blog listing
    }

}
