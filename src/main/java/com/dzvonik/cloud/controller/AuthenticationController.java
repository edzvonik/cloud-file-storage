package com.dzvonik.cloud.controller;

import com.dzvonik.cloud.service.AuthenticationService;
import com.dzvonik.cloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @GetMapping("/signin")
    public String signInForm(Model model) {
        return "signin";
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        boolean isAuthenticated = authenticationService.authenticate(username, password);
        if (isAuthenticated) {
            return "redirect:/hello";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "signin";
        }
    }

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        if (!userService.existsByUsername(username)) {
            userService.save(username, password);
            return "redirect:/auth/signin";
        } else {
            model.addAttribute("error", "User already exist");
            return "signup";
        }
    }

}
