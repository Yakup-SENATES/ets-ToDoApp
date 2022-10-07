package com.example.todoapp.repository;

import com.example.todoapp.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

        @Query("Select todoItems from User ")
        List<ToDoItem> getToDoItems();

}
