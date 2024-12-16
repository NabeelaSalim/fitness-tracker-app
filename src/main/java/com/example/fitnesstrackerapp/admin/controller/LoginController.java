package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.model.User;
import com.example.fitnesstrackerapp.admin.service.UserService;
import com.example.fitnesstrackerapp.utils.DatabaseConnection;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    /*@FXML
    private Button getStartedButton;

    public void getStartedButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) getStartedButton.getScene().getWindow();
        stage.close();
    }*/
    @FXML
    public Label lblgetstarted;
    @FXML
    private Button btn_login;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserService userService = new UserService();

                userService.validateLogin(event, tf_email.getText(), tf_password.getText(), lblgetstarted);

                //after validate login create user
                UserDto userDto = new UserDto();
                userDto.setEmail(tf_email.getText());
                userDto.setPassword(tf_password.getText());
                userDto.setName(tf_email.getText());

            }
        });



    }
}