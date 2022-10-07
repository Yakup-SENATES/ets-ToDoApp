package com.example.todoapp.controller;

import com.example.todoapp.model.ToDoItem;
import com.example.todoapp.repository.ToDoItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ToDoItemRepository toDoItemRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String home(Model model) {
        List<ToDoItem> toDoItems = toDoItemRepository.getToDoItems();
        model.addAttribute("items", toDoItems);
        return "index";
    }

    @GetMapping("/tasklist")
    public String taskList( Model model) {
        List<ToDoItem> toDoItems = toDoItemRepository.getToDoItems();

        model.addAttribute("tasklist", toDoItems);
        return "tasklist";
    }



}




