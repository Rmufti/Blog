package com.example.blog.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.blog.Model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class indexController {

        @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("username", user.getName());
        }
        return "index";
    }
    
}
