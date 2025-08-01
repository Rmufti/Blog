package com.example.blog.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.blog.Model.Post;
import com.example.blog.Repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepo;

    public PostService(PostRepository repo) {
        this.postRepo = repo;
    }

    public Post createPost(Post post) {
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (post.getTitle().length() > 100) {
            throw new IllegalArgumentException("Title too long");
        }
        post.setCreated_at(LocalDateTime.now());
        // Additional checks like profanity filter, rate limiting, etc.

        return postRepo.save(post);
    }

    public void deletePost(int postId) {
        if (postRepo.existsById(postId)) {
            postRepo.deleteById(postId);
        } else {
            throw new RuntimeException("Post not found");
        }
    }

    public Post editPost(int postId, String newTitle, String newContent) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + postId));

        post.setTitle(newTitle);
        post.setContent(newContent);
        post.setCreated_at(LocalDateTime.now());

        return postRepo.save(post);
    }

}
