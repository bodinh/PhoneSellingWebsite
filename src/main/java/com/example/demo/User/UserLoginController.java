package com.example.demo.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLoginController {
    @GetMapping("/login/index")
    public String login() {
        return "/user/Login/login";
    }

    @GetMapping("/register/index")
    public String register() {
        return "/user/Register/register";
    }
}
