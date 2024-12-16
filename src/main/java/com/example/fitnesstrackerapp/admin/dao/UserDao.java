package com.example.fitnesstrackerapp.admin.dao;

import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.model.User;

import java.util.List;

public interface UserDao {
    User addUser(UserDto userDto);
    User getUser(int id);
    User getUserByEmail(String username);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();
}
