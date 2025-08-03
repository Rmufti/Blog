package com.example.blog.Contoller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.events.CommentEvent;

import com.example.blog.Model.Comment;
import com.example.blog.Model.Post;
import com.example.blog.Model.User;
import com.example.blog.Service.CommentService;
import com.example.blog.Service.LikeService;
import com.example.blog.Service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class blogController {

    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;

    // Constructor injection
    public blogController(PostService postService, CommentService commentService, LikeService likeService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
    }

    @GetMapping("/blog")
    public String blog(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("username", user.getName());
        }

        List<Post> posts = postService.listNewest();
        List<Comment> allComments = commentService.findAll(); // Get all comments
        // Group comments by post ID
        Map<Integer, List<Comment>> commentsByPostId = new HashMap<>();
        for (Comment comment : allComments) {
            int postId = comment.getPost().getId();
            commentsByPostId.computeIfAbsent(postId, k -> new ArrayList<>()).add(comment);
        }

        //Likes
        Map<Integer, Integer> likeCounts = new HashMap<>();
        for (Post post : posts) {
            int count = likeService.getLikeCount(post);
            likeCounts.put(post.getId(), count);
        }

        
        model.addAttribute("likeCounts", likeCounts);
        model.addAttribute("posts", posts);
        model.addAttribute("commentsByPostId", commentsByPostId);

        return "blog";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePostById(@PathVariable int id) {
        postService.deletePost(id);
        return "redirect:/blog"; // Ensure this points to your blog listing
    }

    @PostMapping("/blog/createComment")
    public String createComment(@RequestParam int postId, @RequestParam String content, HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            throw new RuntimeException("User not logged in");
        }

        commentService.createComment(postId, user.getId(), content);

        return "redirect:/blog";

    }

    @PostMapping("/blog/likePost")
    public String likePost(@RequestParam int postId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null)
            throw new RuntimeException("User not logged in");

        Post post = postService.getPostById(postId);

        likeService.likePost(user, post);

        return "redirect:/blog";
    }

}
