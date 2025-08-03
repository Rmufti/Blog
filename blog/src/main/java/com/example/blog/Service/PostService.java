package com.example.blog.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.event.spi.LockEventListener;
import org.springframework.cglib.core.Local;
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
        post.setCreatedAt(LocalDateTime.now());

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
        post.setCreatedAt(LocalDateTime.now());

        return postRepo.save(post);
    }

    public List<Post> listNewest() {
        return postRepo.findAllByOrderByCreatedAtDesc();
    }

    public Post getPostById(int id) {
        Optional<Post> postOpt = postRepo.findById(id);
        return postOpt.orElse(null); // returns null if not found, you can throw exception if you prefer
    }



}
