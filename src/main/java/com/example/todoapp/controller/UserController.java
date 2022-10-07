package com.example.todoapp.controller;

import com.example.todoapp.model.ToDoItem;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.ToDoItemRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.request.AddToDoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final ToDoItemRepository toDoItemRepository;

    @GetMapping
    public Iterable<ToDoItem> getAllItems() {
        return toDoItemRepository.getToDoItems();
    }
    // Get user by id
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        System.out.println("Get user by id");
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }
    // Get todo item by id
    @PostMapping("{userId/todos}")
    public void addToDoItem(@PathVariable Long userId,
                            @RequestBody AddToDoRequest toDoItemRequest) {

        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        ToDoItem newToDoItem = new ToDoItem();
        newToDoItem.setDescription(toDoItemRequest.getDescription());
        user.getTodoItems().add(newToDoItem);
        toDoItemRepository.save(newToDoItem);
        userRepository.save(user);
    }

    // Change todo status todo item by id
    @PostMapping("todos/{todoItemId}")
    public void toggleTodoItemCompleted(@PathVariable Long todoItemId) {
        ToDoItem toDoItem = toDoItemRepository.findById(todoItemId).orElseThrow(NoSuchElementException::new);
        toDoItem.setCompleted(!toDoItem.getCompleted());
        toDoItemRepository.save(toDoItem);
    }

    // Delete user's todo item by todo-id
    @GetMapping("{userId}/todos/{todoItemId}")
    public void deleteTodo(@PathVariable("userId") Long userId, @PathVariable("todoItemId") Long todoItemId) {
        System.out.println(userId+" "+todoItemId);
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        ToDoItem toDoItem = toDoItemRepository.findById(todoItemId).orElseThrow(NoSuchElementException::new);
        user.getTodoItems().remove(toDoItem);
        toDoItemRepository.delete(toDoItem);
    }
    // Delete user by id
    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.delete(user);
    }


    @GetMapping("/edit/item/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }


}
