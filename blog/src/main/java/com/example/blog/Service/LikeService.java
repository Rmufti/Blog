package com.example.blog.Service;

import org.springframework.stereotype.Service;

import com.example.blog.Model.Likes;
import com.example.blog.Model.Post;
import com.example.blog.Model.User;
import com.example.blog.Repository.LikesRepository;

@Service
public class LikeService {

    private LikesRepository likesRepo;

    public LikeService(LikesRepository repo) {
        this.likesRepo = repo;
    }

    public boolean userHasLikedPost(User user, Post post) {
        return likesRepo.existsByUserAndPost(user, post);
    }

    public void likePost(User user, Post post) {
        if (!userHasLikedPost(user, post)) {
            likesRepo.save(new Likes(user, post));
        }
    }

    public int getLikeCount(Post post) {
        return likesRepo.countByPost(post);
    }

}
