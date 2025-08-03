package com.example.blog.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Likes(){

    }

    public Likes(User user, Post post){
        this.user = user;
        this.post = post;
    }

    public int getId(){
        return id;
    }
    public User getUser(){
        return user;
    }
    public Post getPost(){
        return post;
    }

    public void setUser(User user){
        this.user = user;
    }
    public void setPost(Post pst){
        this.post = pst;
    }
}
