package com.login.springlogin.services;

import org.springframework.stereotype.Service;

import com.login.springlogin.DTO.UserDTO;
import com.login.springlogin.models.User;

public interface UserService {
    public User findByEmail(String email);
    public User save(UserDTO user);
    public User update(User user);
    public void delete(User user);
}
