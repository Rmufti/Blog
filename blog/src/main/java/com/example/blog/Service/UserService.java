package com.example.blog.Service;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.Model.User;
import com.example.blog.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepo;

    
    public UserService(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    // Create new user if email doesn't exist
    public User createUser(User user) throws Exception {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new Exception("User already exists with email: " + user.getEmail());
        }
        return userRepo.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get user by ID
    public User getUserById(Integer id) throws Exception {
        return userRepo.findById(id)
                .orElseThrow(() -> new Exception("User not found with ID: " + id));
    }

    // Update user by ID
    public User updateUser(Integer id, User user) throws Exception {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new Exception("User not found with ID: " + id));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepo.save(existingUser);
    }

    // Delete user by ID
    public void deleteUser(Integer id) throws Exception {
        if (!userRepo.existsById(id)) {
            throw new Exception("Cannot delete. User not found with ID: " + id);
        }
        userRepo.deleteById(id);
    }

    // Find user by email
    public User findByEmail(String email) throws Exception {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new Exception("User not found with email: " + email));
    }

    // Login: validate email and password
    public User login(String email, String password) throws Exception {
        User usr = userRepo.findByEmail(email)
                .orElseThrow(() -> new Exception("Invalid email or password."));

        if (!usr.getPassword().equals(password)) {
            throw new Exception("Invalid email or password.");
        }

        return usr;
    }
}
