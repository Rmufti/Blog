package com.example.blog.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.blog.Model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class blogController {

    @GetMapping("/blog")
    public String blogPage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("username", loggedInUser.getName());
        } else {
            System.out.println("No user found in session.");
        }
        return "blog";
    }

}
