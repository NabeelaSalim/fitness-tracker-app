package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.dto.UserDto;
import com.example.fitnesstrackerapp.admin.service.UserService;
import com.example.fitnesstrackerapp.utils.PasswordHashing;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SigninController implements Initializable {
    @FXML
    private Button btn_create;
    @FXML
    private Button btn_redirect_login;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_confirm_password;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_phone;
    @FXML
    public Label lbl_work;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserService userService = new UserService();
                String password = PasswordHashing.hashPassword(tf_password.getText());
                String confirm_password = PasswordHashing.hashPassword(tf_confirm_password.getText());

                //after validate login create user
                UserDto userDto = new UserDto();
                userDto.setEmail(tf_email.getText());
                userDto.setPassword(password);
                userDto.setName(tf_username.getText());
                userDto.setPhone(tf_phone.getText());

                userService.addUser(event, userDto, lbl_work, confirm_password);
                userService.changeScence1(event,"user-login.fxml", "Login to your account");
            }
        });
        btn_redirect_login.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"user-login.fxml", "Login to your account");
        });


    }



    public void setUserInformation(String username) {
        lbl_work.setText("Welcome " + username);
    }

}
