package com.example.todoapp.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AddUserRequest {
    private String username;
    private String password;
}
