package com.example.todoapp.controller;

import com.example.todoapp.request.UserRegistrationRequest;
import com.example.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @GetMapping
    public String showRegistration(Model model) {
        model.addAttribute("user", new UserRegistrationRequest());
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") UserRegistrationRequest userRegistrationRequest) {
        userService.save(userRegistrationRequest);
        return "redirect:/registration?success";
    }
}
