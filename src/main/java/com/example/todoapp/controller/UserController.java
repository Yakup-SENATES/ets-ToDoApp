package com.example.todoapp.controller;

import com.example.todoapp.model.ToDoItem;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.ToDoItemRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.request.AddToDoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final ToDoItemRepository toDoItemRepository;

    // Get user by id
    @GetMapping("{/userId}")
    public User getUserById(@PathVariable Long userId) {
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
    @DeleteMapping("{userId}/todos/{todoItemId}")
    public void deleteTodo(@PathVariable Long userId, @PathVariable Long todoItemId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        ToDoItem toDoItem = toDoItemRepository.findById(todoItemId).orElseThrow(NoSuchElementException::new);
        user.getTodoItems().remove(toDoItem);
        toDoItemRepository.delete(toDoItem);
    }
    // Delete user by id
    @DeleteMapping("{userId}")
    public void deleteTodo(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.delete(user);
    }


}
