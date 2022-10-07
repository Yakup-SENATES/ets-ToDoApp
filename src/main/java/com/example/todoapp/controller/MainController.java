package com.example.todoapp.controller;

import com.example.todoapp.model.ToDoItem;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.ToDoItemRepository;
import com.example.todoapp.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ToDoItemRepository toDoItemRepository;
    private final UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/tasklist")
    public String taskList( Model model) {
        List<ToDoItem> toDoItems = toDoItemRepository.getToDoItems();
        model.addAttribute("tasklist", toDoItems);
        long userId = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("userId", userId);
        return "tasklist";
    }



}




