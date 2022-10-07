package com.example.todoapp;

import com.example.todoapp.model.ToDoItem;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.ToDoItemRepository;
import com.example.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RequiredArgsConstructor
@EnableWebMvc
@EnableSwagger2
public class ToDoAppApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ToDoItemRepository toDoItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(ToDoAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("q@q.q");
        user.setPassword("123");

        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setId(1L);
        toDoItem.setDescription("todo app example");
//        toDoItem.getCompleted();

        user.getTodoItems().add(toDoItem);
        toDoItemRepository.save(toDoItem);
        userRepository.save(user);
    }
}
