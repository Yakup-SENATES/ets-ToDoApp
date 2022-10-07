package com.example.todoapp.service;


import com.example.todoapp.model.User;
import com.example.todoapp.request.UserRegistrationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {

    User save (UserRegistrationRequest  userRegistrationRequest);
}
