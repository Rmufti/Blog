package com.example.blog.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Comment() {
        // No-arg constructor needed by JPA
    }

    public Comment(String content, User user, Post post, LocalDateTime date) {
        this.content = content;
        this.user = user;
        this.post = post;
        this.createdAt = date;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateAdded() {
        return createdAt;
    }

    public void setUser(User usr) {
        this.user = usr;
    }

    public void setPost(Post pst) {
        this.post = pst;
    }

    public void setContent(String con) {
        this.content = con;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.createdAt = dateAdded;
    }

}
