package com.example.blog.Contoller;

import com.example.blog.Model.User;
import com.example.blog.Service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    private final UserService userService;

    public indexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        // If user not in session, try getting from Spring Security context
        if (user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
                String username = auth.getName(); // get username from security context
                user = userService.findByName(username); // must return your User object
                session.setAttribute("loggedInUser", user);
            }
        }

        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }

        return "index";
    }
}
