package com.example.todoapp.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TaskList")
@RequiredArgsConstructor
@Getter
@Setter
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name="completed")
    private Boolean completed = Boolean.FALSE;
}
