package com.example.blog.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDate dateAdded;

    public User() {

    }

    public User(int id, String name, String email, String password, LocalDate date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateAdded = date;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Automatically set dateAdded before first save
    @PrePersist
    public void prePersist() {
        if (this.dateAdded == null) {
            this.dateAdded = LocalDate.now();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }
    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded){
        this.dateAdded = dateAdded;
    }

}
