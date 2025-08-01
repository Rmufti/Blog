package com.example.blog.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.Model.User;
import com.example.blog.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Show signin form; capture returnUrl query param
    @GetMapping("/signin")
    public String showSigninForm(@RequestParam(value = "returnUrl", required = false) String returnUrl, Model model) {
        model.addAttribute("returnUrl", returnUrl);
        return "signin";
    }

    // Process signin and save user in session
    @PostMapping("/signin")
    public String processSignin(@RequestParam String email,@RequestParam String password,@RequestParam(required = false) String returnUrl,HttpSession session,Model model) {
        try {
            User user = userService.login(email, password);
            session.setAttribute("loggedInUser", user);

            if (returnUrl != null && !returnUrl.isEmpty()) {
                return "redirect:" + returnUrl;
            }
            return "redirect:/index"; // default homepage
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("returnUrl", returnUrl);
            return "signin";
        }
    }


   @PostMapping("/signin/signup")
    public String processSignUp(@RequestParam String name,@RequestParam String email, @RequestParam String password, @RequestParam(required = false) String returnUrl,HttpSession session,Model model ){
        try{
            User usr = new User(name, email, password);
            usr = userService.createUser(usr);
            session.setAttribute("loggedInUser", usr);

            if(returnUrl != null && !returnUrl.isEmpty()){
                return "redirect:" + returnUrl;
            }
            return "redirect:/index";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("returnUrl", returnUrl);
            return "signin";
        }
    }


    
}
