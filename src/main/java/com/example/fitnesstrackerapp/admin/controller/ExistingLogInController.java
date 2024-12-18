package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;


public class ExistingLogInController {
    public void validateExistingUser(UserDto userDto, ActionEvent event, Label lblgetstarted) {
        UserService userService = new UserService();
        userService.validateExistingLogin(event, userDto.getEmail(), userDto.getPassword(), lblgetstarted);

    }

}
