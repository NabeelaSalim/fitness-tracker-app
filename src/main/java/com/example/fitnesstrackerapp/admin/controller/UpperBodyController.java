package com.example.fitnesstrackerapp.admin.controller;

import com.example.fitnesstrackerapp.admin.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class UpperBodyController implements Initializable {
    @FXML
    private Button btn_upperbodystartworkout;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        btn_upperbodystartworkout.setOnAction(event -> {
            UserService userService = new UserService();
            userService.changeScence1(event,"StartWorkout.fxml", "Upper body start workout");
        });

    }

}
