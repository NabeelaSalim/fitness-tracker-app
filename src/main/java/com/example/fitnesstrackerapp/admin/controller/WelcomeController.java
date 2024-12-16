package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    @FXML
    public Label lb_welcome;
    @FXML
    public Label lb_one;
    @FXML
    public Label lb_two;
    @FXML
    public Button btn_start;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_start.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"select_view.fxml", "Starting and navigating your fitness journey");
        });


}}
