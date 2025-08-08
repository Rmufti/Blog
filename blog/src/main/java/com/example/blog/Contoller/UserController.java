package com.example.blog.Contoller;

import com.example.blog.Model.User;
import com.example.blog.Service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registration form (GET)
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // return register.html view
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam String email,
            HttpSession session,
            Model model) {
        try {
            User user = new User(name, email, password);
            user = userService.register(user);
            session.setAttribute("loggedInUser", user);
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "signin";
        }
    }

    // Login page (GET) - must match SecurityConfig loginPage URL
    @GetMapping("/signin")
    public String signinPage() {
        return "signin"; // signin.html view (your login form)
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "redirect:/index";
    }

}
