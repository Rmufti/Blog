package com.example.blog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.Model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByName(String name); // For authentication
    boolean existsByEmail(String email);    // For signup validation
    Optional<User> findByEmail(String email);



}
